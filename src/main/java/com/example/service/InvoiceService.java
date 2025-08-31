package com.example.service;

import com.example.DTO.InvoiceDTO;
import com.example.entity.Invoice;
import com.example.entity.SubscriptionPlan;
import com.example.repository.InvoiceRepository;
import com.example.repository.SubscriptionPlanRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // ✅ Generate and Save Invoice
    public byte[] generateInvoice(Long subscriptionId, String userEmail, String paymentMethod) {
        SubscriptionPlan sub = subscriptionPlanRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        String invoiceNumber = "ZIDIO-" + UUID.randomUUID();
        String invoiceURL = "/api/invoice/download/" + subscriptionId;

        Invoice invoice = new Invoice(
                userEmail,
                "Subscription Plan",
                sub.getPrice(),
                paymentMethod,
                "PAID",
                invoiceNumber,
                invoiceURL,
                sub
        );
        invoiceRepository.save(invoice);

        return createPDF(invoice);
    }

    // ✅ Create PDF Invoice
    private byte[] createPDF(Invoice invoice) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            document.addTitle("Zidio Connection Invoice");

            Paragraph title = new Paragraph("INVOICE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Invoice Number: " + invoice.getInvoiceNumber()));
            document.add(new Paragraph("Issued Date: " + LocalDate.now()));
            document.add(new Paragraph("Customer Email: " + invoice.getUserEmail()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Plan Name: " + invoice.getSubscriptionPlan().getName()));
            document.add(new Paragraph("Price: ₹" + invoice.getSubscriptionPlan().getPrice()));
            document.add(new Paragraph("Duration: " + invoice.getSubscriptionPlan().getDurationInDays() + " days"));
            document.add(new Paragraph("Payment Method: " + invoice.getPaymentMethod()));
            document.add(new Paragraph("Status: " + invoice.getStatus()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Thank you for choosing Zidio Connection!",
                    FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return baos.toByteArray();
    }
    
}

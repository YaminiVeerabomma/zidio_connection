package com.example.service;

	

import com.example.entity.SubscriptionPlan;
import com.example.repository.InvoiceRepository;
import com.example.repository.SubscriptionPlanRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    public byte[] generateInvoice(Long subscriptionId) {
        SubscriptionPlan sub = subscriptionPlanRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            document.addTitle("Zidio_Connection_Invoice");

            // Title
            Paragraph title = new Paragraph("INVOICE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Invoice Number: ZIDIO-" + UUID.randomUUID()));
            document.add(new Paragraph("Issued Date: " + LocalDate.now()));
            document.add(new Paragraph(" "));

            // Subscription Plan Details
            document.add(new Paragraph("Plan Name: " + sub.getName()));
            document.add(new Paragraph("Price: ₹" + sub.getPrice()));
            document.add(new Paragraph("Duration: " + sub.getDurationInDays() + " days"));
            document.add(new Paragraph("Description: " + sub.getDescription()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Thank you for choosing Zidio Connection!", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return baos.toByteArray();
    }
}


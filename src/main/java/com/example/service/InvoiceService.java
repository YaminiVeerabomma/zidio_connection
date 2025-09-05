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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // ✅ Generate Invoice by Subscription Id
    public InvoiceDTO generateInvoice(Long subscriptionId, String userEmail, String paymentMethod) {
        SubscriptionPlan sub = subscriptionPlanRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        String invoiceNumber = "ZIDIO-" + UUID.randomUUID();
        String invoiceURL = "/api/invoices/download/" + subscriptionId;

        Invoice invoice = new Invoice();
        invoice.setUserEmail(userEmail);
        invoice.setServiceType("Subscription Plan");
        invoice.setAmount(sub.getPrice());
        invoice.setPaymentMethod(paymentMethod);
        invoice.setStatus("PAID");
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDownloadURL(invoiceURL);
        invoice.setSubscriptionPlan(sub);

        invoiceRepository.save(invoice);

        return mapToDTO(invoice);
    }

    // ✅ Save invoice directly from DTO
    public InvoiceDTO saveInvoice(InvoiceDTO dto) {
        SubscriptionPlan sub = null;
        if (dto.getSubscriptionPlanId() != null) {
            sub = subscriptionPlanRepository.findById(dto.getSubscriptionPlanId())
                    .orElseThrow(() -> new RuntimeException("Subscription not found"));
        }

        Invoice invoice = new Invoice();
        invoice.setUserEmail(dto.getUserEmail());
        invoice.setServiceType(dto.getServiceType());
        invoice.setAmount(dto.getAmount());
        invoice.setPaymentMethod(dto.getPaymentMethod());
        invoice.setStatus(dto.getStatus());
        invoice.setInvoiceNumber(dto.getInvoiceNumber() != null ? dto.getInvoiceNumber() : "ZIDIO-" + UUID.randomUUID());
        invoice.setInvoiceDownloadURL(dto.getInvoiceDownloadURL());
        invoice.setSubscriptionPlan(sub);

        invoiceRepository.save(invoice);

        return mapToDTO(invoice);
    }

    // ✅ Fetch all invoices
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Fetch invoice by ID
    public InvoiceDTO getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    // ✅ Delete invoice
    public boolean deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            return false;
        }
        invoiceRepository.deleteById(id);
        return true;
    }

    // ✅ Download invoice as PDF
    public byte[] downloadInvoicePdf(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return createPDF(invoice);
    }

    // ✅ Mapper: Entity → DTO
    private InvoiceDTO mapToDTO(Invoice invoice) {
        SubscriptionPlan sub = invoice.getSubscriptionPlan();

        return new InvoiceDTO(
                invoice.getId(),
                invoice.getUserEmail(),
                invoice.getServiceType(),
                invoice.getAmount(),
                invoice.getPaymentMethod(),
                invoice.getStatus(),
                invoice.getPurchaseDate(),
                invoice.getInvoiceNumber(),
                invoice.getInvoiceDownloadURL(),
                sub != null ? sub.getId() : null,
                sub != null ? sub.getName() : null,
                sub != null ? sub.getPrice() : null,
                sub != null ? sub.getDurationInDays() : null
        );
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

            if (invoice.getSubscriptionPlan() != null) {
                document.add(new Paragraph("Plan Name: " + invoice.getSubscriptionPlan().getName()));
                document.add(new Paragraph("Price: ₹" + invoice.getSubscriptionPlan().getPrice()));
                document.add(new Paragraph("Duration: " + invoice.getSubscriptionPlan().getDurationInDays() + " days"));
            }

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

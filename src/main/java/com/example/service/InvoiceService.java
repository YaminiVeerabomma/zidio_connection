package com.example.service;

import com.example.DTO.InvoiceDTO;
import com.example.entity.Invoice;
import com.example.entity.SubscriptionPlan;
import com.example.repository.InvoiceRepository;
import com.example.repository.SubscriptionPlanRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // ---------------- GENERATE INVOICE ----------------
    public InvoiceDTO generateInvoice(Long subscriptionId, String userEmail, String paymentMethod) {
        log.info("📄 generateInvoice called for subscriptionId={}, userEmail={}, paymentMethod={}", 
                  subscriptionId, userEmail, paymentMethod);

        SubscriptionPlan sub = subscriptionPlanRepository.findById(subscriptionId)
                .orElseThrow(() -> {
                    log.error("❌ Subscription not found for id={}", subscriptionId);
                    return new RuntimeException("Subscription not found");
                });

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
        log.info("✅ Invoice created with invoiceNumber={} for user={}", invoiceNumber, userEmail);

        return mapToDTO(invoice);
    }

    // ---------------- SAVE INVOICE ----------------
    public InvoiceDTO saveInvoice(InvoiceDTO dto) {
        log.info("💾 saveInvoice called for DTO: {}", dto);

        SubscriptionPlan sub = null;
        if (dto.getSubscriptionPlanId() != null) {
            sub = subscriptionPlanRepository.findById(dto.getSubscriptionPlanId())
                    .orElseThrow(() -> {
                        log.error("❌ Subscription not found for id={}", dto.getSubscriptionPlanId());
                        return new RuntimeException("Subscription not found");
                    });
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
        log.info("✅ Invoice saved from DTO with invoiceNumber={}", invoice.getInvoiceNumber());

        return mapToDTO(invoice);
    }

    // ---------------- GET ALL INVOICES ----------------
    public List<InvoiceDTO> getAllInvoices() {
        log.info("📚 getAllInvoices called");

        List<InvoiceDTO> invoices = invoiceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        log.info("✅ Total invoices fetched: {}", invoices.size());
        return invoices;
    }

    // ---------------- GET INVOICE BY ID ----------------
    public InvoiceDTO getInvoiceById(Long id) {
        log.info("🔍 getInvoiceById called with id={}", id);

        return invoiceRepository.findById(id)
                .map(invoice -> {
                    log.info("✅ Invoice found with invoiceNumber={}", invoice.getInvoiceNumber());
                    return mapToDTO(invoice);
                })
                .orElseGet(() -> {
                    log.warn("❌ Invoice not found with id={}", id);
                    return null;
                });
    }

    // ---------------- DELETE INVOICE ----------------
    public boolean deleteInvoice(Long id) {
        log.info("🗑️ deleteInvoice called with id={}", id);

        if (!invoiceRepository.existsById(id)) {
            log.warn("❌ Invoice not found for deletion with id={}", id);
            return false;
        }

        invoiceRepository.deleteById(id);
        log.info("✅ Invoice deleted successfully with id={}", id);
        return true;
    }

    // ---------------- DOWNLOAD INVOICE PDF ----------------
    public byte[] downloadInvoicePdf(Long id) {
        log.info("📥 downloadInvoicePdf called for id={}", id);

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("❌ Invoice not found for PDF download with id={}", id);
                    return new RuntimeException("Invoice not found");
                });

        return createPDF(invoice);
    }

    // ---------------- MAPPER: ENTITY → DTO ----------------
    private InvoiceDTO mapToDTO(Invoice invoice) {
        log.debug("🔧 mapToDTO called for invoiceNumber={}", invoice.getInvoiceNumber());

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

    // ---------------- CREATE PDF ----------------
    private byte[] createPDF(Invoice invoice) {
        log.info("🖨️ createPDF called for invoiceNumber={}", invoice.getInvoiceNumber());

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

            log.info("✅ PDF created successfully for invoiceNumber={}", invoice.getInvoiceNumber());

        } catch (Exception e) {
            log.error("❌ Error while creating PDF for invoiceNumber={}: {}", invoice.getInvoiceNumber(), e.getMessage(), e);
        } finally {
            document.close();
        }

        return baos.toByteArray();
    }
}

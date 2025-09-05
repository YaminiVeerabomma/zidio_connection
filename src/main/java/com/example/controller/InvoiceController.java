package com.example.controller;

import com.example.DTO.InvoiceDTO;
import com.example.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
@Tag(name = "Invoice API", description = "APIs to manage invoices and generate PDFs")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // ✅ Generate invoice from subscription
    @PostMapping("/generate/{subscriptionId}")
    @Operation(summary = "Generate Invoice", description = "Generate an invoice for a given subscription ID")
    public ResponseEntity<InvoiceDTO> generateInvoice(
            @Parameter(description = "Subscription ID", example = "1") @PathVariable Long subscriptionId,
            @Parameter(description = "Customer Email", example = "user@example.com") @RequestParam String userEmail,
            @Parameter(description = "Payment Method", example = "Credit Card") @RequestParam String paymentMethod) {

        InvoiceDTO dto = invoiceService.generateInvoice(subscriptionId, userEmail, paymentMethod);
        return ResponseEntity.ok(dto);
    }

    // ✅ Save invoice directly using DTO
    @PostMapping
    @Operation(summary = "Save Invoice", description = "Create a new invoice directly from DTO")
    public ResponseEntity<InvoiceDTO> saveInvoice(@RequestBody InvoiceDTO dto) {
        return ResponseEntity.ok(invoiceService.saveInvoice(dto));
    }

    // ✅ Get all invoices
    @GetMapping
    @Operation(summary = "Get All Invoices", description = "Retrieve all invoices")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    // ✅ Get invoice by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Invoice by ID", description = "Retrieve an invoice by its ID")
    public ResponseEntity<InvoiceDTO> getInvoiceById(
            @Parameter(description = "Invoice ID", example = "1") @PathVariable Long id) {
        InvoiceDTO dto = invoiceService.getInvoiceById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // ✅ Download invoice as PDF
    @GetMapping("/download/{id}")
    @Operation(summary = "Download Invoice PDF", description = "Download an invoice in PDF format")
    public ResponseEntity<byte[]> downloadInvoicePdf(
            @Parameter(description = "Invoice ID", example = "1") @PathVariable Long id) {

        byte[] pdf = invoiceService.downloadInvoicePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // ✅ Delete invoice
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Invoice", description = "Delete an invoice by ID")
    public ResponseEntity<Void> deleteInvoice(
            @Parameter(description = "Invoice ID", example = "1") @PathVariable Long id) {

        boolean deleted = invoiceService.deleteInvoice(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

package com.rishi.billing.software.controller;

import com.rishi.billing.software.dto.InvoiceRequestDTO;
import com.rishi.billing.software.entity.Invoice;
import com.rishi.billing.software.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")   // Base URL: /invoices
public class InvoiceController {

    private final InvoiceService invoiceService;

    // Constructor injection
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Create invoice
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequestDTO requestDTO) {
        Invoice createdInvoice = invoiceService.createInvoice(requestDTO);
        return ResponseEntity.ok(createdInvoice);
    }

    // Get invoice by ID
    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable long invoiceId) {
        Invoice invoice = invoiceService.getOrderById(invoiceId);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    // Get all invoices
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoice();
        return ResponseEntity.ok(invoices);
    }
}

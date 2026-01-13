package com.rishi.billing.software.controller;
import com.rishi.billing.software.dto.InvoiceRequestDTO;
import com.rishi.billing.software.entity.Invoice;
import com.rishi.billing.software.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequestDTO requestDTO) {
        Invoice createdInvoice = invoiceService.createInvoice(requestDTO);
        return ResponseEntity.ok(createdInvoice);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable long invoiceId) {
        Invoice invoice = invoiceService.getOrderById(invoiceId);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoice();
        return ResponseEntity.ok(invoices);
    }
}

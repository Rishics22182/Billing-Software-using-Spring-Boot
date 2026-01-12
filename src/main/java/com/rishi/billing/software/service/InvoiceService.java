package com.rishi.billing.software.service;

import com.rishi.billing.software.dto.InvoiceRequestDTO;
import com.rishi.billing.software.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO);
    Invoice getOrderById(long invoiceId);
    List<Invoice> getAllInvoice();
}

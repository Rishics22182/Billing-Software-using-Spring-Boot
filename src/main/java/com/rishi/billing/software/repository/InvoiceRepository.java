package com.rishi.billing.software.repository;

import com.rishi.billing.software.dto.InvoiceRequestDTO;
import com.rishi.billing.software.entity.Invoice;
import com.rishi.billing.software.entity.InvoiceItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepository {

    private final List<Invoice> invoices = new ArrayList<>();
    private final List<InvoiceItem> items = new ArrayList<>();

    public Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = new Invoice();
        invoice.setCustomer(invoiceRequestDTO.getCustomer());

        double totalAmount = 0;
        double totalTax = 0;
        double discount = 0;

        for (InvoiceItem item : invoiceRequestDTO.getItems()) {

            item.setInvoiceId(invoice.getInvoiceId());

            double lineAmount = item.getPrice() * item.getQuantity();
            totalAmount += lineAmount;
            totalTax += item.getTaxAmount();
            discount += item.getDiscount();

            item.setTotal(lineAmount + item.getTaxAmount() - item.getDiscount());

            items.add(item);
        }

        invoice.setTotalAmount(totalAmount);
        invoice.setTotalTax(totalTax);

        invoice.setDiscout(discount);
        invoice.setFinalAmount(totalAmount + totalTax - discount);

        invoices.add(invoice);
        return invoice;
    }

    public Invoice getInvoiceById(long invoiceId) {
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId() == invoiceId) {
                return invoice;
            }
        }
        return null;
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices);
    }

    public List<InvoiceItem> getItemsByInvoiceId(long invoiceId) {
        List<InvoiceItem> result = new ArrayList<>();
        for (InvoiceItem item : items) {
            if (item.getInvoiceId() == invoiceId) {
                result.add(item);
            }
        }
        return result;
    }
}
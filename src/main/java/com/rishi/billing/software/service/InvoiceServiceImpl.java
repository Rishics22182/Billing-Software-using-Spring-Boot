package com.rishi.billing.software.service;
import com.rishi.billing.software.dto.InvoiceRequestDTO;
import com.rishi.billing.software.entity.Invoice;
import com.rishi.billing.software.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        return invoiceRepository.createInvoice(invoiceRequestDTO);
    }

    @Override
    public Invoice getOrderById(long invoiceId) {
        return invoiceRepository.getInvoiceById(invoiceId);
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.getAllInvoices();
    }

}

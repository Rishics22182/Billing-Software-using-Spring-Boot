package com.rishi.billing.software.repository;

import com.rishi.billing.software.dto.InvoiceRequestDTO;
import com.rishi.billing.software.entity.Customer;
import com.rishi.billing.software.entity.Invoice;
import com.rishi.billing.software.entity.InvoiceItem;
import com.rishi.billing.software.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class InvoiceRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProductRepository productRepository;

    public InvoiceRepository(JdbcTemplate jdbcTemplate, ProductRepository productRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = productRepository;
    }

    private final RowMapper<Invoice> invoiceRowMapper = (rs, rowNum) -> {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getLong("invoice_id"));
        invoice.setInvoiceDate(rs.getDate("invoice_date").toLocalDate());

        Customer customer = new Customer();
        customer.setId(rs.getInt("customer_id"));
        invoice.setCustomer(customer);

        invoice.setTotalAmount(rs.getDouble("total_amount"));
        invoice.setTotalTax(rs.getDouble("total_tax"));
        invoice.setDiscout(rs.getDouble("discount"));
        invoice.setFinalAmount(rs.getDouble("final_amount"));
        return invoice;
    };

    private final RowMapper<InvoiceItem> invoiceItemRowMapper = (rs, rowNum) -> {
        InvoiceItem item = new InvoiceItem();
        item.setId(rs.getLong("id"));
        item.setInvoiceId(rs.getLong("invoice_id"));

        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        item.setProduct(product);

        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));
        item.setTaxAmount(rs.getDouble("tax_amount"));
        item.setTotal(rs.getDouble("total"));
        item.setDiscount(rs.getDouble("discount"));
        return item;
    };

    public Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO) {

        double totalAmount = 0.0;
        double totalTax = 0.0;
        double totalDiscount = 0.0;

        // 1) Har item ke liye: price DB se, taxAmount/discount user se
        for (InvoiceItem item : invoiceRequestDTO.getItems()) {

            int productId = item.getProduct().getId();
            Product product = productRepository.getProductById(productId);
            if (product == null) {
                throw new IllegalArgumentException("Product not found with id: " + productId);
            }

            int qty = item.getQuantity();
            double price = product.getPrice();
            double lineAmount = price * qty;

            double taxAmount = item.getTaxAmount();      // user se
            double discountAmount = item.getDiscount();  // user se
            double lineTotal = lineAmount + taxAmount - discountAmount;

            totalAmount += lineAmount;
            totalTax += taxAmount;
            totalDiscount += discountAmount;

            item.setPrice(price);
            item.setTaxAmount(taxAmount);
            item.setDiscount(discountAmount);
            item.setTotal(lineTotal);
        }

        // 2) Invoice object + totals
        Invoice invoice = new Invoice(); // constructor se invoiceId / date set ho jayega
        invoice.setCustomer(invoiceRequestDTO.getCustomer());
        invoice.setTotalAmount(totalAmount);
        invoice.setTotalTax(totalTax);
        invoice.setDiscout(totalDiscount);
        invoice.setFinalAmount(totalAmount + totalTax - totalDiscount);

        // 3) invoice table me insert
        String invoiceSql =
                "INSERT INTO invoice (invoice_id, invoice_date, customer_id, total_amount, total_tax, discount, final_amount) " +
                        "VALUES (?,?,?,?,?,?,?)";

        jdbcTemplate.update(
                invoiceSql,
                invoice.getInvoiceId(),                     // invoice_id
                Date.valueOf(invoice.getInvoiceDate()),     // invoice_date
                invoice.getCustomer().getId(),              // customer_id
                invoice.getTotalAmount(),
                invoice.getTotalTax(),
                invoice.getDiscout(),
                invoice.getFinalAmount()
        );

        long invoiceId = invoice.getInvoiceId();

        // 4) invoice_item table me insert
        String itemSql =
                "INSERT INTO invoice_item " +
                        "(invoice_id, product_id, quantity, price, tax_amount, total, discount) " +
                        "VALUES (?,?,?,?,?,?,?)";

        for (InvoiceItem item : invoiceRequestDTO.getItems()) {
            item.setInvoiceId(invoiceId);
            jdbcTemplate.update(
                    itemSql,
                    invoiceId,
                    item.getProduct().getId(),
                    item.getQuantity(),
                    item.getPrice(),
                    item.getTaxAmount(),
                    item.getTotal(),
                    item.getDiscount()
            );
        }

        return invoice;
    }

    public Invoice getInvoiceById(long invoiceId) {
        String sql =
                "SELECT invoice_id, invoice_date, customer_id, " +
                        "       total_amount, total_tax, discount, final_amount " +
                        "FROM invoice " +
                        "WHERE invoice_id = ?";

        return jdbcTemplate.query(sql, invoiceRowMapper, invoiceId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Invoice> getAllInvoices() {
        String sql =
                "SELECT invoice_id, invoice_date, customer_id, " +
                        "       total_amount, total_tax, discount, final_amount " +
                        "FROM invoice";

        return jdbcTemplate.query(sql, invoiceRowMapper);
    }

    public List<InvoiceItem> getItemsByInvoiceId(long invoiceId) {
        String sql =
                "SELECT id, invoice_id, product_id, quantity, " +
                        "       price, tax_amount, total, discount " +
                        "FROM invoice_item " +
                        "WHERE invoice_id = ?";

        return jdbcTemplate.query(sql, invoiceItemRowMapper, invoiceId);
    }
}

package com.rishi.billing.software.entity;

import java.time.LocalDate;

public class Invoice {
    private long invoiceId;
    private LocalDate invoiceDate;
    private Customer customer;
    private double totalAmount;
    private double totalTax;
    private double discout;
    private  double finalAmount;

    public Invoice() {
        this.invoiceId = System.currentTimeMillis();
        this.invoiceDate = LocalDate.now();
    }

    public Invoice(Customer customer, double totalAmount, double totalTax, double discout, double finalAmount) {
        this.invoiceId = System.currentTimeMillis();
        this.invoiceDate = LocalDate.now();
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.totalTax = totalTax;
        this.discout = discout;
        this.finalAmount = finalAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public double getDiscout() {
        return discout;
    }

    public void setDiscout(double discout) {
        this.discout = discout;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}

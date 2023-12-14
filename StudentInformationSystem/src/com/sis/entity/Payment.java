// Payment.java
package com.sis.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private Student student;
    private BigDecimal amount;
    private LocalDate paymentDate;

    public Payment(int paymentId, Student student, BigDecimal amount, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public Student getStudent() {
        return student;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public String toString() {
        return "Payment ID: " + paymentId +
               ", Student: " + student +
               ", Amount: " + amount +
               ", Payment Date: " + paymentDate;
    }
}

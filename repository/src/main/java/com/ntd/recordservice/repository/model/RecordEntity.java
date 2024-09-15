package com.ntd.recordservice.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_record", schema = "record")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "operation_type", nullable = false)
    private String operationType;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "balance", nullable = false)
    private BigDecimal cost;
    @Column(name = "operation_result", nullable = false)
    private String operationResult;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime date;

    @PrePersist
    private void prePersist() {
        this.date = LocalDateTime.now();
    }

    public RecordEntity() {
    }

    public RecordEntity(Long id, Long userId, String operationType, BigDecimal amount, BigDecimal cost, String operationResult) {
        this.id = id;
        this.userId = userId;
        this.operationType = operationType;
        this.amount = amount;
        this.cost = cost;
        this.operationResult = operationResult;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}


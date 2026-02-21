package com.cja.inventory.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_items")
@Data
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;
    private Integer quantity = 1;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
}

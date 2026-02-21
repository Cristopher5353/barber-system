package com.cja.inventory.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
    @ManyToOne
    @JoinColumn(name = "barber_id", nullable = false)
    private User barber;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    private String paymentMethod;
    @CreationTimestamp
    private LocalDateTime saleDate;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems;
}

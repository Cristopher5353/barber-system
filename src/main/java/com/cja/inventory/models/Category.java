package com.cja.inventory.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
    @Column(nullable = false, length = 100)
    private String name;
    private Boolean active = true;
}

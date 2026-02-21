package com.cja.inventory.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "user_businesses")
@Data
public class UserBusiness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(precision = 5, scale = 2)
    private BigDecimal commissionPercentage;
    public enum Role {
        OWNER,
        BARBER
    }
}

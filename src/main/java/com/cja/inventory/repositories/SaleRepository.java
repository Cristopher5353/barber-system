package com.cja.inventory.repositories;

import com.cja.inventory.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("""
        SELECT COALESCE(SUM(s.total), 0)
        FROM Sale s
        WHERE s.business.id = :businessId
        AND DATE(s.saleDate) = CURRENT_DATE
    """)
    BigDecimal totalSalesTodayOwner(Long businessId);

    @Query("""
        SELECT COUNT(si)
        FROM SaleItem si
        WHERE si.business.id = :businessId
        AND DATE(si.sale.saleDate) = CURRENT_DATE
    """)
    Long servicesTodayOwner(Long businessId);

    @Query("""
        SELECT COUNT(DISTINCT s.client.id)
        FROM Sale s
        WHERE s.business.id = :businessId
        AND DATE(s.saleDate) = CURRENT_DATE
    """)
    Long customersTodayOwner(Long businessId);

    @Query("""
        SELECT COALESCE(SUM(s.total), 0)
        FROM Sale s
        WHERE s.business.id = :businessId
        AND s.barber.id = :barberId
        AND DATE(s.saleDate) = CURRENT_DATE
    """)
    BigDecimal totalSalesTodayBarber(Long businessId, Long barberId);

    @Query("""
        SELECT COUNT(si)
        FROM SaleItem si
        WHERE si.business.id = :businessId
        AND si.sale.barber.id = :barberId
        AND DATE(si.sale.saleDate) = CURRENT_DATE
    """)
    Long servicesTodayBarber(Long businessId, Long barberId);

    @Query("""
        SELECT COUNT(DISTINCT s.client.id)
        FROM Sale s
        WHERE s.business.id = :businessId
        AND s.barber.id = :barberId
        AND DATE(s.saleDate) = CURRENT_DATE
    """)
    Long customersTodayBarber(Long businessId, Long barberId);

    @Query("""
        SELECT
            DATE(s.saleDate),
            SUM(s.total)
        FROM Sale s
        WHERE s.business.id = :businessId
          AND s.saleDate >= :startDate
          AND s.saleDate < :endDate
        GROUP BY DATE(s.saleDate)
        ORDER BY DATE(s.saleDate)
    """)
    List<Object[]> salesLast7Days(
            @Param("businessId") Long businessId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("""
        SELECT
            u.id,
            u.name,
            COUNT(si),
            SUM(si.subtotal)
        FROM Sale s
        JOIN s.barber u
        JOIN s.saleItems si
        WHERE s.business.id = :businessId
        AND DATE(s.saleDate) = CURRENT_DATE
        GROUP BY u.id, u.name
        ORDER BY SUM(si.subtotal) DESC
    """)
    List<Object[]> barberRankingToday(Long businessId);

    @Query("""
        SELECT
            s.saleDate,
            i.name,
            si.subtotal
        FROM Sale s
        JOIN s.saleItems si
        JOIN si.item i
        WHERE s.business.id = :businessId
        AND s.barber.id = :barberId
        AND DATE(s.saleDate) = CURRENT_DATE
        ORDER BY s.saleDate DESC
    """)
    List<Object[]> recentSales(Long businessId, Long barberId);

    @Query("""
        SELECT
            u.name,
            COUNT(si),
            SUM(si.subtotal),
            ub.commissionPercentage,
            SUM(si.subtotal) * (ub.commissionPercentage / 100)
        FROM Sale s
        JOIN s.barber u
        JOIN UserBusiness ub ON ub.user.id = u.id AND ub.business.id = s.business.id
        JOIN s.saleItems si
        WHERE s.business.id = :businessId
        AND DATE(s.saleDate) = CURRENT_DATE
        GROUP BY u.name, ub.commissionPercentage
    """)
    List<Object[]> barberPaymentSummary(Long businessId);
}

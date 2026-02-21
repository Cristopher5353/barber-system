package com.cja.inventory.services.dashboard;

import com.cja.inventory.dto.dashboard.*;
import com.cja.inventory.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {
    private final SaleRepository saleRepository;

    public TodayStatsDto getTodayStatsOwner(Long businessId) {
        BigDecimal totalSales =
                saleRepository.totalSalesTodayOwner(businessId);
        Long services =
                saleRepository.servicesTodayOwner(businessId);
        Long customers =
                saleRepository.customersTodayOwner(businessId);

        return new TodayStatsDto(totalSales, services, customers);
    }

    public TodayStatsDto getTodayStatsBarber(Long businessId, Long barberId) {
        BigDecimal totalSales =
                saleRepository.totalSalesTodayBarber(businessId, barberId);
        Long services =
                saleRepository.servicesTodayBarber(businessId, barberId);
        Long customers =
                saleRepository.customersTodayBarber(businessId, barberId);

        return new TodayStatsDto(totalSales, services, customers);
    }

    public List<DailySalesDto> getSalesLast7Days(Long businessId) {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate sunday = monday.plusDays(7);
        LocalDateTime startDate = monday.atStartOfDay();
        LocalDateTime endDate = sunday.atStartOfDay();

        List<Object[]> raw =
                saleRepository.salesLast7Days(businessId, startDate, endDate);

        Map<LocalDate, BigDecimal> totalsByDate = new LinkedHashMap<>();

        for (int i = 6; i >= 0; i--) {
            LocalDate d = today.minusDays(i);
            totalsByDate.put(d, BigDecimal.ZERO);
        }

        for (Object[] row : raw) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            BigDecimal total = (BigDecimal) row[1];
            totalsByDate.put(date, total);
        }

        return totalsByDate.entrySet()
                .stream()
                .map(e -> new DailySalesDto(e.getKey(), e.getValue()))
                .toList();
    }

    public List<BarberRankingDto> getBarberRankingToday(Long businessId) {

        List<Object[]> raw =
                saleRepository.barberRankingToday(businessId);

        return raw.stream()
                .map(r -> new BarberRankingDto(
                        (Long) r[0],
                        (String) r[1],
                        (Long) r[2],
                        (BigDecimal) r[3]
                ))
                .toList();
    }

    public List<RecentSaleDto> getRecentSales(Long businessId, Long barberId) {

        List<Object[]> raw =
                saleRepository.recentSales(businessId, barberId);

        return raw.stream()
                .map(r -> new RecentSaleDto(
                        (LocalDateTime) r[0],
                        (String) r[1],
                        (BigDecimal) r[2]
                ))
                .toList();
    }

    public List<BarberPaymentSummaryDto> getBarberPaymentSummary(Long businessId) {

        List<Object[]> raw =
                saleRepository.barberPaymentSummary(businessId);

        return raw.stream()
                .map(r -> new BarberPaymentSummaryDto(
                        (String) r[0],
                        (Long) r[1],
                        (BigDecimal) r[2],
                        (BigDecimal) r[3],
                        (BigDecimal) r[4]
                ))
                .toList();
    }
}


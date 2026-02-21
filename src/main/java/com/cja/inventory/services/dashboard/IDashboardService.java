package com.cja.inventory.services.dashboard;

import com.cja.inventory.dto.dashboard.*;
import java.util.List;

public interface IDashboardService {
    TodayStatsDto getTodayStatsOwner(Long businessId);
    TodayStatsDto getTodayStatsBarber(Long businessId, Long barberId);
    List<DailySalesDto> getSalesLast7Days(Long businessId);
    List<BarberRankingDto> getBarberRankingToday(Long businessId);
    List<RecentSaleDto> getRecentSales(Long businessId, Long barberId);
    List<BarberPaymentSummaryDto> getBarberPaymentSummary(Long businessId);
}


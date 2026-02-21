package com.cja.inventory.controllers;

import com.cja.inventory.dto.dashboard.TodayStatsDto;
import com.cja.inventory.models.UserBusiness;
import com.cja.inventory.repositories.SaleRepository;
import com.cja.inventory.repositories.UserBusinessRepository;
import com.cja.inventory.security.MyUserDetails;
import com.cja.inventory.services.dashboard.DashboardService;
import com.cja.inventory.services.dashboard.IDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final IDashboardService iDashboardService;

    private MyUserDetails user() {
        return (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping("/summary")
    public TodayStatsDto summary() {
        Long businessId = user().getBusinessId();
        Long userId = user().getUserId();
        boolean isOwner = user().getRole() == UserBusiness.Role.OWNER;

        if (isOwner) {
            return iDashboardService.getTodayStatsOwner(businessId);
        } else {
            return iDashboardService.getTodayStatsBarber(businessId, userId);
        }
    }

    @GetMapping("/owner")
    @PreAuthorize("hasRole('OWNER')")
    public Map<String, Object> ownerDashboard() {
        Long businessId = user().getBusinessId();
        Map<String, Object> response = new HashMap<>();

        response.put("salesLast7DaysChart",
                iDashboardService.getSalesLast7Days(businessId));

        response.put("barberRanking",
                iDashboardService.getBarberRankingToday(businessId));

        response.put("barberPaymentSummary",
                iDashboardService.getBarberPaymentSummary(businessId));

        return response;
    }

    @GetMapping("/barber")
    @PreAuthorize("hasRole('BARBER')")
    public Map<String, Object> barberDashboard() {

        Long businessId = user().getBusinessId();
        Long barberId = user().getUserId();

        return Map.of(
                "recentSales",
                iDashboardService.getRecentSales(businessId, barberId)
        );
    }
}


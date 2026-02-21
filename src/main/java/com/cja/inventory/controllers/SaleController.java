package com.cja.inventory.controllers;

import com.cja.inventory.dto.sales.StoreSaleDto;
import com.cja.inventory.security.MyUserDetails;
import com.cja.inventory.services.clients.IClientService;
import com.cja.inventory.services.items.IItemService;
import com.cja.inventory.services.sale.ISaleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/ventas")
@RequiredArgsConstructor
public class SaleController {
    private final ISaleService iSaleService;
    private final IClientService iClientService;
    private final IItemService iItemService;

    private MyUserDetails user() {
        return (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping("/crear")
    public String create(Model model, HttpServletRequest request) {
        Long businessId = user().getBusinessId();
        model.addAttribute("customers", iClientService.getAllClients(businessId));
        model.addAttribute("products", iItemService.getAllItems(businessId));
        return "views/sale/store";
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody StoreSaleDto storeSaleDto) {
        iSaleService.save(storeSaleDto);
        return ResponseEntity.ok().body("enviado");
    }
}

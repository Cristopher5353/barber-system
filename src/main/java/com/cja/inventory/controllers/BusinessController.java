package com.cja.inventory.controllers;

import com.cja.inventory.services.business.IBusinessService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {
    private final IBusinessService iBusinessService;

    @GetMapping("/select-business")
    public String selectBusiness(Model model, Authentication auth) {
        iBusinessService.selectBusiness(model, auth);
        return "selectBusiness";
    }

    @PostMapping("/select-business")
    public String confirmBusiness(
            @RequestParam Long businessId,
            Authentication authentication,
            HttpServletRequest request) {
        iBusinessService.confirmBusiness(businessId, authentication, request);
        return "redirect:/dashboard";
    }
}

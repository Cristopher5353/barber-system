package com.cja.inventory.services.business;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public interface IBusinessService {
    void selectBusiness(Model model, Authentication auth);
    void confirmBusiness(
            @RequestParam Long businessId,
            Authentication authentication,
            HttpServletRequest request);
}

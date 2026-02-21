package com.cja.inventory.services.business;

import com.cja.inventory.models.UserBusiness;
import com.cja.inventory.repositories.UserBusinessRepository;
import com.cja.inventory.security.LoginUserDetails;
import com.cja.inventory.security.MyUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements IBusinessService {
    private final UserBusinessRepository userBusinessRepository;

    @Override
    public void selectBusiness(Model model, Authentication auth) {
        LoginUserDetails userDetails =
                (LoginUserDetails) auth.getPrincipal();

        List<UserBusiness> businesses =
                userBusinessRepository.findByUser_Id(userDetails.getUserId())
                        .stream().filter(userBusiness -> userBusiness.getBusiness().getActive())
                        .toList();

        model.addAttribute("businesses", businesses);
    }

    @Override
    public void confirmBusiness(Long businessId, Authentication authentication, HttpServletRequest request) {
        LoginUserDetails loginUser =
                (LoginUserDetails) authentication.getPrincipal();

        UserBusiness userBusiness = userBusinessRepository
                .findByUser_IdAndBusiness_Id(loginUser.getUserId(), businessId)
                .orElseThrow(() -> new AccessDeniedException("No access to business"));

        MyUserDetails finalUser = new MyUserDetails(
                loginUser.getUser(),
                businessId,
                userBusiness.getRole()
        );

        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(
                        finalUser,
                        null,
                        finalUser.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(newAuth);
        request.getSession().setAttribute("BUSINESS_ID", businessId);
    }
}

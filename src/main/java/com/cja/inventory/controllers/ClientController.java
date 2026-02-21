package com.cja.inventory.controllers;

import com.cja.inventory.dto.clients.ClientDto;
import com.cja.inventory.security.MyUserDetails;
import com.cja.inventory.services.clients.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService iClientService;

    private MyUserDetails user() {
        return (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        Long businessId = user().getBusinessId();
        return iClientService.getAllClients(businessId);
    }
}

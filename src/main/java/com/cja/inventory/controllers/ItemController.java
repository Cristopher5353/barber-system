package com.cja.inventory.controllers;

import com.cja.inventory.dto.Items.ItemDto;
import com.cja.inventory.security.MyUserDetails;
import com.cja.inventory.services.items.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final IItemService iItemService;

    private MyUserDetails user() {
        return (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping
    public List<ItemDto> getAllItems() {
        Long businessId = user().getBusinessId();
        return iItemService.getAllItems(businessId);
    }
}

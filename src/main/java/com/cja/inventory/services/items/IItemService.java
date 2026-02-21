package com.cja.inventory.services.items;

import com.cja.inventory.dto.Items.ItemDto;
import java.util.List;

public interface IItemService {
    List<ItemDto> getAllItems(Long businessId);
}

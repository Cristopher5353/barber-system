package com.cja.inventory.services.items;

import com.cja.inventory.dto.Items.ItemDto;
import com.cja.inventory.mappers.items.ItemMapper;
import com.cja.inventory.models.Item;
import com.cja.inventory.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IItemServiceImpl implements IItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getAllItems(Long businessId) {
        List<Item> services =
                itemRepository.findByBusinessIdAndStockIsNullAndActiveTrue(businessId);

        List<Item> products =
                itemRepository.findByBusinessIdAndStockIsNotNullAndActiveTrue(businessId);

        return Stream.concat(services.stream(), products.stream())
                .map(itemMapper::entityToItemDto)
                .toList();
    }
}

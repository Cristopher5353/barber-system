package com.cja.inventory.mappers.items;

import com.cja.inventory.dto.Items.ItemDto;
import com.cja.inventory.models.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {
    ItemDto entityToItemDto(Item client);
}

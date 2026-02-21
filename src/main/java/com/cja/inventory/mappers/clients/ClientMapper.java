package com.cja.inventory.mappers.clients;

import com.cja.inventory.dto.clients.ClientDto;
import com.cja.inventory.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    ClientDto entityToClientDto(Client client);
}

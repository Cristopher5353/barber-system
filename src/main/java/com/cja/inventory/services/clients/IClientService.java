package com.cja.inventory.services.clients;

import com.cja.inventory.dto.clients.ClientDto;
import java.util.List;

public interface IClientService {
    List<ClientDto> getAllClients(Long businessId);
}

package com.cja.inventory.services.clients;

import com.cja.inventory.dto.clients.ClientDto;
import com.cja.inventory.mappers.clients.ClientMapper;
import com.cja.inventory.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService{
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAllClients(Long businessId) {
        return clientRepository.findByBusiness_Id(businessId)
                .stream()
                .map(clientMapper::entityToClientDto)
                .toList();
    }
}

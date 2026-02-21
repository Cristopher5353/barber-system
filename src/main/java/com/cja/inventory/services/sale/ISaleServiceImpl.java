package com.cja.inventory.services.sale;

import com.cja.inventory.dto.sales.SaleItemDto;
import com.cja.inventory.dto.sales.StoreSaleDto;
import com.cja.inventory.models.*;
import com.cja.inventory.repositories.*;
import com.cja.inventory.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ISaleServiceImpl implements ISaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ItemRepository itemRepository;
    private final ClientRepository clientRepository;
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    private MyUserDetails user() {
        return (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    /*@Override
    @Transactional
    public void save(StoreSaleDto storeSaleDto) {
        Long businessId = user().getBusinessId();
        Long barberId = user().getUserId();

        Client client = null;
        if(storeSaleDto.getClientId() != null) {
            client = clientRepository.findById(storeSaleDto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontado"));
        }

        Business business = businessRepository.findById(businessId).orElseThrow(() -> new RuntimeException("Negocio no existe"));
        User barber = userRepository.findById(barberId).orElseThrow(() -> new RuntimeException("Barbero no existe"));

        Sale sale = new Sale();
        sale.setBusiness(business);
        sale.setBarber(barber);
        sale.setClient(client);
        sale.setPaymentMethod(storeSaleDto.getPaymentMethod());
        sale.setSaleDate(LocalDateTime.now());

        BigDecimal total = storeSaleDto.getItems().stream()
                .map(storeSale -> {
                    Item item = itemRepository.findById(storeSale.getItemId())
                            .orElseThrow(() -> new RuntimeException("Item no existe"));

                    return item.getPrice().multiply(BigDecimal.valueOf(storeSale.getQuantity()));
                }).reduce(BigDecimal.ZERO, BigDecimal::add);

        sale.setTotal(total);
        saleRepository.save(sale);

        List<SaleItem> details = storeSaleDto.getItems().stream()
                .map(req -> {
                    Item item = itemRepository.findById(req.getItemId())
                            .orElseThrow(() -> new RuntimeException("Item no existe"));

                    int quantity = req.getQuantity() == null || req.getQuantity() <= 0
                            ? 1
                            : req.getQuantity();

                    BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(quantity));

                    SaleItem d = new SaleItem();
                    d.setSale(sale);
                    d.setBusiness(business);
                    d.setItem(item);
                    d.setUnitPrice(item.getPrice());
                    d.setQuantity(quantity);
                    d.setSubtotal(subtotal);

                    return d;
                }).toList();
        saleItemRepository.saveAll(details);
    }*/
    @Override
    @Transactional
    public void save(StoreSaleDto storeSaleDto) {
        Long businessId = user().getBusinessId();
        Long barberId = user().getUserId();

        Client client = null;
        if (storeSaleDto.getClientId() != null) {
            client = clientRepository.findById(storeSaleDto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        }

        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Negocio no existe"));

        User barber = userRepository.findById(barberId)
                .orElseThrow(() -> new RuntimeException("Barbero no existe"));

        Sale sale = new Sale();
        sale.setBusiness(business);
        sale.setBarber(barber);
        sale.setClient(client);
        sale.setPaymentMethod(storeSaleDto.getPaymentMethod());
        sale.setSaleDate(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        List<SaleItem> details = new ArrayList<>();

        for (SaleItemDto req : storeSaleDto.getItems()) {
            Item item = itemRepository.findById(req.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item no existe"));

            int quantity = (req.getQuantity() == null || req.getQuantity() <= 0)
                    ? 1
                    : req.getQuantity();

            if (item.getStock() != null && item.getStock() < quantity) {
                throw new RuntimeException("Stock insuficiente para el item: " + item.getName());
            }

            BigDecimal subtotal = item.getPrice()
                    .multiply(BigDecimal.valueOf(quantity));

            total = total.add(subtotal);

            if (item.getStock() != null) {
                item.setStock(item.getStock() - quantity);
                itemRepository.save(item);
            }

            SaleItem detail = new SaleItem();
            detail.setSale(sale);
            detail.setBusiness(business);
            detail.setItem(item);
            detail.setUnitPrice(item.getPrice());
            detail.setQuantity(quantity);
            detail.setSubtotal(subtotal);

            details.add(detail);
        }

        sale.setTotal(total);
        saleRepository.save(sale);
        saleItemRepository.saveAll(details);
    }

}
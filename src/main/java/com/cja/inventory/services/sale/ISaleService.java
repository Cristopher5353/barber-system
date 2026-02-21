package com.cja.inventory.services.sale;

import com.cja.inventory.dto.customers.ListCustomerDto;
import com.cja.inventory.dto.customers.StoreCustomerDto;
import com.cja.inventory.dto.products.ListProductDto;
import com.cja.inventory.dto.sales.ListSaleDto;
import com.cja.inventory.dto.sales.StoreSaleDto;
import com.itextpdf.text.DocumentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ISaleService {
    void save(StoreSaleDto storeSaleDto);
}

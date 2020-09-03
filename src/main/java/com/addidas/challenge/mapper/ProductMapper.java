package com.addidas.challenge.mapper;

import com.addidas.challenge.dto.ProductDto;
import com.addidas.challenge.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public interface ProductMapper {
    ProductDto mapToProductDto(Product source);

    Iterable<ProductDto> mapToProductsDto(Iterable<Product> source);
}

package com.bigzara.clothingdeliveries.business;

import com.bigzara.clothingdeliveries.model.api.ProductDetail;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ClothingDeliveriesApiMapper {

  ProductDetail mapProduct(ProductDetailDto productDetailDto);

}

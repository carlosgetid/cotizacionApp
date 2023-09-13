package com.bigzara.clothingdeliveries.dao.mapper;

import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import com.bigzara.clothingdeliveries.model.thirdparty.zaracoreservices.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDetailMapper {

  ProductDetailDto mapProductDetail(ProductDetail productDetail);
}

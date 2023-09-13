package com.bigzara.clothingdeliveries.model.business;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(staticName = "of")
public class ProductDetailCacheDto {

  private List<ProductDetailDto> productDetailDtos;
}

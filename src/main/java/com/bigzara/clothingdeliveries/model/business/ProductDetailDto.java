package com.bigzara.clothingdeliveries.model.business;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailDto {

  private String id;

  private String name;

  private BigDecimal price;

  private Boolean availability;
}

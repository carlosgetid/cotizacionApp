package com.bigzara.clothingdeliveries.dao;

import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import io.reactivex.Observable;

public interface ProductDetailDao {
  Observable<ProductDetailDto> getProductDetail(String productId);
}

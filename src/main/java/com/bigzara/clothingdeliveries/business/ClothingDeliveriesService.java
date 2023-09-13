package com.bigzara.clothingdeliveries.business;

import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import io.reactivex.Observable;

public interface ClothingDeliveriesService {

  Observable<String> listSimilarProductIds(String productId);

  Observable<ProductDetailDto> getProductDetail(String productId);

  Observable<ProductDetailDto> showSimilarProducts(String productId);

}

package com.bigzara.clothingdeliveries.dao;

import com.bigzara.clothingdeliveries.model.business.ProductDetailCacheDto;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ProductCacheDao {

  Completable cacheProducts(String productId, ProductDetailCacheDto productDetailCacheDto);

  Observable<ProductDetailDto> getCachedProducts(String productId);
}

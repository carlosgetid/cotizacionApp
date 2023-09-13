package com.bigzara.clothingdeliveries.dao.impl;

import com.bigzara.clothingdeliveries.dao.ProductCacheDao;
import com.bigzara.clothingdeliveries.model.business.ProductDetailCacheDto;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import com.bigzara.clothingdeliveries.util.constants.LogConstants;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ProductCacheDaoImpl implements ProductCacheDao {

  private ConcurrentHashMap<String, ProductDetailCacheDto> cache;

  @Override
  public Completable cacheProducts(String productId, ProductDetailCacheDto productDetailCacheDto) {
    return Completable.defer(() -> {
      cache.put(productId, productDetailCacheDto);
      return Completable.complete();
    });
  }

  @Override
  public Observable<ProductDetailDto> getCachedProducts(String productId) {
    return Optional.ofNullable(cache.get(productId))
        .map(productDetailCacheDto -> Observable.fromIterable(
            productDetailCacheDto.getProductDetailDtos()))
        .orElse(Observable.empty())
        .doOnError(throwable -> log.error(LogConstants.PRODUCT_CACHE_DAO_ERROR));
  }
}

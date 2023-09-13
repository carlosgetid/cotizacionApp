package com.bigzara.clothingdeliveries.business.impl;

import com.bigzara.clothingdeliveries.business.ClothingDeliveriesService;
import com.bigzara.clothingdeliveries.dao.ProductCacheDao;
import com.bigzara.clothingdeliveries.dao.ProductDetailDao;
import com.bigzara.clothingdeliveries.dao.SimilarProductIdDao;
import com.bigzara.clothingdeliveries.exception.CustomExceptionHandler;
import com.bigzara.clothingdeliveries.model.business.ProductDetailCacheDto;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import com.bigzara.clothingdeliveries.util.constants.LogConstants;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ClothingDeliveriesServiceImpl implements ClothingDeliveriesService {

  private SimilarProductIdDao similarProductIdDao;

  private ProductDetailDao productDetailDao;

  private ProductCacheDao productCacheDao;

  private CustomExceptionHandler customExceptionHandler;

  @Override
  public Observable<String> listSimilarProductIds(String productId) {
    return similarProductIdDao.listSimilarProductIds(productId)
        .doOnError(customExceptionHandler :: handleProductException);
  }

  @Override
  public Observable<ProductDetailDto> getProductDetail(String productId) {
    return productDetailDao.getProductDetail(productId)
        .doOnError(throwable -> log.error(LogConstants.PRODUCT_DETAIL_ID_SERVICE_ERROR,
            throwable.getMessage()));
  }

  @Override
  public Observable<ProductDetailDto> showSimilarProducts(String productId) {
    return productCacheDao.getCachedProducts(productId)
        .switchIfEmpty(listSimilarProductIds(productId)
            .doOnSubscribe(
                disposable -> log.info(LogConstants.SIMILAR_PRODUCT_ID_SERVICE_START, productId))
            .flatMap(this :: getProductDetail)
            .toList()
            .flatMapObservable(
                productDetailDtos -> productCacheDao.cacheProducts(productId, ProductDetailCacheDto.of(productDetailDtos))
                    .andThen(Observable.fromIterable(productDetailDtos)))
            .doOnSubscribe(
                disposable -> log.info(LogConstants.PRODUCT_DETAIL_SERVICE_START, productId)));
  }
}

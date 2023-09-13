package com.bigzara.clothingdeliveries.dao.impl;

import com.bigzara.clothingdeliveries.dao.ProductDetailDao;
import com.bigzara.clothingdeliveries.dao.mapper.ProductDetailMapper;
import com.bigzara.clothingdeliveries.dao.proxy.DefaultApi;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import com.bigzara.clothingdeliveries.util.constants.LogConstants;
import com.bigzara.clothingdeliveries.util.constants.ServiceConstants;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ProductDetailDaoImpl implements ProductDetailDao {

  private DefaultApi defaultApi;

  private ProductDetailMapper productDetailMapper;

  @Override
  public Observable<ProductDetailDto> getProductDetail(String productId) {
    return defaultApi.getProductProductId(productId)
        .timeout(ServiceConstants.TIMEOUT, TimeUnit.SECONDS)
        .retry(ServiceConstants.TIMES, throwable -> (throwable instanceof SocketTimeoutException)
            || (throwable instanceof TimeoutException))
        .subscribeOn(Schedulers.io())
        .doOnSubscribe(disposable -> log.info(LogConstants.PRODUCT_DETAIL_DAO_START, productId))
        .doOnComplete(() -> log.info(LogConstants.PRODUCT_DETAIL_DAO_COMPLETE, productId))
        .doOnError(throwable -> log.error(LogConstants.PRODUCT_DETAIL_DAO_ERROR,
            throwable.getMessage()))
        .onErrorResumeNext(Observable.empty())
        .map(productDetailMapper :: mapProductDetail);
  }
}

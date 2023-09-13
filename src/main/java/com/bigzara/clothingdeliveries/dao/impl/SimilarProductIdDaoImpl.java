package com.bigzara.clothingdeliveries.dao.impl;

import com.bigzara.clothingdeliveries.dao.SimilarProductIdDao;
import com.bigzara.clothingdeliveries.dao.proxy.DefaultApi;
import com.bigzara.clothingdeliveries.util.constants.LogConstants;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class SimilarProductIdDaoImpl implements SimilarProductIdDao {

  private DefaultApi defaultApi;

  @Override
  public Observable<String> listSimilarProductIds(String productId) {
    return defaultApi.getProductSimilarids(productId)
        .subscribeOn(Schedulers.io())
        .doOnSubscribe(disposable -> log.info(LogConstants.SIMILAR_PRODUCT_ID_DAO_START, productId))
        .doOnComplete(() -> log.info(LogConstants.SIMILAR_PRODUCT_ID_DAO_COMPLETE, productId))
        .doOnError(throwable -> log.error(LogConstants.SIMILAR_PRODUCT_ID_DAO_ERROR,
            throwable.getMessage()))
        .flatMap(Observable :: fromIterable);
  }
}

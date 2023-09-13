package com.bigzara.clothingdeliveries.dao;

import io.reactivex.Observable;

public interface SimilarProductIdDao {
  Observable<String> listSimilarProductIds(String productId);
}

package com.bigzara.clothingdeliveries.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LogConstants {
  public static final String SIMILAR_PRODUCT_ID_DAO_START = "Getting similar product ids. productId :{}";
  public static final String SIMILAR_PRODUCT_ID_DAO_COMPLETE =
      "Similar product ids were retrieved. productId: {}";
  public static final String SIMILAR_PRODUCT_ID_DAO_ERROR =
      "Error getting similar product Ids. Error message: {}";

  public static final String PRODUCT_DETAIL_DAO_START = "Getting product detail. productId: {}";
  public static final String PRODUCT_DETAIL_DAO_COMPLETE =
      "Product detail was retrieved. productId: {}";
  public static final String PRODUCT_DETAIL_DAO_ERROR =
      "Error getting product detail. Error message: {}";


  public static final String SIMILAR_PRODUCT_ID_SERVICE_START =
      "Calling similar product id service. productId: {}";
  public static final String SIMILAR_PRODUCT_ID_SERVICE_ERROR =
      "Error processing similar product. Error message: {}";
  public static final String PRODUCT_DETAIL_SERVICE_START = "Calling product detail service. productId: {}";

  public static final String PRODUCT_DETAIL_ID_SERVICE_ERROR =
      "Error processing product detail. Error message: {}";


  public static final String CLOTHING_DELIVERIES_CONTROLLER_START = "Endpoint GET /product/{}/similar - Starting";
  public static final String CLOTHING_DELIVERIES_CONTROLLER_COMPLETE = "Endpoint GET /product/{}/similar - Completed";
  public static final String CLOTHING_DELIVERIES_CONTROLLER_ERROR =
      "Endpoint GET /product/{}/similar - Error. Message: {}";

  public static final String PRODUCT_CACHE_DAO_ERROR =
      "Failed to recover from cache";
}

package com.bigzara.expose.web;

import com.bigzara.clothingdeliveries.business.ClothingDeliveriesApiMapper;
import com.bigzara.clothingdeliveries.business.ClothingDeliveriesService;
import com.bigzara.clothingdeliveries.model.api.DetalleData;
import com.bigzara.clothingdeliveries.model.api.ProductDetail;
import com.bigzara.clothingdeliveries.util.constants.LogConstants;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@Component
@Slf4j
@AllArgsConstructor
public class ClothingDeliveriesApiImpl implements ProductApiDelegate, ObtenerdataApiDelegate{

  private ClothingDeliveriesService clothingDeliveriesService;

  private ClothingDeliveriesApiMapper clothingDeliveriesApiMapper;

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return ProductApiDelegate.super.getRequest();
  }

  @Override
  public Maybe<ResponseEntity<Set<ProductDetail>>> getProductSimilar(String productId) {
    return Maybe.fromSingle(clothingDeliveriesService.showSimilarProducts(productId)
            .map(clothingDeliveriesApiMapper :: mapProduct)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(
                disposable -> log.info(LogConstants.CLOTHING_DELIVERIES_CONTROLLER_START, productId))
            .doOnComplete(
                () -> log.info(LogConstants.CLOTHING_DELIVERIES_CONTROLLER_COMPLETE, productId))
            .doOnError(
                throwable -> log.error(LogConstants.CLOTHING_DELIVERIES_CONTROLLER_ERROR, productId,
                    throwable.getMessage()))
            .toList()
            .map(Set :: copyOf))
        .map(ResponseEntity :: ok);
  }

  @Override
  public Maybe<ResponseEntity<Set<DetalleData>>> obtenerData(String entrada) {
    DetalleData detalleData = new DetalleData();
    detalleData.setAvailability(true);
    detalleData.setId("1234");
    detalleData.setName("Nombre");
    detalleData.setPrice(new BigDecimal("123.00"));
    return Maybe.fromSingle(Observable.just(detalleData)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(
                disposable -> log.info(LogConstants.CLOTHING_DELIVERIES_CONTROLLER_START, "productId"))
            .doOnComplete(
                () -> log.info(LogConstants.CLOTHING_DELIVERIES_CONTROLLER_COMPLETE, "productId"))
            .doOnError(
                throwable -> log.error(LogConstants.CLOTHING_DELIVERIES_CONTROLLER_ERROR, "productId",
                    throwable.getMessage()))
            .toList()
            .map(Set :: copyOf))
        .map(ResponseEntity :: ok);
  }
}

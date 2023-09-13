package com.bigzara.expose.web;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.bigzara.clothingdeliveries.business.ClothingDeliveriesApiMapper;
import com.bigzara.clothingdeliveries.business.ClothingDeliveriesService;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import io.reactivex.Observable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ClothingDeliveriesApiImplTest {

  @Spy
  private ClothingDeliveriesApiMapper clothingDeliveriesApiMapper =
      Mappers.getMapper(ClothingDeliveriesApiMapper.class);
  @Mock
  private ClothingDeliveriesService clothingDeliveriesService;
  @InjectMocks
  public ClothingDeliveriesApiImpl clothingDeliveriesApiImpl;

  @DisplayName("Return successfully when get product similar")
  @Test
  void returnSuccessfullyWhenGetProductSimilar() {
    final HttpStatus expectedStatusCode = HttpStatus.OK;
    final String productId = "1";

    ProductDetailDto item = new ProductDetailDto();

    when(clothingDeliveriesService.showSimilarProducts(anyString()))
        .thenReturn(Observable.just(item));

    var test =
        clothingDeliveriesApiImpl.getProductSimilar(productId).test();

    test.awaitTerminalEvent();
    test.assertNoErrors().assertComplete();
    test.assertValue(setResponseEntity ->
        setResponseEntity.getStatusCode() == expectedStatusCode);
  }
}
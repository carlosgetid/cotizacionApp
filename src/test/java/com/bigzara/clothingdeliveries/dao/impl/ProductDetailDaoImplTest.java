package com.bigzara.clothingdeliveries.dao.impl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.bigzara.clothingdeliveries.dao.mapper.ProductDetailMapper;
import com.bigzara.clothingdeliveries.dao.proxy.DefaultApi;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import com.bigzara.clothingdeliveries.model.thirdparty.zaracoreservices.ProductDetail;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductDetailDaoImplTest {


  @Mock
  private DefaultApi defaultApi;

  @Spy
  private ProductDetailMapper productDetailMapper = Mappers.getMapper(ProductDetailMapper.class);

  @InjectMocks
  private ProductDetailDaoImpl productDetailDaoImpl;

  @DisplayName("Return successfully when get product detail")
  @Test
  void returnSuccessfullyWhenGetProductDetail() {
    final ProductDetailDto expectedProductDetailDto = new ProductDetailDto();
    expectedProductDetailDto.setId("2");
    expectedProductDetailDto.setName("Dress");
    expectedProductDetailDto.setPrice(BigDecimal.valueOf(19.99));
    expectedProductDetailDto.setAvailability(true);
    final String productId = "1";

    final ProductDetail item = new ProductDetail();
    item.setId("2");
    item.setName("Dress");
    item.setPrice(BigDecimal.valueOf(19.99));
    item.setAvailability(true);

    when(defaultApi.getProductProductId(anyString()))
        .thenReturn(Observable.just(item));

    TestObserver<ProductDetailDto> testObserver =
        productDetailDaoImpl.getProductDetail(productId).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNoErrors().assertComplete();
    testObserver.assertValue(productDetailDto ->
        productDetailDto.getId().equals(expectedProductDetailDto.getId()) &&
            productDetailDto.getName().equals(expectedProductDetailDto.getName()) &&
            productDetailDto.getPrice().equals(expectedProductDetailDto.getPrice()) &&
            productDetailDto.getAvailability().equals(expectedProductDetailDto.getAvailability())
    );
  }
}
package com.bigzara.clothingdeliveries.business.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.bigzara.clothingdeliveries.dao.ProductCacheDao;
import com.bigzara.clothingdeliveries.dao.ProductDetailDao;
import com.bigzara.clothingdeliveries.dao.SimilarProductIdDao;
import com.bigzara.clothingdeliveries.exception.CustomExceptionHandler;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ClothingDeliveriesServiceImplTest {

  @Mock
  private SimilarProductIdDao similarProductIdDao;

  @Mock
  private ProductDetailDao productDetailDao;

  @Mock
  private ProductCacheDao productCacheDao;

  @Mock
  private CustomExceptionHandler customExceptionHandler;
  @InjectMocks
  private ClothingDeliveriesServiceImpl clothingDeliveriesServiceImpl;


  @DisplayName("Return successfully when list similar product ids")
  @Test
  void returnSuccessfullyWhenListSimilarProductIds() {
    final String expectedProductId = "2";
    final String productId = "1";
    final String item = "2";

    when(similarProductIdDao.listSimilarProductIds(anyString()))
        .thenReturn(Observable.just(item));

    TestObserver<String> testObserver =
        clothingDeliveriesServiceImpl.listSimilarProductIds(productId).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNoErrors().assertComplete();
    testObserver.assertValue(actualProductId -> actualProductId.equals(expectedProductId));
  }

  @DisplayName("Return error when list similar product ids")
  @Test
  void returnErrorWhenListSimilarProductIds() {
    final String productId = "1";

    when(similarProductIdDao.listSimilarProductIds(anyString()))
        .thenReturn(Observable.error(Throwable :: new));

    when(customExceptionHandler.handleProductException(any(Throwable.class)))
        .thenReturn(ResponseEntity.badRequest().body("any error message"));

    TestObserver<String> testObserver =
        clothingDeliveriesServiceImpl.listSimilarProductIds(productId).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertError(Throwable.class);
  }

  @DisplayName("Return successfully when get product detail")
  @Test
  void returnSuccessfullyWhenGetProductDetail() {
    final ProductDetailDto expectedProductDetailDto = new ProductDetailDto();
    expectedProductDetailDto.setId("2");
    expectedProductDetailDto.setName("Dress");
    expectedProductDetailDto.setPrice(BigDecimal.valueOf(19.99));
    expectedProductDetailDto.setAvailability(true);
    final String productId = "1";

    final ProductDetailDto item = new ProductDetailDto();
    item.setId("2");
    item.setName("Dress");
    item.setPrice(BigDecimal.valueOf(19.99));
    item.setAvailability(true);

    when(productDetailDao.getProductDetail(anyString()))
        .thenReturn(Observable.just(item));

    TestObserver<ProductDetailDto> testObserver =
        clothingDeliveriesServiceImpl.getProductDetail(productId).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNoErrors().assertComplete();
    testObserver.assertValue(productDetailDto ->
        productDetailDto.getId().equals(expectedProductDetailDto.getId()) &&
        productDetailDto.getName().equals(expectedProductDetailDto.getName()) &&
        productDetailDto.getPrice().equals(expectedProductDetailDto.getPrice()) &&
        productDetailDto.getAvailability().equals(expectedProductDetailDto.getAvailability())
    );
  }

  @DisplayName("Return successfully when show similar products")
  @Test
  void returnSuccessfullyWhenShowSimilarProducts() {
    final ProductDetailDto expectedProductDetailDto = new ProductDetailDto();
    expectedProductDetailDto.setId("2");
    expectedProductDetailDto.setName("Dress");
    expectedProductDetailDto.setPrice(BigDecimal.valueOf(19.99));
    expectedProductDetailDto.setAvailability(true);
    final String productId = "1";

    final ProductDetailDto item = new ProductDetailDto();
    item.setId("2");
    item.setName("Dress");
    item.setPrice(BigDecimal.valueOf(19.99));
    item.setAvailability(true);

    when(productCacheDao.getCachedProducts(productId))
        .thenReturn(Observable.just(item));

    when(similarProductIdDao.listSimilarProductIds(anyString()))
        .thenReturn(Observable.just("2"));

    TestObserver<ProductDetailDto> testObserver =
        clothingDeliveriesServiceImpl.showSimilarProducts(productId).test();

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
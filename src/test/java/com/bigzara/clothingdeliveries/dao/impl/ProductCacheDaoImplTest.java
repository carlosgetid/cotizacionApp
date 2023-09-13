package com.bigzara.clothingdeliveries.dao.impl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.bigzara.clothingdeliveries.model.business.ProductDetailCacheDto;
import com.bigzara.clothingdeliveries.model.business.ProductDetailDto;
import io.reactivex.observers.TestObserver;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductCacheDaoImplTest {

  @InjectMocks
  private ProductCacheDaoImpl productCacheDaoImpl;

  @Mock
  private ConcurrentHashMap<String, ProductDetailCacheDto> cache;

  @DisplayName("Return successfully when cache products")
  @Test
  void returnSuccessfullyWhenCacheProducts() {
    final String productId = "1";
    ProductDetailCacheDto productDetailCacheDto =
        ProductDetailCacheDto.of(List.of(new ProductDetailDto()));
    TestObserver<Void> testObserver =
        productCacheDaoImpl.cacheProducts(productId, productDetailCacheDto).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNoErrors().assertComplete();
  }

  @DisplayName("Return successfully when get cached products")
  @Test
  void returnSuccessfullyWhenGetCachedProducts() {
    final String productId = "1";
    final ProductDetailDto expectedProductDetailDto = new ProductDetailDto();
    expectedProductDetailDto.setId("2");
    expectedProductDetailDto.setName("Dress");
    expectedProductDetailDto.setPrice(BigDecimal.valueOf(19.99));
    expectedProductDetailDto.setAvailability(true);

    ProductDetailCacheDto productDetailCacheDto = new ProductDetailCacheDto();
    ProductDetailDto itemCache = new ProductDetailDto();
    itemCache.setId("2");
    itemCache.setName("Dress");
    itemCache.setPrice(BigDecimal.valueOf(19.99));
    itemCache.setAvailability(true);
    productDetailCacheDto.setProductDetailDtos(List.of(itemCache));

    when(cache.get(anyString()))
        .thenReturn(productDetailCacheDto);

    TestObserver<ProductDetailDto> testObserver =
        productCacheDaoImpl.getCachedProducts(productId).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNoErrors().assertComplete();

    testObserver.assertValue(productDetailDto ->
        productDetailDto.getId().equals(expectedProductDetailDto.getId()) &&
            productDetailDto.getName().equals(expectedProductDetailDto.getName()) &&
            productDetailDto.getPrice().equals(expectedProductDetailDto.getPrice()) &&
            productDetailDto.getAvailability().equals(expectedProductDetailDto.getAvailability()));
  }
}
package com.bigzara.clothingdeliveries.dao.impl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.bigzara.clothingdeliveries.dao.mapper.ProductDetailMapper;
import com.bigzara.clothingdeliveries.dao.proxy.DefaultApi;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SimilarProductIdDaoImplTest {

  @Mock
  private DefaultApi defaultApi;

  @Spy
  private ProductDetailMapper productDetailMapper = Mappers.getMapper(ProductDetailMapper.class);

  @InjectMocks
  private SimilarProductIdDaoImpl similarProductIdDaoImpl;

  @DisplayName("Return successfully when list similar product ids")
  @Test
  void returnSuccessfullyWhenListSimilarProductIds() {
    final String expectedProductId = "1";
    final String productId = "1";

    when(defaultApi.getProductSimilarids(anyString()))
        .thenReturn(Observable.just(Set.of(expectedProductId)));

    TestObserver<String> testObserver =
        similarProductIdDaoImpl.listSimilarProductIds(productId).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertNoErrors().assertComplete();
    testObserver.assertValue(actualProductId -> actualProductId.equals(expectedProductId));
  }
}
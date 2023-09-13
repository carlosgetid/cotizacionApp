package com.bigzara.clothingdeliveries.config;

import com.bigzara.clothingdeliveries.dao.ApiClient;
import com.bigzara.clothingdeliveries.dao.proxy.DefaultApi;
import com.bigzara.clothingdeliveries.model.business.ProductDetailCacheDto;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public DefaultApi defaultApi() {
    return apiClient().createService(DefaultApi.class);
  }

  @Bean
  public ApiClient apiClient(){
    return new ApiClient();
  }

  @Bean
  public ConcurrentMap<String, ProductDetailCacheDto> cache(){
    return new ConcurrentHashMap<>();
  }
}

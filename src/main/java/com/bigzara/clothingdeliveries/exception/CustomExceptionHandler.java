package com.bigzara.clothingdeliveries.exception;

import com.bigzara.clothingdeliveries.util.constants.LogConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleProductException(Throwable throwable) {
    log.error(LogConstants.SIMILAR_PRODUCT_ID_SERVICE_ERROR,
        throwable.getMessage());
    ApiException apiException =
        new ApiException(HttpStatus.BAD_REQUEST, "No information available");
    return new ResponseEntity<>(apiException.getMessage(), apiException.getStatus());
  }

}

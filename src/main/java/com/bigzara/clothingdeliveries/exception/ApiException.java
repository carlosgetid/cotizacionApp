package com.bigzara.clothingdeliveries.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {

  private HttpStatus status;

  private String message;

}

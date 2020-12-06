package gal.andres.vacaloura.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler({ IllegalArgumentException.class })
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "resource not found");
    return new ResponseEntity<Object>(
        apiError, new HttpHeaders(), apiError.getStatus());
  }

}

package dev.abhi.productcatalog.exceptions;


import dev.abhi.productcatalog.dtos.NotFoundExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiException apiError =
                new ApiException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMessageNotReadableException(HttpMessageNotReadableException ex){
       String error = ex.getMessage() ;
       ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST,ex.getLocalizedMessage(),error);
       return new ResponseEntity<>(apiException,apiException.getStatus());
    }


    /*
    Commented the below exception because we have used @ResponseStatus annotation
    in the exception class :
     */
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<NotFoundExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//
//        return new ResponseEntity<>(new NotFoundExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND) ;
//    }
}

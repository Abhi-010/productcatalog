package dev.abhi.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundExceptionDto {
    private HttpStatus httpStatus ;
    private String errorMessage ;

    public NotFoundExceptionDto(HttpStatus httpStatus , String errorMessage){
        this.httpStatus = httpStatus ;
        this.errorMessage = errorMessage ;
    }
}

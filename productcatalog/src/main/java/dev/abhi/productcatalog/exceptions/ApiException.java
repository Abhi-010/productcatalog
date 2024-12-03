package dev.abhi.productcatalog.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiException {

    private HttpStatus status;
    private String message;
    private String errors;

    public ApiException(HttpStatus status, String message, String errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

}
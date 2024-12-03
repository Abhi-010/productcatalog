package dev.abhi.productcatalog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArgumentMisMatchException extends Exception {
    private String message ;
    public ArgumentMisMatchException(String message){
        super(message);
    }
}

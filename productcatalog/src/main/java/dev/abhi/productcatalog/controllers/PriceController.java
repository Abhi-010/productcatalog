package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.PriceRequestDto;
import dev.abhi.productcatalog.services.priceservices.PriceServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceServices priceServices ;
    public PriceController(PriceServices priceServices){
        this.priceServices = priceServices ;
    }

    @PostMapping
    public String  createPrice(@RequestBody PriceRequestDto priceRequestDto){
        //priceServices.createPrice(priceRequestDto.getCurrency(),priceRequestDto.getPrice());
        return " successfully created";
    }

}

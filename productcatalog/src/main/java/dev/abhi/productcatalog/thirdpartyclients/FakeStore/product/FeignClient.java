package dev.abhi.productcatalog.thirdpartyclients.FakeStore.product;


import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@org.springframework.cloud.openfeign.FeignClient(name="fake-store",url = "${fakeStore.url}")
public interface FeignClient {
    @GetMapping("/{id}")
    FakeStoreProductDto getProductById(@PathVariable Long id);
}

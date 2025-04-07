package dev.abhi.productcatalog.security;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JwtObject {
    private String email ;
    private Long userId ;
    private Date createdAt ;
    private Date expireAt;
    private List<Role> roles = new ArrayList<>();
}

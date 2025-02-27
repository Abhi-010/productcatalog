package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.models.Category;
import dev.abhi.productcatalog.services.categoryservices.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerWebMvcTest {
    // test : get All categories
    @MockBean
    private CategoryService categoryService ; 
    
    @Autowired
    private MockMvc mockMvc ;
    
    @Test
    void getAllCategoriestest() throws Exception {
        
        // Arrange : 
        CategoryDto categoryDto = new CategoryDto() ; 
        categoryDto.setName("Electronics");
        List<CategoryDto> categoryList = new ArrayList<>() ; 
        categoryList.add(categoryDto);
                
        when(categoryService.getAllCategories()).thenReturn(categoryList);
        
        // Act :
        
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) ;
        
        // Assert :

        // .andExpect(jsonPath("$.id",is("61c84078-da0a-4b8e-9f9f-59e0b43a6c05")))
    }

    @Test
    void addTwoNumber(){
        assertThat(4+3,is(7)) ;
    }

    @Test
    void testList(){
        List<Integer> result = List.of(1,2,3);
        List<Integer> expected = List.of(1,2);

        assertThat(result)
                .withFailMessage("Expected %s but found %s", expected, result)
                .isEqualTo(expected);
    }

    @Test
    public void given2Strings_whenEqual_thenCorrect() {
        String a = "foo";
        String b = "FOO";
        assertThat(a, equalToIgnoringCase(b));
    }
  
}

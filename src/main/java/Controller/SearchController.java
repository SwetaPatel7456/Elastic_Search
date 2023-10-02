package Controller;

import model.Product;
import model.SearchRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ProductElasticService;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    ProductElasticService productElasticService;

    @GetMapping("/search/products")
    public List<Product> getProducts(@RequestParam("searchKey") String searchKey) throws IOException {
         return productElasticService.getAllProductsFromElastic(searchKey);
    }
}

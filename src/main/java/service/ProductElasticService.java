package service;

import model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductElasticService {

    public List<Product> getAllProductsFromElastic(String searchKey) throws IOException;
}

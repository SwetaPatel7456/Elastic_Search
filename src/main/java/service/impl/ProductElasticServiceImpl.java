package service.impl;

import dao.ESProductDao;
import model.Product;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import service.ProductElasticService;

import java.io.IOException;
import java.util.List;

public class ProductElasticServiceImpl implements ProductElasticService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ESProductDao esProductDao;

    @Override
    public List<Product> getAllProductsFromElastic(String searchKey) throws IOException {
        //created bool query to search data in elastic
        BoolQueryBuilder boolQueryBuilder  =  createBoolQueryBuilder(searchKey);
        List<Product> products =esProductDao.executeProductSearchQuery(boolQueryBuilder);
        return products;

    }

    private BoolQueryBuilder createBoolQueryBuilder(String searchKey) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder queryBuilder = null;
        queryBuilder = QueryBuilders.termQuery(searchKey,searchKey.toLowerCase());
        boolQueryBuilder.must(queryBuilder);
        return  boolQueryBuilder;

    }
}

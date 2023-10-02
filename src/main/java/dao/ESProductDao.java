package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ESProductDao {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Value("${es.brandAliasName}")
    private String BRAND_ALIAS_NAME;

    public List<Product> executeProductSearchQuery(BoolQueryBuilder boolQueryBuilder) throws IOException {

        SearchRequest searchRequest = new SearchRequest(BRAND_ALIAS_NAME);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(20000);
        searchRequest.scroll(new TimeValue(600000));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        return fetchProductFromSearchHits(searchResponse);

    }

    private List<Product> fetchProductFromSearchHits(SearchResponse searchResponse) throws JsonProcessingException {

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Product> products = new ArrayList<>();
        for(SearchHit searchHit : searchHits){
            Product product = new ObjectMapper().readValue(searchHit.getSourceAsString(), Product.class);
            if(product!=null) {
                products.add(product);
            }

        }
        return products;





    }
}

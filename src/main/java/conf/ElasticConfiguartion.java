package conf;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfiguartion {

    @Value("${elasticsearch.host}")
    public String host;

    @Value("${elasticsearch.port}")
    public int port;

    private static final Logger logger = LoggerFactory.getLogger(ElasticConfiguartion.class);

    @Bean
    RestHighLevelClient getRestHighLevelClient(){
        RestHighLevelClient restHighLevelClient = null;

         restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host,port,"http")));
        return  restHighLevelClient;
    }

}

package com.inspire12.practice.api.config;


//import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.apache.http.client.config.RequestConfig.Builder;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
//import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

//    @Value("${spring.elasticsearch.uris}")
//    private String uris;
//    @Value("${spring.elasticsearch.ip}")
//    private List<String> ip;
//    @Value("${spring.elasticsearch.port}")
//    private int port;
//    @Value("${spring.elasticsearch.connection-timeout}")
//    private long connectionTimeout;
//    @Value("${spring.elasticsearch.socket-timeout}")
//    private long socketTimeout;

//    @Bean
//    public RestClientBuilder restClient() {
//        HttpHost[] httpHosts = ip.stream().map(host -> new HttpHost(host, port, "http"))
//                .toArray(HttpHost[]::new);
//
//        RestClientBuilder builder = RestClient.builder(httpHosts);
//
//        // Asynchronous httpclient connection delay configuration
//        builder.setRequestConfigCallback(new RequestConfigCallback() {
//            @Override
//            public Builder customizeRequestConfig(Builder requestConfigBuilder) {
//                requestConfigBuilder.setConnectTimeout((int) connectionTimeout);
//                requestConfigBuilder.setSocketTimeout((int) socketTimeout);
//                requestConfigBuilder.setConnectionRequestTimeout(500);
//
//                return requestConfigBuilder;
//            }
//        });
////        // Asynchronous httpclient connection number configuration
//        builder.setHttpClientConfigCallback(new HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                httpClientBuilder.setMaxConnTotal(100);
//                httpClientBuilder.setMaxConnPerRoute(100);
//                httpClientBuilder.setConnectionReuseStrategy((httpResponse, httpContext) -> true);
//                httpClientBuilder.setKeepAliveStrategy((httpResponse, httpContext) -> 300);
//                return httpClientBuilder;
//            }
//        });
//
//        return builder;
//    }
//
//    @Bean
//    public ElasticsearchTransport elasticsearchTransport() {
//        return new RestClientTransport(
//                restClient().build(), new JacksonJsonpMapper());
//    }
//
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        ElasticsearchClient client = new ElasticsearchClient(elasticsearchTransport());
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchAsyncClient elasticsearchAsyncClient() {
//        ElasticsearchAsyncClient client = new ElasticsearchAsyncClient(elasticsearchTransport());
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(new RestHighLevelClient(restClient()));
//    }

}

package com.spirngboot.rest.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spirngboot.rest.entity.User;

@Component
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(new HttpHost("localhost", 9200, "http")));

	@Override
	public List<User> findAllUserDetailsFromElastic() {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("usermanage");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);
		List<User> userList = new ArrayList<>();
		SearchResponse searchResponse = null;
		try {
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					userList.add(objectMapper.convertValue(map, User.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public List<User> findAllUserDataByNameFromElastic(String userName) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("usermanage");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userName.keyword", userName)));
		searchRequest.source(searchSourceBuilder);
		List<User> userList = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = null;
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					userList.add(objectMapper.convertValue(map, User.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public List<User> findAllUserDataByNameAndAddressFromElstic(String userName, String address) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("usermanage");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userName.keyword", userName))
				                                           .must(QueryBuilders.matchQuery("address", address)));
		searchRequest.source(searchSourceBuilder);
		List<User> userList = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = null;
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					userList.add(objectMapper.convertValue(map, User.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}
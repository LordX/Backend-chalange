package com.gemography.Backendchallenge.GithubAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemography.Backendchallenge.PJO.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ConsumerService {
    private LocalDate currenteDate=LocalDate.now();

    private final String ENDPOINT=String.format("https://api.github.com/search/repositories?q=%s&sort=stars&order=desc",
            currenteDate.minusDays(30).toString());
    @Autowired
    RestTemplate restTemplate;
    public List<Repository> getRepositoryList() throws IOException {
        String response=restTemplate.getForObject(ENDPOINT, String.class);
        System.out.println("date***"+ENDPOINT);
        return mapToListObjects(response);
    }

   public List<Repository> mapToListObjects(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        JsonNode internalNode = rootNode.path("items");
        System.out.println(internalNode);

        List<Repository> repositoryList = mapper.readValue( internalNode.toString(), mapper.getTypeFactory().constructCollectionType(List.class, Repository.class));
    return repositoryList;
    }
}

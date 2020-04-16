package com.gemography.Backendchallenge.Service;

import com.gemography.Backendchallenge.GithubAPI.ConsumerService;
import com.gemography.Backendchallenge.PJO.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RepositoryService {
    @Autowired
    ConsumerService consumerService;
    public List<Repository> getRepostoryList() throws IOException {
        return consumerService.getRepositoryList().stream().limit(100).collect(Collectors.toList());
    }
    public List<String> getTopTrendingRepositoriesLanguageList() throws IOException {
        return consumerService.getRepositoryList().stream().limit(100).map(Repository::getLanguage).distinct().collect(Collectors.toList());
    }

    public Map<String, Long> getCountLanguage() throws IOException {
        Map<String, Long> counted = consumerService.getRepositoryList().stream().limit(100).map(Repository::getLanguage).filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return  counted;
    }

}

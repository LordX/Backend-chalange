package com.gemography.Backendchallenge.Controller;

import com.gemography.Backendchallenge.PJO.Repository;
import com.gemography.Backendchallenge.Service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/repositories")
public class RepositoryController {
    @Autowired
    RepositoryService repositoryService;

    @GetMapping
    public ResponseEntity<List<Repository>> getRepostoryList() throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(repositoryService.getRepostoryList());
    }

    @GetMapping("/languages")
    public ResponseEntity<List<String>> getTopTrendingRepositoriesLanguageList() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(repositoryService.getTopTrendingRepositoriesLanguageList());
    }
    @GetMapping("/languages/counts")
    public ResponseEntity<Map<String,Long>> getCountLanguage() throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(repositoryService.getCountLanguage());
    }
}

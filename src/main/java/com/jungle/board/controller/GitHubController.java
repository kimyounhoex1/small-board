package com.jungle.board.controller;

import com.jungle.board.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/github")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/commits")
    public Mono<String> getCommits(
            @RequestParam String owner,
            @RequestParam String repo) {
        return gitHubService.getCommits(owner, repo);
    }

//    @GetMapping("/commits/{commit}")
//    public Mono<String> getCompareCommites(){}
}
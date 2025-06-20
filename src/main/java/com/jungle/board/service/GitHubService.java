package com.jungle.board.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GitHubService {
    private final WebClient githubWebClient;

    public GitHubService(WebClient githubWebClient) {
        this.githubWebClient = githubWebClient;
    }

    public Mono<String> getCommits(String owner, String repo) {
        return githubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/repos/kimyounhoex1/small-board/commits")
                        .build(owner, repo))
                .retrieve()
                .bodyToMono(String.class); // 또는 CommitDTO[]로 파싱 가능
    }


}

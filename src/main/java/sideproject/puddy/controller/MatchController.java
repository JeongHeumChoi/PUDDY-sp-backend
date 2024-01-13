package sideproject.puddy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sideproject.puddy.dto.match.MatchSearchResponse;
import sideproject.puddy.dto.match.RandomDogDetailListResponse;
import sideproject.puddy.dto.match.RandomDogDetailResponse;
import sideproject.puddy.service.MatchService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/random")
    public ResponseEntity<RandomDogDetailListResponse> findMatches(@RequestParam int pageNum){
        RandomDogDetailListResponse matchSearchResponse = matchService.getMatchingByDog(pageNum);
        return ResponseEntity.ok(matchSearchResponse);
    }

    @GetMapping("/random/{personId}")
    public ResponseEntity<RandomDogDetailResponse> findMatchDetailByPerson(@PathVariable Long personId){
        RandomDogDetailResponse matchSearchResponse = matchService.getShowingByMatchDetail(personId);
        return ResponseEntity.ok(matchSearchResponse);
    }

    @GetMapping("/match")
    public ResponseEntity<MatchSearchResponse> findWhoPostLike(){
        MatchSearchResponse matchSearchResponse = matchService.getPersonProfileWhoPostLike();
        return ResponseEntity.ok(matchSearchResponse);
    }
    @GetMapping("/matched")
    public ResponseEntity<MatchSearchResponse> findWhoPostMatched(){
        MatchSearchResponse matchSearchResponse = matchService.getSuccessMatched();
        return ResponseEntity.ok(matchSearchResponse);
    }

    @PostMapping("/match/{personId}")
    public ResponseEntity<String> likePost(@PathVariable Long personId) {
        matchService.likeProfile(personId);
        return ResponseEntity.ok("Like post successful");
    }
}
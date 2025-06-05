package kr.hhplus.be.server.dining.restaurant.adapter.in.web.controller.query;

import kr.hhplus.be.server.dining.restaurant.adapter.in.web.dto.condition.SearchRestaurantCondition;
import kr.hhplus.be.server.dining.restaurant.adapter.in.web.dto.response.SearchRestaurantResponse;
import kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.query.SearchRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dining/restaurant")
@RequiredArgsConstructor
public class SearchRestaurantController {
  private final SearchRestaurantUseCase searchRestaurantUseCase;

  @GetMapping
  public ResponseEntity<SearchRestaurantResponse> execute(@ModelAttribute SearchRestaurantCondition condition) {
//    PageRequest pageRequest = PageRequest.of(condition.page(), condition.display());
    return ResponseEntity.ok(SearchRestaurantResponse.search(
        searchRestaurantUseCase.execute(condition.toCriteria())));
  }
}

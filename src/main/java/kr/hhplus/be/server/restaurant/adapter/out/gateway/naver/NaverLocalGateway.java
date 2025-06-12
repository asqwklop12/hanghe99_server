package kr.hhplus.be.server.restaurant.adapter.out.gateway.naver;

import java.util.List;
import kr.hhplus.be.server.restaurant.adapter.out.client.naver.NaverResponseMapper;
import kr.hhplus.be.server.restaurant.adapter.out.client.naver.NaverSearchResponse;
import kr.hhplus.be.server.restaurant.application.port.out.client.LocalApiClient;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class NaverLocalGateway implements LocalApiClient {
  private final WebClient webClient;
  private final NaverResponseMapper naverResponseMapper;

  @Override
  public Pagination<Restaurant> execute(RestaurantCriteria criteria) {
    NaverSearchResponse response = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/local.json")
            .queryParam("query", criteria.query())
            .queryParam("display", criteria.display())
            .queryParam("start", criteria.start())
            .queryParam("sort", criteria.sort())
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(NaverSearchResponse.class)
        .block();

    List<Restaurant> restaurants = naverResponseMapper.toRestaurants(response);
    
    // 필터링된 결과로 새로운 응답 생성
    NaverSearchResponse filteredResponse = new NaverSearchResponse(
        restaurants.size(),  // total을 필터링된 결과 개수로 설정
        response.start(),
        response.display(),
        response.items()
    );

    return Pagination.of(
        naverResponseMapper.toRestaurants(filteredResponse),
        response.display(),
        response.start(),
        response.total()
    );
  }
}

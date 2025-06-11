package kr.hhplus.be.server.restaurant.adapter.out.client.naver;

import java.util.List;
import java.util.stream.Collectors;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class NaverResponseMapper {
  private static final List<String> VALID_CATEGORIES = List.of(
      "음식점", "음식", "중식", "일식", "한식", "양식"
  );

  public List<Restaurant> toRestaurants(NaverSearchResponse response) {
    List<NaverSearchItem> filteredItems = response.items().stream()
        .filter(item -> isValidCategory(item.category()))
        .collect(Collectors.toList());

    // total을 필터링된 결과 개수로 설정
    NaverSearchResponse filteredResponse = new NaverSearchResponse(
        filteredItems.size(),
        response.start(),
        response.display(),
        filteredItems
    );

    return filteredResponse.items().stream()
        .map(this::toRestaurant)
        .collect(Collectors.toList());
  }

  public Restaurant toRestaurant(NaverSearchItem item) {
    return Restaurant.builder()
        .title(item.title())
        .link(item.link())
        .address(item.address())
        .roadAddress(item.roadAddress())
        .build();
  }

  private boolean isValidCategory(String category) {
    if (category == null) {
        return false;
    }

    // 카테고리를 '>'로 분리하여 각 단계 확인
    String[] categories = category.split(">");
    for (String cat : categories) {
        if (VALID_CATEGORIES.contains(cat.trim())) {
            return true;
        }
    }
    return false;
  }
}

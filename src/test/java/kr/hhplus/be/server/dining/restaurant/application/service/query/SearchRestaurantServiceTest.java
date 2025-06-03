package kr.hhplus.be.server.dining.restaurant.application.service.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SearchRestaurantServiceTest {

  @Test
  @DisplayName("데이터가 아무것도 존재하지 않는 경우")
  void selectRestaurantTest() {
    //when
    List<Restaurant> restaurants = mock(SearchRestaurantService.class).execute(Restaurant.builder().build(), null);
    //then
    assertThat(restaurants).isEmpty();
  }
}

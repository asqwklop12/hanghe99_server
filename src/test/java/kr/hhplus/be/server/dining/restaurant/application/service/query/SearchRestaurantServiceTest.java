package kr.hhplus.be.server.dining.restaurant.application.service.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.service.query.fake.FakeSearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

class SearchRestaurantServiceTest {

  private SearchRestaurantRepository fakeSearchRestaurantRepository;
  private SearchRestaurantService searchRestaurantService;

  @BeforeEach
  void setUp() {
    //given
    fakeSearchRestaurantRepository = new FakeSearchRestaurantRepository();
    searchRestaurantService = new SearchRestaurantService(fakeSearchRestaurantRepository);
  }

  @Test
  @DisplayName("데이터가 아무것도 존재하지 않는 경우")
  void selectRestaurantTest() {
    //when
    List<Restaurant> restaurants = mock(SearchRestaurantService.class).execute(Restaurant.builder().build(), null);
    //then
    assertThat(restaurants).isEmpty();
  }

  @Test
  @DisplayName("데이터가 존재하는 경우")
  void selectSimpleRestaurantTest() {
    //when
    List<Restaurant> restaurants = searchRestaurantService.execute(Restaurant.builder().build(), null);
    //then
    assertThat(fakeSearchRestaurantRepository.execute()).isEqualTo(restaurants);
  }


  @Test
  @DisplayName("데이터를 하나만 존재하고 싶은 경우")
  void selectSimpleRestaurantPageTest() {
    //given
    PageRequest pageRequest = PageRequest.of(1, 1);
    //when
    List<Restaurant> restaurants = searchRestaurantService.execute(Restaurant.builder().build(), pageRequest);
    //then
    assertThat(restaurants).size().isEqualTo(1);
  }


}

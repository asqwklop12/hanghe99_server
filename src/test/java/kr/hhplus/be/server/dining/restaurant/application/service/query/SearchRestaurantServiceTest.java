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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    Restaurant restaurant = Restaurant.builder().build();
    //when
    List<Restaurant> restaurants = searchRestaurantService.execute(restaurant, null);
    //then
    assertThat(fakeSearchRestaurantRepository.execute(restaurant, null)).isEqualTo(restaurants);
  }


  @ParameterizedTest
  @ValueSource(ints = {1, 2})
  @DisplayName("데이터를 n개를 테스트하고 싶은 경우")
  void selectSimpleRestaurantPageTest(int size) {
    //given
    PageRequest pageRequest = PageRequest.of(0, size);
    //when
    List<Restaurant> restaurants = searchRestaurantService.execute(Restaurant.builder().build(), pageRequest);
    //then
    assertThat(restaurants).size().isEqualTo(size);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 10})
  @DisplayName("페이지가 변경이 되었을때 데이터가 변경되었는지 확인")
  void selectChangeRestaurantPageTest(int size) {
    //given
    List<Restaurant> firstData = searchRestaurantService.execute(Restaurant.builder().build(), PageRequest.of(0, size));
    List<Restaurant> secondData = searchRestaurantService.execute(Restaurant.builder().build(), PageRequest.of(1, size));

    //when&then
    assertThat(firstData).isNotEqualTo(secondData);
  }


}

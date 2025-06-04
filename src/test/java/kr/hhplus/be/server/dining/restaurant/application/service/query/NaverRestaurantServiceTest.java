package kr.hhplus.be.server.dining.restaurant.application.service.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.adapter.out.client.naver.NaverLocalApiClient;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.service.query.fake.FakeNaverLocalApiClient;
import kr.hhplus.be.server.dining.restaurant.application.service.query.fake.FakeSearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NaverRestaurantServiceTest {

  private NaverLocalApiClient fakeSearchRestaurantRepository;
  private SearchRestaurantService searchRestaurantService;

  @BeforeEach
  void setUp() {
    //given
    FakeNaverLocalApiClient fakeNaverLocalApiClient = new FakeNaverLocalApiClient();
    fakeSearchRestaurantRepository = new NaverLocalApiClient(fakeNaverLocalApiClient);
    searchRestaurantService = new SearchRestaurantService(fakeSearchRestaurantRepository);
  }

  @Test
  @DisplayName("데이터가 아무것도 존재하지 않는 경우")
  void selectRestaurantTest() {
    //when
    List<Restaurant> restaurants = mock(SearchRestaurantService.class).execute(RestaurantCriteria.builder().build());
    //then
    assertThat(restaurants).isEmpty();
  }

  @Test
  @DisplayName("데이터가 존재하는 경우")
  void selectSimpleRestaurantTest() {
    //when
    List<Restaurant> restaurants = searchRestaurantService.execute(RestaurantCriteria.builder().build());
    //then
    assertThat(fakeSearchRestaurantRepository.execute(RestaurantCriteria.builder().build())).isEqualTo(restaurants);
  }


  @ParameterizedTest
  @ValueSource(ints = {1, 2})
  @DisplayName("데이터를 n개를 테스트하고 싶은 경우")
  void selectSimpleRestaurantPageTest(int size) {
    //given
    //when
    List<Restaurant> restaurants = searchRestaurantService.execute(RestaurantCriteria.builder()
        .start(0)
        .display(size)
        .build());
    //then
    assertThat(restaurants).size().isEqualTo(size);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 10})
  @DisplayName("페이지가 변경이 되었을때 데이터가 변경되었는지 확인")
  void selectChangeRestaurantPageTest(int size) {
    //given
    List<Restaurant> firstData = searchRestaurantService.execute(
        RestaurantCriteria.builder()
            .start(0)
            .display(size)
            .build());
    List<Restaurant> secondData = searchRestaurantService.execute(RestaurantCriteria.builder()
        .start(1)
        .display(size)
        .build());

    //when&then
    assertThat(firstData).isNotEqualTo(secondData);
  }


}

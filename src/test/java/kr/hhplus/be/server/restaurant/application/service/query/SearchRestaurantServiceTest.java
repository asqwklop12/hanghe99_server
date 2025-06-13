package kr.hhplus.be.server.restaurant.application.service.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import kr.hhplus.be.server.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.restaurant.application.service.query.fake.FakeSearchRestaurantRepository;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class SearchRestaurantServiceTest {

  private SearchRestaurantRepository fakeSearchRestaurantRepository;
  private SearchRestaurantService searchRestaurantService;

  @BeforeEach
  void setUp() {
    //given
    fakeSearchRestaurantRepository = new FakeSearchRestaurantRepository();
    ApplicationEventPublisher eventPublisher = mock(ApplicationEventPublisher.class);
    searchRestaurantService = new SearchRestaurantService(List.of(fakeSearchRestaurantRepository), eventPublisher);
  }

  @Test
  @DisplayName("데이터가 아무것도 존재하지 않는 경우")
  void selectRestaurantTest() {
    //given
    SearchRestaurantService mockService = mock(SearchRestaurantService.class);
    when(mockService.execute(RestaurantCriteria.builder().build()))
        .thenReturn(Pagination.of(List.of(), 0, 10, 0L));

    //when
    Pagination<Restaurant> restaurants = mockService.execute(RestaurantCriteria.builder().build());

    //then
    assertThat(restaurants.getContent()).isEmpty();
  }

  @Test
  @DisplayName("데이터가 존재하는 경우")
  void selectSimpleRestaurantTest() {
    //when
    Pagination<Restaurant> restaurants = searchRestaurantService.execute(RestaurantCriteria.builder().build());
    Pagination<Restaurant> expected = fakeSearchRestaurantRepository.execute(RestaurantCriteria.builder().build());

    //then
    assertThat(restaurants.getContent()).isEqualTo(expected.getContent());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2})
  @DisplayName("데이터를 n개를 테스트하고 싶은 경우")
  void selectSimpleRestaurantPageTest(int size) {
    //when
    Pagination<Restaurant> restaurants = searchRestaurantService.execute(RestaurantCriteria.builder()
        .start(0)
        .display(size)
        .build());

    //then
    assertThat(restaurants.getContent()).hasSize(size);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 10})
  @DisplayName("페이지가 변경이 되었을때 데이터가 변경되었는지 확인")
  void selectChangeRestaurantPageTest(int size) {
    //given
    Pagination<Restaurant> firstData = searchRestaurantService.execute(
        RestaurantCriteria.builder()
            .start(0)
            .display(size)
            .build());
    Pagination<Restaurant> secondData = searchRestaurantService.execute(
        RestaurantCriteria.builder()
            .start(1)
            .display(size)
            .build());

    //when&then
    assertThat(firstData.getContent()).isNotEqualTo(secondData.getContent());
  }

}

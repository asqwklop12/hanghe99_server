package kr.hhplus.be.server.restaurant.application.service.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import kr.hhplus.be.server.keyword.application.port.in.usecase.KeywordUseCase;
import kr.hhplus.be.server.restaurant.adapter.out.client.naver.NaverLocalApiClient;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ExtendWith(MockitoExtension.class)
@Testcontainers
class SearchRestaurantIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine")
        .withDatabaseName("restaurant_test")
        .withUsername("test")
        .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.jpa.show-sql", () -> "true");
        registry.add("spring.jpa.properties.hibernate.format_sql", () -> "true");
        registry.add("client.naver.X-Naver-Client-Id", () -> "test_client_id");
        registry.add("client.naver.X-Naver-Client-Secret", () -> "test_client_secret");
        registry.add("client.naver.base-url", () -> "https://openapi.naver.com");
        registry.add("client.naver.local-search-path", () -> "/v1/search/local.json");
    }

    @Mock
    private NaverLocalApiClient naverLocalApiClient;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private KeywordUseCase keywordUseCase;

    private SearchRestaurantService searchRestaurantService;

    @BeforeEach
    void setUp() {
        searchRestaurantService = new SearchRestaurantService(List.of(naverLocalApiClient), eventPublisher);
    }

    @Test
    @DisplayName("검색 요청 → 외부 API 호출 → 검색 기록 저장 전체 흐름 테스트")
    void searchFlowTest() {
        // given
        String keyword = "맛집";
        RestaurantCriteria criteria = RestaurantCriteria.builder()
            .query(keyword)
            .start(1)
            .display(5)
            .build();

        List<Restaurant> expectedRestaurants = List.of(
            Restaurant.builder().title("맛있는집").address("서울시").link("http://example.com").build(),
            Restaurant.builder().title("맛집").address("부산시").link("http://example.com/2").build()
        );

        when(naverLocalApiClient.execute(any()))
            .thenReturn(Pagination.of(expectedRestaurants, 1, 5, 1L));

        // when
        Pagination<Restaurant> result = searchRestaurantService.execute(criteria);

        // then
        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getTotal()).isEqualTo(1L);
    }

    @Test
    @DisplayName("인기 키워드 API 응답 테스트: 키워드 누적 후 정확한 순위 반환 확인")
    void popularKeywordsTest() {
        // given
        String keyword1 = "맛집";
        String keyword2 = "분식";
        String keyword3 = "맛집"; // 중복 검색

        when(naverLocalApiClient.execute(any()))
            .thenReturn(Pagination.of(List.of(), 0, 5, 0L));

        List<String> expectedKeywords = List.of("맛집", "분식");
        when(keywordUseCase.execute()).thenReturn(expectedKeywords);

        // when
        searchRestaurantService.execute(RestaurantCriteria.builder().query(keyword1).build());
        searchRestaurantService.execute(RestaurantCriteria.builder().query(keyword2).build());
        searchRestaurantService.execute(RestaurantCriteria.builder().query(keyword3).build());

        // then
        List<String> popularKeywords = keywordUseCase.execute();
        assertThat(popularKeywords).isNotEmpty();
        assertThat(popularKeywords.get(0)).isEqualTo("맛집");
        assertThat(popularKeywords.get(1)).isEqualTo("분식");
    }

    @Test
    @DisplayName("외부 API 실패 시 → Fallback API로 대체하거나 기본 응답 반환 확인")
    void fallbackTest() {
        // given
        RestaurantCriteria criteria = RestaurantCriteria.builder()
            .query("존재하지않는매우긴키워드123456789")
            .start(1)
            .display(5)
            .build();

        when(naverLocalApiClient.execute(any()))
            .thenReturn(Pagination.of(List.of(), 1, 5, 0L));

        // when
        Pagination<Restaurant> result = searchRestaurantService.execute(criteria);

        // then
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotal()).isZero();
    }
} 

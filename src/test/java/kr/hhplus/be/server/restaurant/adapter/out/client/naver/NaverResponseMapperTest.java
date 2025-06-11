package kr.hhplus.be.server.restaurant.adapter.out.client.naver;

import kr.hhplus.be.server.restaurant.model.Restaurant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NaverResponseMapperTest {

    private final NaverResponseMapper mapper = new NaverResponseMapper();

    @Test
    @DisplayName("유효한 카테고리의 음식점만 변환된다")
    void toRestaurants_WithValidCategories() {
        // given
        NaverSearchResponse response = new NaverSearchResponse(
            8, 1, 8,
            List.of(
                createItem("맛있는 한식", "본점>한식", "서울시 강남구", "서울시 강남구 테헤란로", "http://example.com/1"),
                createItem("맛있는 중식", "프랜차이즈>중식>냉면", "서울시 서초구", "서울시 서초구 서초대로", "http://example.com/2"),
                createItem("맛있는 일식", "일식", "서울시 송파구", "서울시 송파구 올림픽로", "http://example.com/3"),
                createItem("맛있는 양식", "양식", "서울시 마포구", "서울시 마포구 홍대입구로", "http://example.com/4"),
                createItem("맛있는 음식점", "음식점", "서울시 종로구", "서울시 종로구 인사동길", "http://example.com/5"),
                createItem("맛있는 음식", "음식", "서울시 용산구", "서울시 용산구 이태원로", "http://example.com/6"),
                createItem("맛있는 카페", "카페", "서울시 중구", "서울시 중구 명동길", "http://example.com/7"),
                createItem("맛있는 술집", "술집", "서울시 강서구", "서울시 강서구 공항대로", "http://example.com/8")
            )
        );

        // when
        List<Restaurant> restaurants = mapper.toRestaurants(response);

        // then
        assertThat(restaurants).hasSize(6);
        assertThat(restaurants).extracting("title")
            .containsExactlyInAnyOrder(
                "맛있는 한식", "맛있는 중식", "맛있는 일식",
                "맛있는 양식", "맛있는 음식점", "맛있는 음식"
            );
    }

    @Test
    @DisplayName("카테고리가 null인 경우 필터링된다")
    void toRestaurants_WithNullCategory() {
        // given
        NaverSearchResponse response = new NaverSearchResponse(
            2, 1, 2,
            List.of(
                createItem("맛있는 한식", "본점>한식", "서울시 강남구", "서울시 강남구 테헤란로", "http://example.com/1"),
                createItem("카테고리 없는 음식점", null, "서울시 서초구", "서울시 서초구 서초대로", "http://example.com/2")
            )
        );

        // when
        List<Restaurant> restaurants = mapper.toRestaurants(response);

        // then
        assertThat(restaurants).hasSize(1);
        assertThat(restaurants.get(0).title()).isEqualTo("맛있는 한식");
    }

    @Test
    @DisplayName("빈 응답이 주어지면 빈 리스트를 반환한다")
    void toRestaurants_WithEmptyResponse() {
        // given
        NaverSearchResponse response = new NaverSearchResponse(0, 1, 0, List.of());

        // when
        List<Restaurant> restaurants = mapper.toRestaurants(response);

        // then
        assertThat(restaurants).isEmpty();
    }

    @Test
    @DisplayName("Restaurant 객체가 올바르게 변환된다")
    void toRestaurant_ConvertsCorrectly() {
        // given
        NaverSearchItem item = createItem(
            "맛있는 한식",
            "본점>한식",
            "서울시 강남구",
            "서울시 강남구 테헤란로",
            "http://example.com/1"
        );

        // when
        Restaurant restaurant = mapper.toRestaurant(item);

        // then
        assertThat(restaurant.title()).isEqualTo("맛있는 한식");
        assertThat(restaurant.link()).isEqualTo("http://example.com/1");
        assertThat(restaurant.address()).isEqualTo("서울시 강남구");
        assertThat(restaurant.roadAddress()).isEqualTo("서울시 강남구 테헤란로");
    }

    private NaverSearchItem createItem(String title, String category, String address, String roadAddress, String link) {
        return NaverSearchItem.builder()
            .title(title)
            .link(link)
            .category(category)
            .address(address)
            .roadAddress(roadAddress)
            .build();
    }
} 

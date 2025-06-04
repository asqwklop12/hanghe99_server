package kr.hhplus.be.server.dining.restaurant.application.service.query.fake;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.out.client.LocalApiClient;
import kr.hhplus.be.server.dining.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;

public class FakeNaverLocalApiClient implements LocalApiClient {
  private final List<Restaurant> restaurants = List.of(
      Restaurant.builder().id(1L).title("title1").address("address1").link("link1").build(),
      Restaurant.builder().id(2L).title("title2").address("address2").link("link2").build(),
      Restaurant.builder().id(3L).title("title3").address("address3").link("link3").build(),
      Restaurant.builder().id(4L).title("title4").address("address12").link("link4").build(),
      Restaurant.builder().id(5L).title("title5").address("addre55").link("link5").build(),
      Restaurant.builder().id(6L).title("title6").address("AAAA5").link("link6").build(),
      Restaurant.builder().id(7L).title("title7").address("address7").link("link7").build(),
      Restaurant.builder().id(8L).title("title8").address("address8").link("link8").build(),
      Restaurant.builder().id(9L).title("custom1").address("address10").link("link9").build(),
      Restaurant.builder().id(10L).title("custom2").address("address12").link("link10").build(),
      Restaurant.builder().id(11L).title("cusAtom1").address("address13").link("link11").build()
  );


  @Override
  public List<Restaurant> execute(RestaurantCriteria criteria) {
    int start = criteria.start();
    int end = Math.min((start + criteria.display()), restaurants.size());
    return restaurants.subList(start, end);
  }
}

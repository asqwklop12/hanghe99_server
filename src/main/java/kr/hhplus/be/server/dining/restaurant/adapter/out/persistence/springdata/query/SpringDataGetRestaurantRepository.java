package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.query;

import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query.JpaGetRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.GetRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataGetRestaurantRepository implements GetRestaurantRepository {
  private final JpaGetRestaurantRepository repository;

  @Override
  public Restaurant execute() {
    repository.findById(1L);
    return Restaurant.builder().build();
  }
}

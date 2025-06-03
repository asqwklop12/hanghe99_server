package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.query;

import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query.JpaSearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataSearchJpaRestaurantRepository implements SearchRestaurantRepository {
  private final JpaSearchRestaurantRepository repository;

  @Override
  public Restaurant execute() {
    repository.findById(1L);
    return null;
  }
}

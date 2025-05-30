package kr.hhplus.be.server.dining.adapter.out.persistence.springdata.query;

import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.query.JpaGetRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.query.GetRestaurantRepository;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataGetRestaurantRepository implements GetRestaurantRepository {
  private final JpaGetRestaurantRepository repository;

  @Override
  public Restaurant execute() {
    repository.findById(1L);
    return new Restaurant();
  }
}

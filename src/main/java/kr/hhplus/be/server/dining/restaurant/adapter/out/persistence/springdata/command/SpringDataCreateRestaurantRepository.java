package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.command;

import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.RestaurantEntity;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.command.JpaCreateRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command.CreateRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataCreateRestaurantRepository implements CreateRestaurantRepository {
  private final JpaCreateRestaurantRepository repository;

  @Override
  public Restaurant execute(Restaurant restaurant) {
    return repository.save(RestaurantEntity.fromDomain(restaurant)).toDomain();
  }
}

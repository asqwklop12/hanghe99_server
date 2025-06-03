package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.query;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.RestaurantEntity;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query.JpaSearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataSearchJpaRestaurantRepository implements SearchRestaurantRepository {
  private final JpaSearchRestaurantRepository repository;

  @Override
  public List<Restaurant> execute(Restaurant restaurant, Pageable pageable) {
    Page<RestaurantEntity> entities = repository.findAll(pageable);
    return entities.get().map(RestaurantEntity::toDomain).toList();
  }
}

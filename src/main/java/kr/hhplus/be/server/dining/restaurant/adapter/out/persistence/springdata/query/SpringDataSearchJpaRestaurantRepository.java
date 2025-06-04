package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.query;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.RestaurantEntity;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query.JpaSearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataSearchJpaRestaurantRepository implements SearchRestaurantRepository {
  private final JpaSearchRestaurantRepository repository;

  @Override
  public List<Restaurant> execute(RestaurantCriteria criteria) {
    Page<RestaurantEntity> entities = repository.findAll(PageRequest.of(criteria.start(), criteria.display()));
    return entities.get().map(RestaurantEntity::toDomain).toList();
  }
}

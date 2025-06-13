package kr.hhplus.be.server.restaurant.adapter.out.persistence.springdata.query;

import kr.hhplus.be.server.restaurant.adapter.out.persistence.entity.RestaurantEntity;
import kr.hhplus.be.server.restaurant.adapter.out.persistence.jpa.query.JpaSearchRestaurantRepository;
import kr.hhplus.be.server.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
public class SpringDataSearchJpaRestaurantRepository implements SearchRestaurantRepository {
  private final JpaSearchRestaurantRepository repository;

  @Override
  public Pagination<Restaurant> execute(RestaurantCriteria criteria) {
    Page<RestaurantEntity> entities = repository.findAll(PageRequest.of(criteria.start(), criteria.display()));


    return Pagination.of(entities.get().map(RestaurantEntity::toDomain).toList(),
        entities.getTotalPages(),
        entities.getSize(),
        entities.getTotalElements()
        ) ;
  }
}

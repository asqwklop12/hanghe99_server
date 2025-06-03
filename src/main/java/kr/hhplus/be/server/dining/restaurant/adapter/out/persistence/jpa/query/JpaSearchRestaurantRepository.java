package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query;

import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSearchRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}

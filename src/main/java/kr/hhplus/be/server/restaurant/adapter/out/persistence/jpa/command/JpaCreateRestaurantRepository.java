package kr.hhplus.be.server.restaurant.adapter.out.persistence.jpa.command;

import kr.hhplus.be.server.restaurant.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCreateRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}

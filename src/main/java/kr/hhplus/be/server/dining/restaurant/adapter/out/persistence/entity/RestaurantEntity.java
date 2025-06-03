package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;

@Entity
public class RestaurantEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public static RestaurantEntity fromDomain(Restaurant restaurant) {
    return new RestaurantEntity();
  }

  public Restaurant toDomain() {
    return new Restaurant();
  }
}

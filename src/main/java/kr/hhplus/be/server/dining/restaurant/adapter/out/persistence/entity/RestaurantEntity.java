package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String link;
  private String description;
  private String telephone;
  private String address;
  private String roadAddress;
  private String mapx;
  private String mapy;

  public static RestaurantEntity fromDomain(Restaurant restaurant) {
    return new RestaurantEntity();
  }

  public Restaurant toDomain() {
    return null;
  }
}

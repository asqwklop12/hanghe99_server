package kr.hhplus.be.server.restaurant.application.port.out.repository.command;

import kr.hhplus.be.server.restaurant.model.Restaurant;

public interface CreateRestaurantRepository {

  Restaurant execute(Restaurant restaurant);
}

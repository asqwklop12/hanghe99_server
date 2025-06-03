package kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command;

import kr.hhplus.be.server.dining.restaurant.model.Restaurant;

public interface CreateRestaurantRepository {

  Restaurant execute(Restaurant restaurant);
}

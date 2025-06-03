package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.command;

import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.command.JpaDeleteRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command.DeleteRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataDeleteRestaurantRepository implements DeleteRestaurantRepository {
  private final JpaDeleteRestaurantRepository repository;

  @Override
  public void execute() {

  }
}

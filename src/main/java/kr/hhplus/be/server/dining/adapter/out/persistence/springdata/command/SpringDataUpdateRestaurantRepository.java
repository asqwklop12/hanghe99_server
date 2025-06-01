package kr.hhplus.be.server.dining.adapter.out.persistence.springdata.command;

import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.command.JpaUpdateRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.command.UpdateRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataUpdateRestaurantRepository implements UpdateRestaurantRepository {
  private final JpaUpdateRestaurantRepository repository;

  @Override
  public void execute() {

  }
}

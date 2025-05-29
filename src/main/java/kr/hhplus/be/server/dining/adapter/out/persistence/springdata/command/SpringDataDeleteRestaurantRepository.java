package kr.hhplus.be.server.dining.adapter.out.persistence.springdata.command;

import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.command.JpaDeleteRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.command.DeleteRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataDeleteRestaurantRepository implements DeleteRestaurantRepository {
  private final JpaDeleteRestaurantRepository repository;

  public SpringDataDeleteRestaurantRepository(JpaDeleteRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute() {

  }
}

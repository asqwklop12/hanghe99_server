package kr.hhplus.be.server.restaurant.application.service.command;

import kr.hhplus.be.server.restaurant.application.port.in.usecase.command.CreateRestaurantUseCase;
import kr.hhplus.be.server.restaurant.application.port.out.repository.command.CreateRestaurantRepository;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRestaurantService implements CreateRestaurantUseCase {
  private final CreateRestaurantRepository createRestaurantPort;

  @Override
  public void execute() {
    createRestaurantPort.execute(Restaurant.builder().build());
  }
}

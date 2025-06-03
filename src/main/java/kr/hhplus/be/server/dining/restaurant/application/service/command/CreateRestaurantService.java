package kr.hhplus.be.server.dining.restaurant.application.service.command;

import kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.command.CreateRestaurantUseCase;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command.CreateRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRestaurantService implements CreateRestaurantUseCase {
  private final CreateRestaurantRepository createRestaurantPort;

  @Override
  public void execute() {
    createRestaurantPort.execute(new Restaurant());
  }
}

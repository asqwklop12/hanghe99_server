package kr.hhplus.be.server.dining.restaurant.application.service.command;

import kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.command.DeleteRestaurantUseCase;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command.DeleteRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRestaurantService implements DeleteRestaurantUseCase {
  private final DeleteRestaurantRepository deleteRestaurantPort;

  @Override
  public void execute() {

  }
}

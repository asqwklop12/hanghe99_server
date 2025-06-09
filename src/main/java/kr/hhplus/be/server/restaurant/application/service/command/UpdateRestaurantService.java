package kr.hhplus.be.server.restaurant.application.service.command;

import kr.hhplus.be.server.restaurant.application.port.in.usecase.command.UpdateRestaurantUseCase;
import kr.hhplus.be.server.restaurant.application.port.out.repository.command.UpdateRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRestaurantService implements UpdateRestaurantUseCase {
  private final UpdateRestaurantRepository updateRestaurantPort;

  @Override
  public void execute() {

  }
}

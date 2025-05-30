package kr.hhplus.be.server.dining.application.service.command;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.command.UpdateRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.command.UpdateRestaurantRepository;
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

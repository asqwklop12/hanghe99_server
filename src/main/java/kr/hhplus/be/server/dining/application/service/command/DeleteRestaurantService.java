package kr.hhplus.be.server.dining.application.service.command;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.command.DeleteRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.command.DeleteRestaurantRepository;
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

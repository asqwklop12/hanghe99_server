package kr.hhplus.be.server.dining.application.service.query;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.query.GetRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.query.GetRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRestaurantService implements GetRestaurantUseCase {
  private final GetRestaurantRepository getRestaurantPort;

  @Override
  public void execute() {

  }
}

package kr.hhplus.be.server.dining.application.service.query;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.query.SearchRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.query.SearchRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchRestaurantService implements SearchRestaurantUseCase {
  private final SearchRestaurantRepository searchRestaurantPort;

  @Override
  public void execute() {

  }
}

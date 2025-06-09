package kr.hhplus.be.server.restaurant.adapter.in.web.controller.query;

import kr.hhplus.be.server.restaurant.application.port.in.usecase.query.GetRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dining/restaurant")
@RequiredArgsConstructor
public class GetRestaurantController {
  private final GetRestaurantUseCase getRestaurantUseCase;

  public void execute() {
    getRestaurantUseCase.execute();
  }
}

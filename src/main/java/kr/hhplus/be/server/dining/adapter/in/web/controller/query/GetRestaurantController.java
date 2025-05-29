package kr.hhplus.be.server.dining.adapter.in.web.controller.query;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.query.GetRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dining/restaurant")
public class GetRestaurantController {
  private final GetRestaurantUseCase getRestaurantUseCase;

  public GetRestaurantController(GetRestaurantUseCase getRestaurantUseCase) {
    this.getRestaurantUseCase = getRestaurantUseCase;
  }

  public void execute() {
    getRestaurantUseCase.execute();
  }
}

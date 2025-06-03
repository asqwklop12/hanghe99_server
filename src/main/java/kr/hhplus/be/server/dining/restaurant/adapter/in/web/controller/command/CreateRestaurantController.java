package kr.hhplus.be.server.dining.restaurant.adapter.in.web.controller.command;

import kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.command.CreateRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dining/restaurant")
@RequiredArgsConstructor
public class CreateRestaurantController {
  private final CreateRestaurantUseCase createRestaurantUseCase;

  public void execute() {
    createRestaurantUseCase.execute();
  }
}

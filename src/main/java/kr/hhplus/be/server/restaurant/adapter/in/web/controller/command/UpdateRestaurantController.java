package kr.hhplus.be.server.restaurant.adapter.in.web.controller.command;

import kr.hhplus.be.server.restaurant.application.port.in.usecase.command.UpdateRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dining/restaurant")
@RequiredArgsConstructor
public class UpdateRestaurantController {
  private final UpdateRestaurantUseCase updateRestaurantUseCase;

  public void execute() {
    updateRestaurantUseCase.execute();
  }
}

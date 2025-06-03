package kr.hhplus.be.server.dining.restaurant.adapter.in.web.controller.command;

import kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.command.DeleteRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dining/restaurant")
@RequiredArgsConstructor
public class DeleteRestaurantController {
  private final DeleteRestaurantUseCase deleteRestaurantUseCase;

  public void execute() {
    deleteRestaurantUseCase.execute();
  }
}

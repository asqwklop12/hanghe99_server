package kr.hhplus.be.server.keyword.adapter.in.web.controller;

import java.util.List;
import kr.hhplus.be.server.keyword.application.port.in.usecase.KeywordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dining/restaurant/keyword")
@RequiredArgsConstructor
public class KeywordController {
  private final KeywordUseCase keywordUseCase;


  @GetMapping
  public List<String> execute() {
    return keywordUseCase.execute();
  }

}

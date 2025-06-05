package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.memory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.memory.fake.FakeInMemoryTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InMemorySearchKeywordAdapterTest {
  private Map<String, Integer> keywordCounts;
  private InMemorySearchKeywordAdapter adapter;

  @BeforeEach
  void setUp() {
    keywordCounts = new FakeInMemoryTemplate();
    adapter = new InMemorySearchKeywordAdapter(keywordCounts);
  }

  @Test
  @DisplayName("키워드를 한번만 입력했을 경우")
  void saveAndSearchKeyword() {
    // given
    String keyword = "맛집";

    // when
    adapter.saveKeyword(keyword);
    List<String> keywords = adapter.searchKeyword();

    // then
    assertThat(keywords).contains(keyword);
  }

  @Test
  @DisplayName("서로 다른 키워드를 입력했을경우")
  void saveAndDifferenceSearchKeyword() {
    // given
    String keyword = "맛집";
    String keyword2 = "맛집2";

    // when
    adapter.saveKeyword(keyword);
    adapter.saveKeyword(keyword2);

    List<String> keywords = adapter.searchKeyword();
    // then
    assertThat(keywords.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("키워드 랭킹 확인 -> 키워드가 많이 입력된 순서대로 조회가 되어야 한다.")
  void rankSearchKeyword() {
    // given
    String keyword = "맛집";
    String keyword2 = "맛집2";

    // when
    adapter.saveKeyword(keyword);
    adapter.saveKeyword(keyword2);
    adapter.saveKeyword(keyword2);
    adapter.saveKeyword(keyword2);

    List<String> keywords = adapter.searchKeyword();
    // then
    assertThat(keywords.get(0)).isEqualTo(keyword2);
  }

  @Test
  @DisplayName("11개 이상의 키워드를 입력했을 경우")
  void saveOver10SearchKeyword() {
    // given
    String keyword = "맛집";
    String keyword2 = "맛집2";
    String keyword3 = "맛집3";
    String keyword4 = "맛집4";
    String keyword5 = "맛집5";
    String keyword6 = "맛집6";
    String keyword7 = "맛집7";
    String keyword8 = "맛집8";
    String keyword9 = "맛집9";
    String keyword10 = "맛집10";
    String keyword11 = "맛집11";

    // when
    adapter.saveKeyword(keyword);
    adapter.saveKeyword(keyword2);
    adapter.saveKeyword(keyword3);
    adapter.saveKeyword(keyword4);
    adapter.saveKeyword(keyword5);
    adapter.saveKeyword(keyword6);
    adapter.saveKeyword(keyword7);
    adapter.saveKeyword(keyword8);
    adapter.saveKeyword(keyword9);
    adapter.saveKeyword(keyword10);
    adapter.saveKeyword(keyword11);

    List<String> keywords = adapter.searchKeyword();
    // then
    assertThat(keywords.size()).isEqualTo(10);
  }

}

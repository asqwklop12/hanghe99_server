package kr.hhplus.be.server.keyword.persistence.redis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kr.hhplus.be.server.keyword.adapter.out.persistence.redis.RedisSearchKeywordAdapter;
import kr.hhplus.be.server.keyword.persistence.redis.fake.FakeRedisTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

class RedisSearchKeywordAdapterTest {
  private RedisTemplate<String, String> redisTemplate;
  private RedisSearchKeywordAdapter redisSearchKeywordAdapter;

  @BeforeEach
  void setUp() {
    redisTemplate = new FakeRedisTemplate();
    redisSearchKeywordAdapter = new RedisSearchKeywordAdapter(redisTemplate);
  }

  @Test
  @DisplayName("키워드를 한번만 입력했을 경우")
  void saveAndSearchKeyword() {
    // given
    String keyword = "맛집";

    // when
    redisSearchKeywordAdapter.saveKeyword(keyword);
    List<String> keywords = redisSearchKeywordAdapter.searchKeyword();

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
    redisSearchKeywordAdapter.saveKeyword(keyword);
    redisSearchKeywordAdapter.saveKeyword(keyword2);

    List<String> keywords = redisSearchKeywordAdapter.searchKeyword();
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
    redisSearchKeywordAdapter.saveKeyword(keyword);
    redisSearchKeywordAdapter.saveKeyword(keyword2);
    redisSearchKeywordAdapter.saveKeyword(keyword2);
    redisSearchKeywordAdapter.saveKeyword(keyword2);

    List<String> keywords = redisSearchKeywordAdapter.searchKeyword();
    // then
    assertThat(keywords.get(0)).isEqualTo(keyword);
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
    redisSearchKeywordAdapter.saveKeyword(keyword);
    redisSearchKeywordAdapter.saveKeyword(keyword2);
    redisSearchKeywordAdapter.saveKeyword(keyword3);
    redisSearchKeywordAdapter.saveKeyword(keyword4);
    redisSearchKeywordAdapter.saveKeyword(keyword5);
    redisSearchKeywordAdapter.saveKeyword(keyword6);
    redisSearchKeywordAdapter.saveKeyword(keyword7);
    redisSearchKeywordAdapter.saveKeyword(keyword8);
    redisSearchKeywordAdapter.saveKeyword(keyword9);
    redisSearchKeywordAdapter.saveKeyword(keyword10);
    redisSearchKeywordAdapter.saveKeyword(keyword11);

    List<String> keywords = redisSearchKeywordAdapter.searchKeyword();
    // then
    assertThat(keywords.size()).isEqualTo(10);
  }

  @Test
  @DisplayName("ttl 만료 테스트")
  void timeOver() throws InterruptedException {
    //given
    String keyword = "맛집1";
    redisSearchKeywordAdapter.saveKeyword(keyword);
    redisTemplate.expire("keywords", 1, TimeUnit.SECONDS);  // keywords 키에 TTL 설정
    
    //when
    Thread.sleep(1002);  // 1초 대기
    List<String> keywords = redisSearchKeywordAdapter.searchKeyword();
    
    //then
    assertThat(keywords).isEmpty();
  }
}

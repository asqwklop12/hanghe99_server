package kr.hhplus.be.server.keyword.persistence.redis;

import kr.hhplus.be.server.keyword.adapter.out.persistence.redis.RedisSearchKeywordAdapter;
import org.junit.jupiter.api.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class RedisSearchKeywordAdapterIntegrationTest {
    @Container
    static final GenericContainer<?> redisContainer = new GenericContainer<>("redis:7-alpine")
            .withExposedPorts(6379);

    private RedisTemplate<String, String> redisTemplate;
    private RedisSearchKeywordAdapter redisSearchKeywordAdapter;

    @BeforeEach
    void setUp() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(
                redisContainer.getHost(), redisContainer.getMappedPort(6379));
        connectionFactory.afterPropertiesSet();
        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        redisSearchKeywordAdapter = new RedisSearchKeywordAdapter(redisTemplate);
    }

    @AfterEach
    void tearDown() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    @DisplayName("실제 Redis 환경에서 동시성 저장이 안전하게 동작한다")
    void concurrentSaveKeyword_realRedis() throws InterruptedException {
        // given
        String keyword = "동시성맛집";
        int threadCount = 20;
        int repeat = 1000;
        Thread[] threads = new Thread[threadCount];
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < repeat; j++) {
                    redisSearchKeywordAdapter.saveKeyword(keyword);
                }
                latch.countDown();
            });
            threads[i].start();
        }
        latch.await();

        // then
        Double score = redisTemplate.opsForZSet().score("keywords", keyword);
        assertThat(score).isEqualTo(threadCount * repeat);
    }
} 
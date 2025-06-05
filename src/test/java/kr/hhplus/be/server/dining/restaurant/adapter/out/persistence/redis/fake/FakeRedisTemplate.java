package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.redis.fake;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.Limit;
import org.springframework.data.redis.connection.zset.Aggregate;
import org.springframework.data.redis.connection.zset.Weights;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

public class FakeRedisTemplate extends RedisTemplate<String, String> {
  private final Map<String, Map<String, Double>> zSetStore = new ConcurrentHashMap<>();
  private final Map<String, Long> ttlStore = new ConcurrentHashMap<>();

  @Override
  public Boolean expire(String key, long timeout, TimeUnit unit) {
    if (zSetStore.containsKey(key)) {
      ttlStore.put(key, System.currentTimeMillis() + unit.toMillis(timeout));
      return true;
    }
    return false;
  }

  @Override
  public Long getExpire(String key, TimeUnit unit) {
    Long expireTime = ttlStore.get(key);
    if (expireTime == null) {
      return null;
    }
    long remainingTime = expireTime - System.currentTimeMillis();
    return remainingTime > 0 ? unit.convert(remainingTime, TimeUnit.MILLISECONDS) : -2L;
  }

  @Override
  public ZSetOperations<String, String> opsForZSet() {
    return new ZSetOperations<String, String>() {
      @Override
      public Boolean add(String key, String value, double score) {
        return null;
      }

      @Override
      public Boolean addIfAbsent(String key, String value, double score) {
        return null;
      }

      @Override
      public Long add(String key, Set<TypedTuple<String>> typedTuples) {
        return 0L;
      }

      @Override
      public Long addIfAbsent(String key, Set<TypedTuple<String>> typedTuples) {
        return 0L;
      }

      @Override
      public Long remove(String key, Object... values) {
        return 0L;
      }

      @Override
      public Double incrementScore(String key, String value, double delta) {
        if (isExpired(key)) {
          return null;
        }
        Map<String, Double> scores = zSetStore.computeIfAbsent(key, k -> new ConcurrentHashMap<>());
        return scores.merge(value, delta, Double::sum);
      }

      @Override
      public String randomMember(String key) {
        return "";
      }

      @Override
      public Set<String> distinctRandomMembers(String key, long count) {
        return Set.of();
      }

      @Override
      public List<String> randomMembers(String key, long count) {
        return List.of();
      }

      @Override
      public TypedTuple<String> randomMemberWithScore(String key) {
        return null;
      }

      @Override
      public Set<TypedTuple<String>> distinctRandomMembersWithScore(String key, long count) {
        return Set.of();
      }

      @Override
      public List<TypedTuple<String>> randomMembersWithScore(String key, long count) {
        return List.of();
      }

      @Override
      public Long rank(String key, Object o) {
        return 0L;
      }

      @Override
      public Long reverseRank(String key, Object o) {
        return 0L;
      }

      @Override
      public Set<String> range(String key, long start, long end) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> rangeWithScores(String key, long start, long end) {
        return Set.of();
      }

      @Override
      public Set<String> rangeByScore(String key, double min, double max) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max) {
        return Set.of();
      }

      @Override
      public Set<String> rangeByScore(String key, double min, double max, long offset, long count) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max, long offset,
                                                            long count) {
        return Set.of();
      }

      @Override
      public Set<String> reverseRange(String key, long start, long end) {
        if (isExpired(key)) {
          return Set.of();
        }
        Map<String, Double> scores = zSetStore.getOrDefault(key, Map.of());
        return scores.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .skip(start)
            .limit(end - start + 1)
            .map(Map.Entry::getKey)
            .collect(java.util.stream.Collectors.toSet());
      }

      @Override
      public Set<TypedTuple<String>> reverseRangeWithScores(String key, long start, long end) {
        return Set.of();
      }

      @Override
      public Set<String> reverseRangeByScore(String key, double min, double max) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> reverseRangeByScoreWithScores(String key, double min, double max) {
        return Set.of();
      }

      @Override
      public Set<String> reverseRangeByScore(String key, double min, double max, long offset, long count) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> reverseRangeByScoreWithScores(String key, double min, double max, long offset,
                                                                   long count) {
        return Set.of();
      }

      @Override
      public Long count(String key, double min, double max) {
        return 0L;
      }

      @Override
      public Long lexCount(String key, Range<String> range) {
        return 0L;
      }

      @Override
      public TypedTuple<String> popMin(String key) {
        return null;
      }

      @Override
      public Set<TypedTuple<String>> popMin(String key, long count) {
        return Set.of();
      }

      @Override
      public TypedTuple<String> popMin(String key, long timeout, TimeUnit unit) {
        return null;
      }

      @Override
      public TypedTuple<String> popMax(String key) {
        return null;
      }

      @Override
      public Set<TypedTuple<String>> popMax(String key, long count) {
        return Set.of();
      }

      @Override
      public TypedTuple<String> popMax(String key, long timeout, TimeUnit unit) {
        return null;
      }

      @Override
      public Long size(String key) {
        return 0L;
      }

      @Override
      public Long zCard(String key) {
        return 0L;
      }

      @Override
      public Double score(String key, Object value) {
        if (isExpired(key)) {
          return null;
        }
        Map<String, Double> scores = zSetStore.getOrDefault(key, Map.of());
        return scores.getOrDefault(value.toString(), 0.0);
      }

      @Override
      public List<Double> score(String key, Object... o) {
        return List.of();
      }

      @Override
      public Long removeRange(String key, long start, long end) {
        return 0L;
      }

      @Override
      public Long removeRangeByLex(String key, Range<String> range) {
        return 0L;
      }

      @Override
      public Long removeRangeByScore(String key, double min, double max) {
        return 0L;
      }

      @Override
      public Set<String> difference(String key, Collection<String> otherKeys) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> differenceWithScores(String key, Collection<String> otherKeys) {
        return Set.of();
      }

      @Override
      public Long differenceAndStore(String key, Collection<String> otherKeys, String destKey) {
        return 0L;
      }

      @Override
      public Set<String> intersect(String key, Collection<String> otherKeys) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> intersectWithScores(String key, Collection<String> otherKeys) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> intersectWithScores(String key, Collection<String> otherKeys, Aggregate aggregate,
                                                         Weights weights) {
        return Set.of();
      }

      @Override
      public Long intersectAndStore(String key, String otherKey, String destKey) {
        return 0L;
      }

      @Override
      public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return 0L;
      }

      @Override
      public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey, Aggregate aggregate,
                                    Weights weights) {
        return 0L;
      }

      @Override
      public Set<String> union(String key, Collection<String> otherKeys) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> unionWithScores(String key, Collection<String> otherKeys) {
        return Set.of();
      }

      @Override
      public Set<TypedTuple<String>> unionWithScores(String key, Collection<String> otherKeys, Aggregate aggregate,
                                                     Weights weights) {
        return Set.of();
      }

      @Override
      public Long unionAndStore(String key, String otherKey, String destKey) {
        return 0L;
      }

      @Override
      public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return 0L;
      }

      @Override
      public Long unionAndStore(String key, Collection<String> otherKeys, String destKey, Aggregate aggregate,
                                Weights weights) {
        return 0L;
      }

      @Override
      public Cursor<TypedTuple<String>> scan(String key, ScanOptions options) {
        return null;
      }

      @Override
      public Set<String> rangeByLex(String key, Range<String> range, Limit limit) {
        return Set.of();
      }

      @Override
      public Set<String> reverseRangeByLex(String key, Range<String> range, Limit limit) {
        return Set.of();
      }

      @Override
      public Long rangeAndStoreByLex(String srcKey, String dstKey, Range<String> range, Limit limit) {
        return 0L;
      }

      @Override
      public Long reverseRangeAndStoreByLex(String srcKey, String dstKey, Range<String> range, Limit limit) {
        return 0L;
      }

      @Override
      public Long rangeAndStoreByScore(String srcKey, String dstKey, Range<? extends Number> range, Limit limit) {
        return 0L;
      }

      @Override
      public Long reverseRangeAndStoreByScore(String srcKey, String dstKey, Range<? extends Number> range,
                                              Limit limit) {
        return 0L;
      }

      @Override
      public RedisOperations<String, String> getOperations() {
        return null;
      }
    };
  }

  private boolean isExpired(String key) {
    Long expireTime = ttlStore.get(key);
    if (expireTime == null) {
      return false;
    }
    return System.currentTimeMillis() > expireTime;
  }
}

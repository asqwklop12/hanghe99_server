package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.memory.fake;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FakeInMemoryTemplate implements Map<String, Integer> {
    private final Map<String, Integer> store = new HashMap<>();
    @Override
    public int size() {
      return store.size();
    }

    @Override
    public boolean isEmpty() {
      return store.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
      return store.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
      return store.containsValue(value);
    }

    @Override
    public Integer get(Object key) {
      return store.get(key);
    }

    @Override
    public Integer put(String key, Integer value) {
      return store.put(key, value);
    }

    @Override
    public Integer remove(Object key) {
      return store.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Integer> m) {
      store.putAll(m);
    }

    @Override
    public void clear() {
      store.clear();
    }

    @Override
    public Set<String> keySet() {
      return store.keySet();
    }

    @Override
    public Collection<Integer> values() {
      return store.values();
    }

    @Override
    public Set<Entry<String, Integer>> entrySet() {
      return store.entrySet();
    }

}

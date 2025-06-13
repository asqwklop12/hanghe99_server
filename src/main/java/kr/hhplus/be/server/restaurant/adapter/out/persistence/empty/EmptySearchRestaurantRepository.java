package kr.hhplus.be.server.restaurant.adapter.out.persistence.empty;

import java.util.List;
import kr.hhplus.be.server.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

/**
 * 외부 API가 실패하였을때 이 클래스가 실행이 되어집니다.
 */
@Order(2)
@Repository
public class EmptySearchRestaurantRepository implements SearchRestaurantRepository {


  @Override
  public Pagination<Restaurant> execute(RestaurantCriteria criteria) {
    return Pagination.of(
        List.of(),
        criteria.start(),
        criteria.display(),
        0L
    );
  }
}

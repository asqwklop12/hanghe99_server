package kr.hhplus.be.server.restaurant.adapter.out.client.naver;

import java.util.List;

public record NaverSearchResponse(
    int total,
    int start,
    int display,
    List<NaverSearchItem> items) {

}

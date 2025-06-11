package kr.hhplus.be.server.restaurant.adapter.out.client.naver;

import lombok.Builder;

@Builder
public record NaverSearchItem(
    String title,
    String link,
    String category,
    String address,
    String roadAddress,
    String mapx,
    String mapy
) {

}

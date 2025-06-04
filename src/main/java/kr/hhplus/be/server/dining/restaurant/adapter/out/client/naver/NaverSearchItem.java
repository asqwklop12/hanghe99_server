package kr.hhplus.be.server.dining.restaurant.adapter.out.client.naver;

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

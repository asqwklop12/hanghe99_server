package kr.hhplus.be.server.restaurant.model;

public enum RestaurantCategory {
    RESTAURANT("음식점"),
    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식"),
    FOOD("음식");

    private final String description;

    RestaurantCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 
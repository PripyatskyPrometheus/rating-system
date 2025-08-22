package com.restaurant.ratingsystem.test;


import com.restaurant.ratingsystem.dto.RatingRequestDTO;
import com.restaurant.ratingsystem.dto.RestaurantRequestDTO;
import com.restaurant.ratingsystem.dto.VisitorRequestDTO;
import com.restaurant.ratingsystem.service.RatingVisitorService;
import com.restaurant.ratingsystem.service.RestaurantService;
import com.restaurant.ratingsystem.service.VisitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Data {
    private final VisitorService visitorService;
    private final RestaurantService restaurantService;
    private final RatingVisitorService ratingVisitorService;

    public Data(VisitorService visitorService, RestaurantService restaurantService, RatingVisitorService ratingVisitorService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.ratingVisitorService = ratingVisitorService;
    }

    @PostConstruct
    public void loadData() {
        VisitorRequestDTO request1 = new VisitorRequestDTO("Леонид Брежнев", 75, "Мужской");
        VisitorRequestDTO request2 = new VisitorRequestDTO("Индира Ганди", 66, "Женский");
        VisitorRequestDTO request3 = new VisitorRequestDTO("Маргарет Тэтчер",   87, "Женский");
        VisitorRequestDTO request4 = new VisitorRequestDTO(null, 21, "Мужской");

        Long visitor1 = visitorService.saveVisitor(request1).id();
        Long visitor2 = visitorService.saveVisitor(request2).id();
        Long visitor3 = visitorService.saveVisitor(request3).id();
        Long visitor4 = visitorService.saveVisitor(request4).id();

        RestaurantRequestDTO restaurantRequest1 = new RestaurantRequestDTO(
            "В гостях у Джованни", 
            "Вкуснейшая паста и превосходное вино", 
            "Итальянская", 
            2000);

        RestaurantRequestDTO restaurantRequest2 = new RestaurantRequestDTO(
            "Сытый азиатский дракон",
            "Если вы любите острый ощущения - мы вам рады", 
            "Корейская",
            1200);

        Long restaurant1 = restaurantService.saveRestaurant(restaurantRequest1).id();
        Long restaurant2 = restaurantService.saveRestaurant(restaurantRequest2).id();

        RatingRequestDTO ratingRequest1 = new RatingRequestDTO(visitor1, restaurant1, 5, "Еда вкуснейшая!");
        RatingRequestDTO ratingRequest2 = new RatingRequestDTO(visitor2, restaurant1, 4, "Отличное вино");
        RatingRequestDTO ratingRequest3 = new RatingRequestDTO(visitor3, restaurant1, 3, "Я в вохищении от этого места");
        RatingRequestDTO ratingRequest4 = new RatingRequestDTO(visitor4, restaurant2, 2, "Еда очень острая. Прямо огонь!");
        RatingRequestDTO ratingRequest5 = new RatingRequestDTO(visitor1, restaurant2, 5, "Очень рекомендую здешние роллы");
        RatingRequestDTO ratingRequest6 = new RatingRequestDTO(visitor2, restaurant1, 5, 
            "Прекрасное заведение! Еда -вкуснейшая. Персонал - чуткий и расторопный");

        ratingVisitorService.addRating(ratingRequest1);
        ratingVisitorService.addRating(ratingRequest2);
        ratingVisitorService.addRating(ratingRequest3);
        ratingVisitorService.addRating(ratingRequest4);
        ratingVisitorService.addRating(ratingRequest5);
        ratingVisitorService.addRating(ratingRequest6);

        System.out.println("Данные добавлены");
    }
}
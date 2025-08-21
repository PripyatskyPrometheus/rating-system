package com.restaurant.ratingsystem.test;

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

        Long visitor1 = visitorService.saveVisitor("Леонид Брежнев", 75, "Мужской").getId();
        Long visitor2 = visitorService.saveVisitor("Индира Ганди", 66, "Женский").getId();
        Long visitor3 = visitorService.saveVisitor("Маргарет Тэтчер", 87, "Женский").getId();
        Long visitor4 = visitorService.saveVisitor(null, 21, "Мужской").getId();

        Long restaurant1 = restaurantService.saveRestaurant("В гостях у Джованни", 
        "Вкуснейшая паста и превосходное вино", "Итальянская", 2000).getId();
        
        Long restaurant2 = restaurantService.saveRestaurant(
            "Сытый азиатский дракон", "Если вы любите острый ощущения - мы вам рады", 
            "Корейская", 1200).getId();

        ratingVisitorService.addRating(visitor1, restaurant1, 5, "Еда вкуснейшая!");
        ratingVisitorService.addRating(visitor2, restaurant1, 4, "Отличное вино");
        ratingVisitorService.addRating(visitor3, restaurant1, 3, "Я в вохищении от этого места");
        
        ratingVisitorService.addRating(visitor4, restaurant2, 2, "Еда очень острая. Прямо огонь!");
        ratingVisitorService.addRating(visitor1, restaurant2, 5, "Очень рекомендую здешние роллы");

        ratingVisitorService.addRating(visitor2, restaurant1, 5, 
        "Прекрасное заведение! Еда -вкуснейшая. Персонал - чуткий и расторопный");

        System.out.println("Данные добавлены");
    }
}
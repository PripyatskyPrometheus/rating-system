package com.restaurant.ratingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.restaurant.ratingsystem.service.RestaurantService;
import com.restaurant.ratingsystem.service.VisitorService;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class RestaurantRatingSystemApplication implements CommandLineRunner {
	private final VisitorService visitorService;
    private final RestaurantService restaurantService;

	public RestaurantRatingSystemApplication(VisitorService visitorService, RestaurantService restaurantService) {
		this.visitorService = visitorService;
    	this.restaurantService = restaurantService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantRatingSystemApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println("\nТестирование приложения");
        
        System.out.println("Посетители:");
        visitorService.getAllVisitors().forEach(v -> 
            System.out.println("	" + v.name() + " - " + v.age()));
        
        System.out.println("\nРестораны:");
        restaurantService.getAllRestaurants().forEach(r -> 
            System.out.println("	" + r.name() + ": " + r.userRating() + "/5"));

        System.out.println("Тестирование успешно завершено! Приложение работает исправно!");*/
    }
}
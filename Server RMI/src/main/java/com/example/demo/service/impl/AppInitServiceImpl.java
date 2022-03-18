package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.inter.IAppInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class AppInitServiceImpl implements IAppInitService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MealCategoryRepository mealCategoryRepository;

    @Autowired
    MealRepository mealRepository;

    @Autowired
    RateRepository rateRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    FavoriteRestaurantRepository favoriteRestaurantRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public void initUsers()
    {
        List<User> users = new ArrayList<>();
        users.add(new Client(null, "username1", "email1@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
        users.add(new Manager(null, "username2", "email2@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
        users.add(new Client(null, "username3", "email3@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
        users.add(new Manager(null, "username4", "email4@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
        for (User user : users)
        {
            userRepository.save(user);
        }
    }

//    // Second version
//    public void initUsers()
//    {
//        List<Client> clients = new ArrayList<>();
//        List<Manager> managers = new ArrayList<>();
//        clientRepository.save(new Client(null, "username1", "email1@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
//        managerRepository.save(new Manager(null, "username2", "email2@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
//        clientRepository.save(new Client(null, "username3", "email3@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
//        managerRepository.save(new Manager(null, "username4", "email4@email.com", new BCryptPasswordEncoder().encode("123"), null, new Date()));
//    }

    @Override
    public void initCities()
    {
        Stream.of("Casablanca", "Rabat", "Kénitra").forEach(cityName ->
        {
            City city = new City(null, cityName, null);
            cityRepository.save(city);
        });
    }

    @Override
    public void initRestaurantCategories()
    {
        List<RestaurantCategory> categories = new ArrayList<>();
        categories.add(new RestaurantCategory(null, "Fast Food", null));
        categories.add(new RestaurantCategory(null, "Pizza", null));
        categories.add(new RestaurantCategory(null, "Tacos", null));
        for (RestaurantCategory category : categories)
        {
            restaurantCategoryRepository.save(category);
        }
    }

    @Override
    public void initRestaurants()
    {
        // Une première façon
        restaurantCategoryRepository.findById(1L).ifPresent(restaurantCategory ->
        {
            managerRepository.findById(2L).ifPresent(manager ->
            {
                cityRepository.findById(1L).ifPresent(city ->
                {
                    Calendar instance = Calendar.getInstance();
                    instance.set(0, 0, 0, 8, 0, 0);
                    Date openingTime = instance.getTime();
                    instance.set(0, 0, 0, 23, 0, 0);
                    Date closingTime = instance.getTime();

                    restaurantRepository.save(new Restaurant(null, "La Sqala", "Le restaurant La Sqala vous souhaite la bienvenue", null, "0522260960", 33.602907494472916, -7.619123341144301, city, openingTime, closingTime, restaurantCategory, manager, null, null, null));
                });
            });
        });

        // Une deuxième façon
        {
            Calendar instance = Calendar.getInstance();
            instance.set(0, 0, 0, 11, 0, 0);
            Date openingTime = instance.getTime();
            instance.set(0, 0, 0, 0, 0, 0);
            Date closingTime = instance.getTime();

            restaurantRepository.save(new Restaurant(null, "Le Dhow", "Le restaurant Le Dhow vous souhaite la bienvenue", null, "0537702302", 34.028942633048786, -6.832355812393539, cityRepository.findById(2L).get(), openingTime, closingTime, restaurantCategoryRepository.findById(2L).get(), managerRepository.findById(4L).get(), null, null, null));
        }

        {
            Calendar instance = Calendar.getInstance();
            instance.set(0, 0, 0, 11, 0, 0);
            Date openingTime = instance.getTime();
            instance.set(0, 0, 0, 0, 0, 0);
            Date closingTime = instance.getTime();

            restaurantRepository.save(new Restaurant(null, "Little Japan", "Le restaurant Little Japan vous souhaite la bienvenue", null, "0537363534", 34.260982980170155, -6.589085301636395, cityRepository.findById(3L).get(), openingTime, closingTime, restaurantCategoryRepository.findById(2L).get(), managerRepository.findById(4L).get(), null, null, null));
        }

    }

    @Override
    public void initMealCategories()
    {
        Stream.of("Breakfast", "Lunch", "Dinner").forEach(category ->
        {
            mealCategoryRepository.save(new MealCategory(null, category, null));
        });
    }

    @Override
    public void initMeals()
    {
        mealRepository.save(new Meal(null, "Hamburger", 30.00, restaurantRepository.findById(1L).get(), mealCategoryRepository.findById(1L).get()));
        mealRepository.save(new Meal(null, "Double Cheese", 45.00, restaurantRepository.findById(2L).get(), mealCategoryRepository.findById(2L).get()));
        mealRepository.save(new Meal(null, "Rosti", 60.00, restaurantRepository.findById(2L).get(), mealCategoryRepository.findById(3L).get()));
    }

    @Override
    public void initRate()
    {
        rateRepository.save(new Rate(null, 2, clientRepository.findById(1L).get(), restaurantRepository.findById(1L).get()));
        rateRepository.save(new Rate(null, 3, clientRepository.findById(1L).get(), restaurantRepository.findById(2L).get()));
        rateRepository.save(new Rate(null, 4, clientRepository.findById(3L).get(), restaurantRepository.findById(1L).get()));
        rateRepository.save(new Rate(null, 5, clientRepository.findById(3L).get(), restaurantRepository.findById(2L).get()));
    }

    @Override
    public void initComment()
    {
        commentRepository.save(new Comment(null, "Coool", new Date(), clientRepository.findById(1L).get(), restaurantRepository.findById(1L).get()));
        commentRepository.save(new Comment(null, "J'adore ce restaurant", new Date(), clientRepository.findById(1L).get(), restaurantRepository.findById(1L).get()));
        commentRepository.save(new Comment(null, "J'aime", new Date(), clientRepository.findById(3L).get(), restaurantRepository.findById(1L).get()));
        commentRepository.save(new Comment(null, "Nul", new Date(), clientRepository.findById(3L).get(), restaurantRepository.findById(2L).get()));
        commentRepository.save(new Comment(null, "1 étoile", new Date(), clientRepository.findById(3L).get(), restaurantRepository.findById(2L).get()));
        commentRepository.save(new Comment(null, "Je déconseille", new Date(), clientRepository.findById(1L).get(), restaurantRepository.findById(2L).get()));
    }

    @Override
    public void initFavoriteRestaurant()
    {
        favoriteRestaurantRepository.save(new FavoriteRestaurant(null, clientRepository.findById(1L).get(), restaurantRepository.findById(1L).get()));
        favoriteRestaurantRepository.save(new FavoriteRestaurant(null, clientRepository.findById(3L).get(), restaurantRepository.findById(1L).get()));
        favoriteRestaurantRepository.save(new FavoriteRestaurant(null, clientRepository.findById(3L).get(), restaurantRepository.findById(2L).get()));
    }

    @Override
    public void insertAllData()
    {
        System.out.println("INSERT");

        initUsers(); // 1
        initCities(); // 2
        initRestaurantCategories(); // 3
        initRestaurants(); // 4
        initMealCategories(); // 5
        initMeals(); // 6
        initRate(); // 7
        initComment(); // 8
        initFavoriteRestaurant(); // 9
    }

    @Override
    public void deleteAllData()
    {
        System.out.println("DELETE");

        favoriteRestaurantRepository.truncate(); // 9
        commentRepository.truncate(); // 8
        rateRepository.truncate(); // 7
        mealRepository.truncate(); // 6
        mealCategoryRepository.truncate(); // 5
        restaurantRepository.truncate(); // 4
        restaurantCategoryRepository.truncate(); // 3
        cityRepository.truncate(); // 2
        userRepository.truncate(); // 1
    }
}
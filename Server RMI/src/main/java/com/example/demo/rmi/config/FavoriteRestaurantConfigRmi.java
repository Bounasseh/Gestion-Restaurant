package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IFavoriteRestaurantRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class FavoriteRestaurantConfigRmi
{
    @Bean
    public RmiServiceExporter getFavoriteRestaurantRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("favoriteRestaurantRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("favoriteRestaurant");
        exporter.setServiceInterface(IFavoriteRestaurantRmiRemote.class);

        return exporter;
    }
}
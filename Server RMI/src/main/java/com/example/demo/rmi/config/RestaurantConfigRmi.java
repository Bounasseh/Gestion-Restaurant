package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IRestaurantRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class RestaurantConfigRmi
{
    @Bean
    public RmiServiceExporter getRestaurantRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("restaurantRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("restaurant");
        exporter.setServiceInterface(IRestaurantRmiRemote.class);

        return exporter;
    }
}
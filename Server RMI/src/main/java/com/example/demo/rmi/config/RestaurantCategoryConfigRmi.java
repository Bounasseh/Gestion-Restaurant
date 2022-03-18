package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IRestaurantCategoryRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class RestaurantCategoryConfigRmi
{
    @Bean
    public RmiServiceExporter getRestaurantCategoryRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("restaurantCategoryRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("restaurantCategory");
        exporter.setServiceInterface(IRestaurantCategoryRmiRemote.class);

        return exporter;
    }
}
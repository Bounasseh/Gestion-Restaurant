package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IMealCategoryRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class MealCategoryConfigRmi
{
    @Bean
    public RmiServiceExporter getMealCategoryRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("mealCategoryRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("mealCategory");
        exporter.setServiceInterface(IMealCategoryRmiRemote.class);

        return exporter;
    }
}
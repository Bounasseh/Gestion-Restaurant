package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IMealRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class MealConfigRmi
{
    @Bean
    public RmiServiceExporter getMealRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("mealRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("meal");
        exporter.setServiceInterface(IMealRmiRemote.class);

        return exporter;
    }
}
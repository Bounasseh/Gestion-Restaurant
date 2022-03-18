package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.ICityRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class CityConfigRmi
{
    @Bean
    public RmiServiceExporter getCityRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("cityRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("city");
        exporter.setServiceInterface(ICityRmiRemote.class);

        return exporter;
    }
}
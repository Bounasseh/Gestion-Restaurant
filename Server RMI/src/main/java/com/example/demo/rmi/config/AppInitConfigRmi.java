package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IAppInitRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class AppInitConfigRmi
{
    @Bean
    public RmiServiceExporter getAppInitRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("appInitRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("appInit");
        exporter.setServiceInterface(IAppInitRmiRemote.class);

        return exporter;
    }
}
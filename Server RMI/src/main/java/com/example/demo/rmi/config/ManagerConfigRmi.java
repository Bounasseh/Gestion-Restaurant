package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IManagerRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class ManagerConfigRmi
{
    @Bean
    public RmiServiceExporter getManagerRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("managerRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("manager");
        exporter.setServiceInterface(IManagerRmiRemote.class);

        return exporter;
    }
}
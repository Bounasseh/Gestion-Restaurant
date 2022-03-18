package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IManagerRmiRemote;
import com.example.demo.rmi.inter.IUserRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class UserConfigRmi
{
    @Bean
    public RmiServiceExporter getUserRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("userRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("user");
        exporter.setServiceInterface(IUserRmiRemote.class);

        return exporter;
    }
}
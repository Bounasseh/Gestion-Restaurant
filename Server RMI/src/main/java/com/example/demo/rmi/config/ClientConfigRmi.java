package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.IClientRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class ClientConfigRmi
{
    @Bean
    public RmiServiceExporter getClientRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("clientRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("client");
        exporter.setServiceInterface(IClientRmiRemote.class);

        return exporter;
    }
}
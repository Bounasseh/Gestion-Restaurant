package com.example.demo.rmi.config;

import com.example.demo.rmi.inter.ICommentRmiRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class CommentConfigRmi
{
    @Bean
    public RmiServiceExporter getCommentRmiService(ApplicationContext context)
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean("commentRmiService"));
        exporter.setRegistryPort(1099);
        exporter.setServiceName("comment");
        exporter.setServiceInterface(ICommentRmiRemote.class);

        return exporter;
    }
}
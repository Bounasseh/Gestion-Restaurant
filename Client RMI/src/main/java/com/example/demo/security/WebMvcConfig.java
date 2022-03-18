package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        /* Cette Classe n'est pas liée toujours au context de Securité, je peux par exemple avoir un controleur qui implémente "WebMvcConfigurer" où je peux réécrire la méthode "addViewControllers" */

         /*Cette classe avec cette méthode intervient lorsqu'on a pas de contrôleur qui interepte le chemin "/login"
         Lorsqu'on a un contrôleur qui interepte le chemin "/login", cette configuration sera cachée
         Dans cette configuration, il n'y a que les chemin "login" & logout à leur associer les une vue. Avec autres chemin, cette configuration sera ignoré
         Cette méthode n'est pas faite pour configurer le chemin d'authentification Spring Security*/

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler(
                "/webjars/**",
                "/images/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/images/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
}
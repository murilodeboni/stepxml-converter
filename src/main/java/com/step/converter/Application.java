package com.step.converter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class Application {

    public static void main( String args[] ) {

        new SpringApplicationBuilder( Application.class ).properties( "spring.config.name:application,messages" ).build().run( args );

    }

}
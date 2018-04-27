package com.step.converter.infrastructure.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value( "${service.version}" )
    private String VERSION;

    @Bean
    public Docket productApi( ) {

        return new Docket( DocumentationType.SWAGGER_2 )
                .select()
                .apis( RequestHandlerSelectors.any( ) )
                .paths( Predicates.not( PathSelectors.regex("/error.*" ) ) )
                .build()
                .apiInfo( metaData() );
    }

    private ApiInfo metaData() {

        return new ApiInfo( "STEPml Converter"
                , "STEPXml Converter"
                , VERSION
                ,""
                ,new Contact( "OpenSource", "", null )
                ,""
                ,""
                , new ArrayList<>() );
    }
}
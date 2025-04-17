package com.litovka.project_example.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);
        return modelMapper;
    }
}
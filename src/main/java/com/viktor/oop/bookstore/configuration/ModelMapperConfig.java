package com.viktor.oop.bookstore.configuration;

import com.viktor.oop.bookstore.configuration.converter.DtoToBookConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static final ModelMapper modelMapper = new ModelMapper();
    @Bean
    public ModelMapper modelMapper() {
        modelMapper.addConverter(new DtoToBookConverter());
        return modelMapper;
    }
}

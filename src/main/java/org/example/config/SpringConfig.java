package org.example.config;

import org.example.repository.TaskRepository;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = TaskRepository.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.example\\..+Repository")
        })

public class SpringConfig {
}

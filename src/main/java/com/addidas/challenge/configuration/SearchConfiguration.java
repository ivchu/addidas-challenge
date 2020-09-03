package com.addidas.challenge.configuration;

import com.google.common.collect.ImmutableMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Configuration
public class SearchConfiguration {

    @Bean
    public Map<String, Sort> sortingMap() {
        return ImmutableMap.<String, Sort>builder()
                .put("ratingAsc", Sort.by(Sort.Order.asc("rating")))
                .put("ratingDesc", Sort.by(Sort.Order.desc("rating")))
                .put("dateAsc", Sort.by(Sort.Order.asc("date")))
                .put("dateDesc", Sort.by(Sort.Order.desc("date")))
                .build();
    }
}

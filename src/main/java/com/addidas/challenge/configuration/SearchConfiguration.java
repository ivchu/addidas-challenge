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
                .put("ratingasc", Sort.by(Sort.Order.asc("rating")))
                .put("ratingdesc", Sort.by(Sort.Order.desc("rating")))
                .put("dateasc", Sort.by(Sort.Order.asc("date")))
                .put("datedesc", Sort.by(Sort.Order.desc("date")))
                .build();
    }
}

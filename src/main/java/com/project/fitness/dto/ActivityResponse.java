package com.project.fitness.dto;

import com.project.fitness.model.ActivityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityResponse {

        private String id ;

        private String userId  ;
        private ActivityType type;
        private Map<String,Object> additionalMetrics ;

        private Integer duration;
        private Integer calories;
        private LocalDateTime startTime;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;


    }



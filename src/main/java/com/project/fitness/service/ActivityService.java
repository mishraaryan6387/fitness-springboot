package com.project.fitness.service;


import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;

import com.project.fitness.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ActivityService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ActivityService.class);

    private final ActivityRepository activityRepository;

    private  final UserRepository userRepository;




    public ActivityResponse trackActivity(ActivityRequest request) {
        User user =  userRepository.findById(request.getUserId())
                .orElseThrow(() -> { logger.warn("User not found: {}", request.getUserId()); return new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "User not found " + request.getUserId()); });

        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .calories(request.getCalories())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
                response.setId(activity.getId());
                response.setUserId((activity.getUser().getId()));
                response.setType(activity.getType());
                response.setDuration(activity.getDuration());
                response.setCalories(activity.getCalories());
                response.setStartTime(activity.getStartTime());
                response.setAdditionalMetrics(activity.getAdditionalMetrics());
                response.setUpdatedAt(activity.getUpdatedAt());
                response.setCreatedAt(activity.getCreatedAt());
                return response;

}

    public List<ActivityResponse> getUserActivities(String userId) {
                 List<Activity> activityList =   activityRepository.findByUserId(userId);
             return    activityList.stream()
                        .map(this::mapToResponse)
                     .collect(Collectors.toList());


    }
}


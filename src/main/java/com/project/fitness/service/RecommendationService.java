package com.project.fitness.service;


import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private  final UserRepository userRepository;
    private final RecommendationRepository recommendationRepository;
    private final ActivityRepository activityRepository;
    public Recommendation generateRecommendations(RecommendationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found :"+ request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(()-> new RuntimeException("Activity not found :"+ request.getActivityId()));
        Recommendation recommendation = new Recommendation().builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .safety(request.getSafety())
                .suggestions(request.getSuggestions())
                .build();
      return   recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getUserRecommendation(String userId) {

        return recommendationRepository.findByUser_Id(userId);
    }
    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivity_Id(activityId);

    }
}

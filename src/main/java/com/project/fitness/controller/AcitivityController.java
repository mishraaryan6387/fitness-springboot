package com.project.fitness.controller;


import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AcitivityController {

    private  final ActivityService activityService;



    @PostMapping("/activities")
    public ResponseEntity<ActivityResponse> Trackactivities (@RequestBody ActivityRequest request, org.springframework.security.core.Authentication authentication){

        String origUserId = request.getUserId();
        Object principal = authentication != null ? authentication.getPrincipal() : null;
        org.slf4j.LoggerFactory.getLogger(AcitivityController.class).info("Trackactivities called. request.userId={}, principal={}", origUserId, principal);

        if (request.getUserId() == null || request.getUserId().isBlank()) {
            if (principal != null) {
                request.setUserId(principal.toString());
                org.slf4j.LoggerFactory.getLogger(AcitivityController.class).info("Set request.userId from principal: {}", request.getUserId());
            } else {
                org.slf4j.LoggerFactory.getLogger(AcitivityController.class).warn("No principal available and no userId in request");
            }
        }

        return ResponseEntity.ok(activityService.trackActivity(request));

     }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityResponse>> getUserActivities (
          @RequestHeader(value = "X-User-ID")  String  userId
    ){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }


}

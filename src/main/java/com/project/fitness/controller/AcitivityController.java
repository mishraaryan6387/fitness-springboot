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



    @PostMapping("/activity")
    public ResponseEntity<ActivityResponse> Trackactivities (@RequestBody ActivityRequest request){

        return ResponseEntity.ok(activityService.trackActivity(request));

     }

    @GetMapping("/activit")
    public ResponseEntity<List<ActivityResponse>> getUserActivities (
          @RequestHeader(value = "X-User-ID")  String  userId
    ){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }


}

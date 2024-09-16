package com.mithun.SpringRest.controller;


import com.mithun.SpringRest.model.JobPost;
import com.mithun.SpringRest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobRestController {
    @Autowired
    private JobService service;

 @GetMapping(path="jobPosts",produces={"application/json"})
//    @ResponseBody
    public List<JobPost> getAlljobs(){
        return service.getAllJobs();

    }


    @GetMapping("jobPost/{postId}")
    public ResponseEntity<JobPost> getJob(@PathVariable("postId") int postId) {
        JobPost jobPost = service.getJob(postId);
        if (jobPost != null) {
            return ResponseEntity.ok(jobPost);  // Returns 200 OK with the JobPost object
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Returns 404 Not Found
        }
    }

    @PostMapping("jobPost")
    public ResponseEntity<JobPost> addJob(@RequestBody JobPost jobPost) {
        try {
            service.addJob(jobPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(jobPost);  // Return 201 Created with the JobPost object
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Return 500 Internal Server Error if any error occurs
        }
    }

    @PutMapping("jobPost")
    public ResponseEntity<JobPost> updateJob(@RequestBody JobPost jobPost) {
        // Check if the job post exists
        JobPost existingJobPost = service.getJob(jobPost.getPostId());
        if (existingJobPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 if the job post doesn't exist
        }

        // Update the job post
        service.updateJob(jobPost);

        // Return updated job post with 200 OK
        JobPost updatedJobPost = service.getJob(jobPost.getPostId());
        return ResponseEntity.ok(updatedJobPost);
    }


    @DeleteMapping("jobPost/{postId}")
    public ResponseEntity<String> deleteJob(@PathVariable("postId") int postId) {
        JobPost existingJobPost = service.getJob(postId);

        if (existingJobPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job post not found");  // 404 if job post doesn't exist
        }

        service.deleteJob(postId);
        return ResponseEntity.ok("Job post deleted successfully");  // 200 OK if deleted successfully
    }
//    @GetMapping("/frontend/experienced")
//    public ResponseEntity<List<JobPost>> getExperiencedFrontEndDevelopers() {
//        List<JobPost> exp = service.findFrontEndDevelopersWithExperienceGreaterThanThreeYears();
//        return ResponseEntity.ok(exp);
//    }

    @GetMapping("/frontend/{id}")
    public ResponseEntity<List<JobPost>> getExperiencedFrontEndDevelopers(@PathVariable("id")int id) {
        List<JobPost> frontendexp = service.findFrontEndDevelopersWithExperienceGreaterThanThreeYears(id);
        return ResponseEntity.ok(frontendexp);
    }
    @GetMapping("/type/{jobType}")
    public ResponseEntity<List<JobPost>> getJobsByType(@PathVariable String jobType) {
        List<JobPost> jobs = service.findJobsByType(jobType);
        return ResponseEntity.ok(jobs);

    }
}

package com.mithun.SpringRest.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mithun.SpringRest.model.JobPost;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile,String postDec);

    @Query("SELECT jp FROM JobPost jp WHERE jp.postProfile= 'Frontend Developer' AND jp.reqExperience > :years")
    List<JobPost> findFrontEndDevelopersWithExperienceGreaterThan(@Param("years") int years);

    @Query("SELECT jp FROM JobPost jp WHERE jp.jobtype = :jobType")
    List<JobPost> findJobType(@Param("jobType") String jobType);
}



//    // method to return all JobPosts
//    public List<JobPost> getAllJobs() {
//        return jobs;
//    }
//
//
//
//    // method to save a job post object into arrayList
//    public void addJob(JobPost job) {
//        jobs.add(job);
//
//    }
//
//
//
//
//    //method to get a job by postId
//    public JobPost getJob(int postId) {
//        for (JobPost job : jobs) {
//            if (job.getPostId() == postId) {
//                return job;
//            }
//        }
//
//        return null;
//    }
//
//    public void updateJob(JobPost jobPost) {
//        for(JobPost job : jobs) {
//            if(job.getPostId() == jobPost.getPostId()) {
//                job.setPostId(jobPost.getPostId());
//                job.setPostProfile(jobPost.getPostProfile());
//                job.setPostDesc(jobPost.getPostDesc());
//                job.setReqExperience(jobPost.getReqExperience());
//                job.setPostTechStack(jobPost.getPostTechStack());
//            }
//        }
//    }
//
//    public void deleteJob(int postId) {
//        for(JobPost job : jobs) {
//            if(job.getPostId() == postId) {
//                jobs.remove(job);
//            }
//        }
//    }

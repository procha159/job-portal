package com.example.sampleapp.repository;

import com.example.sampleapp.entity.IRecruiterJobs;
import com.example.sampleapp.entity.JobPostActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobPostActivityRepository extends JpaRepository<JobPostActivity, Integer> {

    @Query(value = "SELECT COUNT(s.user_id) AS totalCandidates, j.job_post_id, j.job_title, l.id AS locationId, l.city, l.state, l.country, c.id AS companyId, c.name " +
            "FROM job_post_activity j INNER JOIN job_location l ON j.job_location_id = l.id INNER JOIN job_company c ON j.job_company_id = c.id " +
            "LEFT JOIN job_seeker_apply s ON s.job = j.job_post_id " +
            "WHERE j.posted_by_id = :recruiter " +
            "GROUP BY j.job_post_id, j.job_title, l.id, l.city, l.state, l.country, c.id, c.name" ,nativeQuery = true)
    List<IRecruiterJobs> getRecruitersJobs(@Param("recruiter") int recruiter);
}

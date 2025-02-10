package com.videostream.videoapp.repository;

import com.videostream.videoapp.entity.Video;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video,String> , JpaSpecificationExecutor<Video> {

    static Specification<Video> hasSearchCriteria(String searchCriteria) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + searchCriteria.toLowerCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("director")), "%" + searchCriteria.toLowerCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("cast")), "%" + searchCriteria.toLowerCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("synopsis")), "%" + searchCriteria.toLowerCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("genre")), "%" + searchCriteria.toLowerCase() + "%")
        );
    }


}

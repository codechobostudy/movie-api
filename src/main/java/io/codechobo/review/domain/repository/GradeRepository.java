package io.codechobo.review.domain.repository;


import io.codechobo.review.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}
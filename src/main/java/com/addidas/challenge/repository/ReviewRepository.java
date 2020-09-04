package com.addidas.challenge.repository;

import com.addidas.challenge.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query
    Optional<Review> findByActiveTrueAndId(Long reviewId);
    @Query
    Optional<Review> findByText(String text);
    @Query
    List<Review> findByActiveTrueAndProductId(Long  productId, Pageable pageable);
    @Query
    Optional<Review> findByActiveTrueAndIdAndProductId(Long reviewId, Long  productId);
}

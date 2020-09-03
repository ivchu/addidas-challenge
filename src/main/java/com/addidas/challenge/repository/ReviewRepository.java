package com.addidas.challenge.repository;

import com.addidas.challenge.entity.Product;
import com.addidas.challenge.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query
    List<Review> findByActiveTrue();
    @Query
    List<Review> findByActiveTrueAndProductId(Long  productId);
    @Query
    List<Review> findByActiveTrueAndProductId(Long  productId, Pageable pageable);
    @Query
    List<Review> findByActiveTrueAndProductId(Long  productId, Sort sort);
}

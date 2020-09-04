package com.addidas.challenge.service

import com.addidas.challenge.entity.Review
import com.addidas.challenge.exception.ReviewNotFoundException
import com.addidas.challenge.repository.ReviewRepository
import com.addidas.challenge.service.impl.ReviewServiceImpl
import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class ReviewServiceSpec extends Specification {
    @Subject
    ReviewServiceImpl reviewService
    @Collaborator
    ReviewRepository reviewRepository = Mock()
    @Collaborator
    ProductService productService = Mock()


    @Test
    def 'should delegate save to repository'() {
        when:
        reviewService.saveReview(Mock(Review))
        then:
        1 * reviewRepository.save(_)
    }


    @Test
    def 'deleteReview should throw ReviewNotFoundException if review is not presented for product'() {
        given:
        reviewRepository.findByActiveTrueAndIdAndProductId(*_) >> Optional.empty()
        when:
        reviewService.disableReview(0L, 0L)
        then:
        thrown(ReviewNotFoundException.class)
    }
}

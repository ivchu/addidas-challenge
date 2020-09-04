package com.addidas.challenge.service

import com.addidas.challenge.entity.Review
import com.addidas.challenge.repository.ReviewRepository
import com.addidas.challenge.service.impl.ReviewServiceImpl
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject

@SpringBootTest
class ReviewServiceSpec extends Specification{
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
    def 'deleteReview should set active false and save entity to repository'() {
        given:
        def reviewMock = Spy(Review)
        def id = 0L
        reviewRepository.findById(id) >> Optional.of(reviewMock)
        when:
        reviewService.deleteReview(id)
        then:
        1 * reviewMock.setActive(false)
        and:
        1 * reviewRepository.save(reviewMock)
    }
}

package io.codechobo.review.interfaces.api;

import io.codechobo.review.domain.Review;
import io.codechobo.review.domain.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/review")
public class ReviewRestController {

    @Autowired
    private ReviewRepository reviewRepository;

    @RequestMapping(value = {"", "/"})
    public ResponseEntity list() {
        return new ResponseEntity<>(reviewRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody @Valid Review review){
        return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"})
    public ResponseEntity show(@PathVariable Long id) {
        return new ResponseEntity<>(reviewRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody Review review) {
        return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.OK);
    }


}

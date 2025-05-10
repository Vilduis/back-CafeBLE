package backend.project.controllers;

import backend.project.dtos.DTOReview;
import backend.project.entities.Review;
import backend.project.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview() {
        return ResponseEntity.ok(reviewService.listAllReview());
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Long id) {
        Review review = reviewService.findById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/reviews/register")
    public ResponseEntity<Review> register(@RequestBody DTOReview dtoReview) {
        Review review = reviewService.insertReview(dtoReview);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long id, @RequestBody DTOReview dtoReview) {
        Review review = reviewService.updateReview(dtoReview);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //listar las 3 reviews con mejor rating
    @GetMapping("/reviews/top")
    public ResponseEntity<List<Review>> findTop3ByRating() {
        return ResponseEntity.ok(reviewService.findTop3ByRating());
    }
}

package backend.project.services;

import backend.project.dtos.DTOReview;
import backend.project.entities.Review;

import java.util.List;

public interface ReviewService {
    public Review insertReview(DTOReview dtoReview);
    public Review updateReview(DTOReview dtoReview);
    public Review findById(Long id);
    public void deleteReview(Long id);
    public List<Review> listAllReview();

    // listar 3 mejores reviews List<Review> findTop3ByRating();

    public List<Review> findTop3ByRating();

}

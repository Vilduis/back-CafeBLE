package backend.project.repositories;

import backend.project.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long> {

    //Listar review por rating (4 o 5), solo debe mostrar los 3 primeros
    @Query(value = "SELECT * FROM reviews WHERE rating = 4 OR rating = 5 ORDER BY rating DESC LIMIT 3", nativeQuery = true)
    List<Review> findTop3ByRating();
}

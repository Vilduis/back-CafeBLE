package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String origin;
    private String intensity;
    private String weightgram;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Review> reviewss;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<CartItems> cartItems;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private  List<OrderDetail> orderDetails;

}

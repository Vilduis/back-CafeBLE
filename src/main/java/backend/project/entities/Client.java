package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="clients")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Review> reviewss;

}

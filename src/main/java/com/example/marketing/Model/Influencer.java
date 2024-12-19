package com.example.marketing.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
///   Essa

public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotEmpty(message = "Influencer name cannot be Empty")
    @Size(min = 2, max = 100, message = "Influencer name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String influencer_name;

    @NotEmpty(message = "Influencer phone cannot be Empty")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be a valid format (e.g., +966234567890)")
    @Column(unique = true, nullable = false)
    private String influencer_phone;

    @NotEmpty(message = "Influencer email cannot be Empty")
    @Email
    @Column(unique = true , columnDefinition = "varchar(50) not null")
    private String influencer_email;

    @NotEmpty
    @Pattern(regexp = "^(Restaurants|Tourism|Technology|Cars|Fashion|Sports)$", message = "Booking status must be one of Restaurants|Tourism|Technology|Cars|Fashion|Sports")
    @Column(nullable = false)
    private String specialty;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    private Set<Platform> platforms;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    private Set<BookingOneTime> bookingOneTimes;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    private Set<Package> packages;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "influencer")
    private Set<BookingPackage> bookingPackages;

}

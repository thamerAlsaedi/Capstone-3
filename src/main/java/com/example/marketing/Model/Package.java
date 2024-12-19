package com.example.marketing.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 1, message = "Package times must be at least 1")
    @Column(nullable = false)
    private Integer package_times;

    @Min(value = 0, message = "Package price must be greater than or equal to 0")
    @Column(nullable = false)
    private Double package_price;

    @NotEmpty(message = "Platform name cannot be Empty")
    @Size(min = 3, max = 50, message = "Platform name must be between 3 and 50 characters")
    @Column(nullable = false)
    private String platform_name;

    @Column(nullable = false)
    private boolean isAvailable = false;

    @ManyToOne
    @JoinColumn(name = "influencer_id", nullable = false)
    @JsonIgnore
    private Influencer influencer;


    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL)
    private Set<BookingPackage> bookingPackages;
}
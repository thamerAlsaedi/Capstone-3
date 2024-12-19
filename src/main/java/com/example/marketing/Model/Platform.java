package com.example.marketing.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

public class Platform {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotEmpty(message = "Platform name is mandatory")
    @Size(min = 3, max = 100, message = "Platform name must be between 3 and 100 characters")
    @Column(nullable = false)
    private String platform_name;

    @NotNull(message = "Platform followers count is mandatory")
    @Min(value = 0, message = "Platform followers cannot be negative")
    @Column(nullable = false)
    private Integer platform_followers;

    @ManyToOne
    @JsonIgnore
    private Influencer influencer;

    @OneToMany(mappedBy = "platform", cascade = CascadeType.ALL)
    private Set<Type> types;

    @OneToMany(mappedBy = "platform", cascade = CascadeType.ALL)
    private Set<BookingOneTime> bookingOneTimes;


}

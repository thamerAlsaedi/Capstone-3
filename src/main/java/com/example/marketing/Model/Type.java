package com.example.marketing.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Type name can not be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String type_name;
    @NotNull(message = "price can not be null")
    @Column(columnDefinition = "double not null")
    @Positive
    private Double type_price;


    @ManyToOne
    @JsonIgnore
    private Platform platform;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<BookingOneTime> bookingOneTimes;
}

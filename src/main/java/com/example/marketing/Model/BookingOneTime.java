package com.example.marketing.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

///   Essa
public class BookingOneTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "booking can be not null")
    @Column(columnDefinition = "datetime not null")
    private LocalDateTime bookingDate = LocalDateTime.now();

    @NotEmpty(message = "Booking_OneTime Status can not be null")
    @Pattern(regexp = "PENDING|In Progress|COMPLETED|REJECT|Accepted", message = "Booking status must be one of PENDING, IN PROCESS, COMPLETED or REJECT")
    @Column(columnDefinition = "varchar(20) not null")
    private String bookingStatus = "PENDING";



//    @NotNull
    @Column(nullable = false)
//    @JsonFormat(pattern = "yyyy-mm-dd")
    @FutureOrPresent
    private LocalDate advertisementStartDate;

    // Relationships
    @ManyToOne
//    @JoinColumn(name = "Company_company_id", nullable = false)
    @JsonIgnore
    private Company company;

    @ManyToOne
//    @JoinColumn(name = "Influencer_Influencer_id", nullable = false)
    @JsonIgnore
    private Influencer influencer;

    @ManyToOne
//    @JoinColumn(name = "platform_platform_id", nullable = false)
    @JsonIgnore
    private Platform platform;

    @ManyToOne
//    @JoinColumn(name = "type", nullable = false)
    @JsonIgnore
    private Type type;
    @OneToMany(mappedBy = "bookingOneTime", cascade = CascadeType.ALL)
    private Set<Review> reviews;
}

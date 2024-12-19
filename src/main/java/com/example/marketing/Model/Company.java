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

    public class Company {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotEmpty(message = "Company name cannot be Empty")
        @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
        @Column(nullable = false,length = 100)
        private String company_name;

        @NotEmpty(message = "Company email cannot be Empty")
        @Email(message = "Invalid email format")
        @Column(nullable = false, unique = true)
        private String company_email;

        @NotEmpty(message = "Company phone number cannot be Empty")
        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a valid format (e.g., 1234567890)")
        @Column(nullable = false, unique = true)
        private String company_phone;

        @NotEmpty(message = "Company CR cannot be Empty")
        @Pattern(regexp = "^[A-Za-z0-9]{3,20}$", message = "Company CR must be alphanumeric and between 3 to 20 characters")
        @Column(nullable = false, unique = true)
        private String company_CR;

        @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
        private Set<BookingOneTime> bookingOneTimes;

        @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
        private Set<Review> reviews;


    }

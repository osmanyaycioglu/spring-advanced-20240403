package org.training.kafka.spring.advanced.models;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.training.kafka.spring.advanced.validation.CheckWords;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@CheckWords({"abc","xyz","123"})
public class Employee {
    @NotBlank
    @Size(min = 2,max = 15)
    private String name;
    @NotEmpty
    private String    surname;
    @NotNull
    @Past
    private LocalDate birthdate;
    @Min(50)
    @Max(300)
    private Integer   height;
    @Min(10)
    @Max(300)
    private Integer weight;
    private Integer counter;

}

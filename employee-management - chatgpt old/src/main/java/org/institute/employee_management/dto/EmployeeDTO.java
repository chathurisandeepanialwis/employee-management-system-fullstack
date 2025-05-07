package org.institute.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

//    @NotBlank(message = "First Name is required")
//    @Size(min = 2, max = 30, message = "Name must be 2–30 characters")
    private String firstName;


//    @NotBlank(message = "Last Name is required")
//    @Size(min = 2, max = 30, message = "Name must be 2–30 characters")
    private String lastName;

//    @Email(message = "Enter a valid email")
//    @NotBlank(message = "Email is required")
    private String email;
}



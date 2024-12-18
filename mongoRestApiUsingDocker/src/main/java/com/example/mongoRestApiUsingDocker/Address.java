package com.example.mongoRestApiUsingDocker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class Address {
    private String country;
    private String city;
    private String postCode;

}

package com.codegym.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class CountryDTO {
    @Pattern(regexp = "^\\d+$", message = "ID nước phải là số")
    private String id;

    @NotEmpty(message = "Vui lòng nhập tên nước")
    private String name;
}

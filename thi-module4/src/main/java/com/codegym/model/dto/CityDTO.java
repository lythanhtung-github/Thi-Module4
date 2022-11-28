package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CityDTO {
    private Long id;

    @NotEmpty(message = "Tên thành phố cần nhập vào chuỗi ngắn")
    @Size(min = 5, max = 100, message = "Tên thành phố có độ dài nằm trong khoảng 5 - 100 ký tự")
    private String name;

    @Pattern(regexp = "^\\d+$", message = "Diện tích phải là số")
    private String area;

    @Pattern(regexp = "^\\d+$", message = "Dân số phải là số")
    private String population;

    @Pattern(regexp = "^\\d+$", message = "Dân số phải là số")
    private String GDP;

    @NotEmpty(message = "Mô tả cần nhập vào 1 đoạn dài")
    @Size(min = 10, max = 200, message = "Tên thành phố có độ dài nằm trong khoảng 10 - 200 ký tự")
    private String description;

    @Valid
    private CountryDTO country;
}

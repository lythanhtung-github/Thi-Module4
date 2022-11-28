package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cities")
@Accessors(chain = true)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name", nullable = false)
    @Length(min = 1, max = 30)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @NotNull(message = "Diện tích không được bỏ trống.")
    @Min(value = 1,message = "Diện tích phải lớn hơn 0")
    @Max(value = 1000000000, message = "Diện tích tối đa là 1 tỷ")
    private BigDecimal area;

    @NotNull(message = "Dân số không được bỏ trống")
    @Min(1)
    @Max(100000000)
    private BigDecimal population;

    @NotNull(message = "GDP không được để trống")
    @Min(1)
    @Max(100000000)
    private BigDecimal GDP;

    @NotEmpty(message = "Vui lòng thêm mô tả")
    @Length(min = 1, max = 200,message = "Mô tả từ 1 đến 200 kí tự")
    private String description;

}

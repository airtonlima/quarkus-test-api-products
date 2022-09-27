package br.dev.airtonlima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ProductDTO {
    private String name;
    private String description;
    private String category;
    private String model;
    private BigDecimal price;
}

package br.com.prodsys.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @JsonProperty("name")
    @NotNull(message = "The name is mandatory")
    private String name;

    @JsonProperty("description")
    @NotNull(message = "The description is mandatory")
    private String description;

    @JsonProperty("price")
    @NotNull(message = "The price is mandatory")
    private double price;

    @JsonProperty("amount")
    @NotNull(message = "The amount is mandatory")
    private int amount;
}

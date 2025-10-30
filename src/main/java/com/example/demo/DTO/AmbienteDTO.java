package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AmbienteDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome do ambiente é obrigatório")
    private String nome;
    @NotBlank(message = "A descrição do ambiente é obrigatória")
    private String descricao;
    @NotBlank(message = "A localização do ambiente é obrigatória")
    private String localizacao;
    @NotNull(message = "A capacidade do ambiente é obrigatória")
    private int capacidade;

}

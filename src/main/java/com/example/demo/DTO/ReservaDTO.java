package com.example.demo.DTO;

import java.time.LocalDateTime;

import com.example.demo.entity.Ambiente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
 
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome da reserva é obrigatório")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "A data de início da reserva é obrigatória")
    private LocalDateTime dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "A data de fim da reserva é obrigatória")
    private LocalDateTime dataFim;

    @NotNull(message = "O ambiente da reserva é obrigatório")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Ambiente ambiente;


}

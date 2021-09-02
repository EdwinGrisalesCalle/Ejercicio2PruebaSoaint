package com.example.pruebasoaint.ejercicio2.dto;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@ApiModel(description = "Representa un objeto de entrada "
		+ "con la información del o los acudientes de un estudiente a registrar")
public class AcudienteRequest {

	@ApiModelProperty(notes = "nombre del acudiente", example = "Maria", required = true)
	private String nombre;
	@ApiModelProperty(notes = "parentesco con el estudiante", example = "papá", required = true)
	private String parentesco;
	@ApiModelProperty(notes = "telefono de acudiente", example = "3234332333", required = true)
	private String telefono;
	
	
}

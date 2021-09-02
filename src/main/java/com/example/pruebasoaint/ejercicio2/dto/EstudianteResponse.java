package com.example.pruebasoaint.ejercicio2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Representa un objeto de salida "
		+ "con la información de un estudiente")
public class EstudianteResponse {

	@ApiModelProperty(notes = "nombre del estudiante", example = "pepito", required = true)
	private String nombre;
	@ApiModelProperty(notes = "Documento de identificación", example = "3213123", required = true)
	private String numeroIdentificacion;
	@ApiModelProperty(notes = "correo electronico", example = "edwin@gmail.com", required = true)
	private String email;
	@ApiModelProperty(notes = "edad del estudiante", example = "22", required = true)
	private Integer edad;
	@ApiModelProperty(notes = "estado activo o inactivo", example = "activo", required = true)
	private String estado;
	
}

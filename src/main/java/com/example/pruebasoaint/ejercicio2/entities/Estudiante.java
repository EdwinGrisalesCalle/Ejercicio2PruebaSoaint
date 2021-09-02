package com.example.pruebasoaint.ejercicio2.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tba_Estudiante")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "numero_identificacion", length = 50)
	private String numeroIdentificacion;
	@Column(name = "email", length = 50)
	private String email;
	@Column(name = "edad", length = 50)
	private Integer edad;
	@Column(name = "estado", length = 50)
	private String estado;
	
	@OneToMany ( fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "estudiante" )
	private List<Acudiente> acudientes;
	


}

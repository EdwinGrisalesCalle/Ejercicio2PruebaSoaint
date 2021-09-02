package com.example.pruebasoaint.ejercicio2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pruebasoaint.ejercicio2.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

	
	public List<Estudiante> findByEdadAndEstado(Integer edad,String estado);

	
}

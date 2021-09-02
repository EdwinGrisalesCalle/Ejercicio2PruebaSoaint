package com.example.pruebasoaint.ejercicio2.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pruebasoaint.ejercicio2.dto.EstudianteRequest;
import com.example.pruebasoaint.ejercicio2.entities.Acudiente;
import com.example.pruebasoaint.ejercicio2.entities.Estudiante;
import com.example.pruebasoaint.ejercicio2.repository.EstudianteRepository;


@Service
public class EstudianteService {

	@Autowired
	private EstudianteRepository estudianteRepository;
	
	public Estudiante crearEstudiante(EstudianteRequest nuevoEstudiante) {
		Estudiante estudiante = new Estudiante();

		estudiante.setNombre(nuevoEstudiante.getNombre());
		estudiante.setNumeroIdentificacion(nuevoEstudiante.getNumeroIdentificacion());
		estudiante.setEmail(nuevoEstudiante.getEmail());
		estudiante.setEdad(nuevoEstudiante.getEdad());
		estudiante.setEstado(nuevoEstudiante.getEstado());
		
		List<Acudiente> acudientes = nuevoEstudiante.getAcudientes().stream()
				.map(acudiente -> Acudiente.builder()
						.nombre(acudiente.getNombre())
						.parentesco(acudiente.getParentesco())
						.telefono(acudiente.getTelefono()).estudiante(estudiante).build())
				.collect(Collectors.toList());


		estudiante.setAcudientes(acudientes);
		estudianteRepository.save(estudiante);

		return estudiante;
	}
	
	
	public List<Estudiante> listarEstudiantes() {
		List<Estudiante> listaEstudiantes = estudianteRepository.findAll();
		return listaEstudiantes;

	}
	
	
	public boolean eliminarEstudiante(Long id) {
		if (estudianteRepository.existsById(id)) {
			Estudiante estudiante = estudianteRepository.findById(id).get();
			estudiante.setEstado("inactivo");
			estudianteRepository.save(estudiante);

			return true;
		}

		return false;
	}
	
	public Estudiante buscarEstudiante(Long id) {
		Estudiante estudiante = estudianteRepository.findById(id).get();
		return estudiante;
	}

	public Estudiante actualizarEstudiante(Long id, EstudianteRequest payLoad) {
		Estudiante EstudianteModificar = buscarEstudiante(id);
		
		EstudianteModificar.setNombre(payLoad.getNombre());
		EstudianteModificar.setNumeroIdentificacion(payLoad.getNumeroIdentificacion());
		EstudianteModificar.setEmail(payLoad.getEmail());
		EstudianteModificar.setEdad(payLoad.getEdad());
		EstudianteModificar.setEstado(payLoad.getEstado());
		
		estudianteRepository.save(EstudianteModificar);

		return EstudianteModificar;
	}

	
	public List<Estudiante> consultarEdadEstado(Integer edad, String estado) {

		return estudianteRepository.findByEdadAndEstado(edad, estado);
	}
	
	
	
	
}

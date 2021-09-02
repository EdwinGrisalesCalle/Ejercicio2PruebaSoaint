package com.example.pruebasoaint.ejercicio2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebasoaint.ejercicio2.dto.EstudianteRequest;
import com.example.pruebasoaint.ejercicio2.dto.EstudianteResponse;
import com.example.pruebasoaint.ejercicio2.entities.Estudiante;
import com.example.pruebasoaint.ejercicio2.service.EstudianteService;
import com.example.pruebasoaint.ejercicio2.util.EntidadToConverter;

import io.swagger.annotations.ApiOperation;

@RestController
public class EstudianteController {

	private final int EDAD_BASE = 18;

	@Autowired
	private EstudianteService estudianteService;
	@Autowired
	private EntidadToConverter convertidor;

	@PostMapping(value = "estudiante")
	@ApiOperation(value = "crear estudiantes", notes = "Crea nuevos estudiantes")
	public ResponseEntity<EstudianteResponse> crearFactura(@RequestBody EstudianteRequest nuevoEstudiante) {

		if (nuevoEstudiante.getEdad() < EDAD_BASE) {
			Estudiante estudiante = estudianteService.crearEstudiante(nuevoEstudiante);
			return new ResponseEntity<>(convertidor.convertirEntidad(estudiante), HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.FOUND);
	}

	@GetMapping(value = "estudiante")
	@ApiOperation(value = "Listar estudiantes", notes = "Lista los estudiantes registrados")
	public ResponseEntity<List<EstudianteResponse>> listarEstudiantes() {

		List<Estudiante> listaEstudiantes = estudianteService.listarEstudiantes();
		return new ResponseEntity<>(convertidor.convertirEntidad(listaEstudiantes), HttpStatus.OK);

	}

	@DeleteMapping(value = "eliminar/{id}")
	@ApiOperation(value = "Eliminar un estudiante", notes = "Elimina un estudiante de la base de datos")
	public ResponseEntity<Boolean> eliminarEstudiante(@PathVariable Long id) {
		boolean existe = estudianteService.eliminarEstudiante(id);
		if (existe) {
			return new ResponseEntity<>(existe, HttpStatus.OK);
		}
		return new ResponseEntity<>(existe, HttpStatus.NOT_FOUND);

	}

	@GetMapping(value = "estudiante/buscar/{id}")
	@ApiOperation(value = "Buscar un estudiante", notes = "Busca un estudiante de la base de datos por su Id")
	public ResponseEntity<EstudianteResponse> buscarEstudiante(@PathVariable Long id) {
		Estudiante estudiante = estudianteService.buscarEstudiante(id);
		return new ResponseEntity<>(convertidor.convertirEntidad(estudiante), HttpStatus.OK);
	}

	@PutMapping(value = "estudiante/{id}")
	@ApiOperation(value = "Actualiza la información de un estudiante", notes = "Busca un estudiante de la base de datos por su Id y lo actualiza")
	public ResponseEntity<EstudianteResponse> actualizarEstudinate(@PathVariable Long id,
			@RequestBody EstudianteRequest payLoad) {
		Estudiante estudianteActualizado = estudianteService.actualizarEstudiante(id, payLoad);

		return new ResponseEntity<>(convertidor.convertirEntidad(estudianteActualizado), HttpStatus.OK);

	}
	
	@GetMapping(value="estudiantesPorEdadEstado/{edad}/{estado}")
	@ApiOperation(value = "Obtiene lista de estudiantes", notes = "Busca un estudiante de la base de datos por edad y estado")
	public  ResponseEntity<List<EstudianteResponse>> consultarFacturaPorNumeroCliente(@PathVariable Integer edad,@PathVariable String estado) {
		List<Estudiante> estudiantes = estudianteService.consultarEdadEstado(edad,estado);
		return new ResponseEntity<>(convertidor.convertirEntidad(estudiantes), HttpStatus.CREATED);
	}
	

}

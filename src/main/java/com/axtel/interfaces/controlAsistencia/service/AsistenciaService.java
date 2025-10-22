package com.axtel.interfaces.controlAsistencia.service;


import org.springframework.stereotype.Service;

import com.axtel.interfaces.controlAsistencia.repository.AsistenciaRepository;

import jakarta.transaction.Transactional;

@Service
public class AsistenciaService {

	private final AsistenciaRepository asistenciaRepository;

	public AsistenciaService(AsistenciaRepository asistenciaRepository) {
		this.asistenciaRepository = asistenciaRepository;
	}

	/**
	 * Revierte la asistencia de un empleado para un rango de fechas específico
	 * 
	 * @param idEmpleado ID del empleado
	 * @param fechaInicio Fecha de inicio del rango (como string)
	 * @param fechaFin Fecha de fin del rango (como string)
	 * @return true si la operación fue exitosa, false en caso contrario
	 * @throws IllegalArgumentException si los parámetros no son válidos
	 */
	@Transactional
	public boolean revertirAsistencia(Integer idEmpleado, String fechaInicio, String fechaFin) {
		if (idEmpleado == null || idEmpleado <= 0) {
			throw new IllegalArgumentException("El ID del empleado debe ser un número positivo");
		}
		
		if (fechaInicio == null || fechaFin == null || fechaInicio.trim().isEmpty() || fechaFin.trim().isEmpty()) {
			throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas o vacías");
		}

		try {

			int rowsAffected = asistenciaRepository.revertirAsistencia(idEmpleado, fechaInicio, fechaFin);
			
			return rowsAffected >= 0; // >= 0 significa que se ejecutó correctamente
		} catch (Exception e) {

			System.err.println("Error al revertir asistencia para empleado " + idEmpleado + ": " + e.getMessage());
			throw new RuntimeException("Error al procesar la reversión de asistencia", e);
		}
	}
	
	@Transactional
	public boolean actualizarAsistencia(Integer idEmpleado, String fechaInicio, String fechaFin, String concepto) {
			// Validaciones de entrada
			if (idEmpleado == null || idEmpleado <= 0) {
				throw new IllegalArgumentException("El ID del empleado debe ser un número positivo");
			}
			
			if (fechaInicio == null || fechaFin == null || fechaInicio.trim().isEmpty() || fechaFin.trim().isEmpty()) {
				throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas o vacías");
			}

			try {
				int rowsAffected = asistenciaRepository.actualizarAsistencia(idEmpleado, fechaInicio, fechaFin, concepto);
				
				return rowsAffected >= 0; // >= 0 significa que se ejecutó correctamente
			} catch (Exception e) {
				
				System.err.println("Error al aplicar asistencia para empleado " + idEmpleado + ": " + e.getMessage());
				throw new RuntimeException("Error al procesar la aplicación de asistencia", e);
			}
	}
}
	


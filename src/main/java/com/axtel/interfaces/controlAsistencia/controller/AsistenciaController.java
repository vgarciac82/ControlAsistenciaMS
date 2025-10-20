package com.axtel.interfaces.controlAsistencia.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axtel.interfaces.controlAsistencia.service.AsistenciaService;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

	private final AsistenciaService asistenciaService;

	private final Logger logger = LoggerFactory.getLogger(AsistenciaController.class);
	public AsistenciaController(AsistenciaService asistenciaService) {
		this.asistenciaService = asistenciaService;
	}

	
    @PostMapping("/actualizar")
    public ResponseEntity<Map<String, Object>> actualizarAsistencia(@RequestBody AsistenciaRequest request) {
    	Map<String, Object> response = new HashMap<>();
    	
    	try {
	    	boolean resultado = asistenciaService.actualizarAsistencia(
					request.getIdEmpleado(), 
					request.getFechaInicio(), 
					request.getFechaFin(),
					request.getConcepto()
				);
	    	
	
			response.put("success", resultado);
			response.put("message", resultado ? "Asistencia revertida exitosamente" : "No se pudo revertir la asistencia");
			response.put("idEmpleado", request.getIdEmpleado());
			response.put("fechaInicio", request.getFechaInicio());
			response.put("fechaFin", request.getFechaFin());
			response.put("concepto", request.getConcepto());
			
			return ResponseEntity.ok(response);
		
		} catch (IllegalArgumentException e) {
			logger.error("Error al actualizar asistencia: {}", e.toString(),e);
			response.put("success", false);
			response.put("message", "Error de validación: " + e.getMessage());
			response.put("error", "VALIDATION_ERROR");
			
			return ResponseEntity.badRequest().body(response);
			
		} catch (Exception e) {
			logger.error("Error al actualizar asistencia: {}", e.toString(),e);
			response.put("success", false);
			response.put("message", "Error interno del servidor: " + e.getMessage());
			response.put("error", "INTERNAL_ERROR");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
    }

	@PostMapping("/revertir")
	public ResponseEntity<Map<String, Object>> revertirAsistencia(@RequestBody AsistenciaRequest request) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			// Enviar fechas como strings directamente al servicio
			boolean resultado = asistenciaService.revertirAsistencia(
				request.getIdEmpleado(), 
				request.getFechaInicio(), 
				request.getFechaFin()
			);
			
			response.put("success", resultado);
			response.put("message", resultado ? "Asistencia revertida exitosamente" : "No se pudo revertir la asistencia");
			response.put("idEmpleado", request.getIdEmpleado());
			response.put("fechaInicio", request.getFechaInicio());
			response.put("fechaFin", request.getFechaFin());
			
			return ResponseEntity.ok(response);
			
		} catch (IllegalArgumentException e) {
			logger.error("Error al revertir asistencia: {}", e.toString(),e);
			response.put("success", false);
			response.put("message", "Error de validación: " + e.getMessage());
			response.put("error", "VALIDATION_ERROR");
			
			return ResponseEntity.badRequest().body(response);
			
		} catch (Exception e) {
			logger.error("Error al revertir asistencia: {}", e.toString(),e);
			response.put("success", false);
			response.put("message", "Error interno del servidor: " + e.getMessage());
			response.put("error", "INTERNAL_ERROR");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**
	 * Clase interna para representar la petición de reversión de asistencia
	 */
	public static class AsistenciaRequest {
		private Integer idEmpleado;
		private String fechaInicio;
		private String fechaFin;
		private String concepto; 
		
		// Constructores
		public AsistenciaRequest() {}

		public AsistenciaRequest(Integer idEmpleado, String fechaInicio, String fechaFin, String concepto) {
			this.idEmpleado = idEmpleado;
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;	
			this.concepto = concepto;
		}

		// Getters y Setters
		public Integer getIdEmpleado() {
			return idEmpleado;
		}

		public void setIdEmpleado(Integer idEmpleado) {
			this.idEmpleado = idEmpleado;
		}

		public String getFechaInicio() {
			return fechaInicio;
		}

		public void setFechaInicio(String fechaInicio) {
			this.fechaInicio = fechaInicio;
		}

		public String getFechaFin() {
			return fechaFin;
		}

		public void setFechaFin(String fechaFin) {
			this.fechaFin = fechaFin;
		}

	    public String getConcepto() {
	        return concepto;
	    }

	    public void setConcepto(String concepto) {
	        this.concepto = concepto;
	    }

	    @Override
	    public String toString() {
	        return "AsistenciaRequest{" +
	                "idEmpleado=" + idEmpleado +
	                ", fechaInicio='" + fechaInicio + '\'' +
	                ", fechaFin='" + fechaFin + '\'' +
	                ", concepto='" + concepto + '\'' +
	                '}';
	    }
	}
}

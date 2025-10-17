package com.axtel.interfaces.controlAsistencia.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axtel.interfaces.controlAsistencia.service.FormatoService;

@RestController
@RequestMapping("/api/formatos")
public class FormatoController {

	private final FormatoService formatoService;

	public FormatoController(FormatoService formatoService) {
		this.formatoService = formatoService;
	}
	
	@GetMapping("/verifica")
	    public ResponseEntity<Boolean> tieneOtroFormato(
	            @RequestParam() Integer numeroEmpleado,
	            @RequestParam("fechaInicio") String fechaInicioStr,
	            @RequestParam("fechaFin") String fechaFinStr
	    ) {
	        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
	        LocalDate fechaFin = LocalDate.parse(fechaFinStr);

	        boolean existe = formatoService.tieneOtroFormato(numeroEmpleado, fechaInicio, fechaFin);
	        return ResponseEntity.ok(existe);
	    }
}

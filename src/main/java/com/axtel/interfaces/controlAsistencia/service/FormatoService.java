package com.axtel.interfaces.controlAsistencia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axtel.interfaces.controlAsistencia.entity.Formato;
import com.axtel.interfaces.controlAsistencia.repository.FormatoRepository;

@Service
public class FormatoService {

	private final FormatoRepository formatoRepository;

	public FormatoService(FormatoRepository formatoRepository) {
		this.formatoRepository = formatoRepository;
	}

	public List<Formato> findAll() {
		return formatoRepository.findAll();
	}

	public Optional<Formato> findById(Integer id) {
		return formatoRepository.findById(id);
	}

	public Formato save(Formato formato) {
		return formatoRepository.save(formato);
	}

	public void deleteById(Integer id) {
		formatoRepository.deleteById(id);
	}

	public boolean tieneOtroFormato(Integer numeroEmpleado, LocalDate fechaInicio, LocalDate fechaFin) {
		return formatoRepository.existeOtroFormatoEnRango(numeroEmpleado, fechaInicio, fechaFin);
	}
}

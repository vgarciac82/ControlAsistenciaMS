package com.axtel.interfaces.controlAsistencia.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axtel.interfaces.controlAsistencia.entity.Formato;

@Repository
public interface FormatoRepository extends JpaRepository<Formato, Integer> {

	@Query("""
			    SELECT COUNT(f) > 0 FROM Formato f
			     WHERE f.numeroEmpleado = :numeroEmpleado
			       AND (
			            (:fecha BETWEEN f.fechaInicio AND f.fechaFin)
			            OR (:fechaFin BETWEEN f.fechaInicio AND f.fechaFin)
			            OR (f.fechaInicio BETWEEN :fecha AND :fechaFin)
			            OR (f.fechaFin BETWEEN :fecha AND :fechaFin)
			       )
			       AND (f.bAceptado IS NULL OR f.bAceptado = TRUE)
			       AND (f.bAutoriza IS NULL OR f.bAutoriza = TRUE)
			""")
	
	boolean existeOtroFormatoEnRango(@Param("numeroEmpleado") Integer numeroEmpleado, @Param("fecha") LocalDate fecha,
			@Param("fechaFin") LocalDate fechaFin);

}

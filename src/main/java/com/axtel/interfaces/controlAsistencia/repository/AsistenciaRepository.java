package com.axtel.interfaces.controlAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axtel.interfaces.controlAsistencia.entity.Formato;

@Repository
public interface AsistenciaRepository extends JpaRepository<Formato, Integer> {

	@Modifying
	@Query(value = "CALL sp_revertir_viatico(:idEmpleado, :fechaInicio, :fechaFin)", nativeQuery = true)
	int revertirAsistencia(
			@Param("idEmpleado") Integer idEmpleado, 
			@Param("fechaInicio") String fechaInicio, 
			@Param("fechaFin") String fechaFin
			);
	
	@Modifying
	@Query(value = "CALL sp_asistenciasViaticos(:idEmpleado,:fechaInicio,:fechaFin,:concepto)", nativeQuery = true)
	 int actualizarAsistencia(
	            @Param("idEmpleado") Integer idEmpleado,
	            @Param("fechaInicio") String fechaInicio,
	            @Param("fechaFin") String fechaFin,
	            @Param("concepto") String concepto
	    );

}

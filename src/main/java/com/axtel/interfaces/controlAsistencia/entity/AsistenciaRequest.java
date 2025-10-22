package com.axtel.interfaces.controlAsistencia.entity;

/**
 * Clase interna para representar la petición de reversión de asistencia
 */
public class AsistenciaRequest {
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

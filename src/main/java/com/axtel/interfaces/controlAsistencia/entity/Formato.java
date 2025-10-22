package com.axtel.interfaces.controlAsistencia.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "formato")
public class Formato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "numero_empleado")
	private Integer numeroEmpleado;

	@Column(name = "cat_tipo_formato_id")
	private Integer catTipoFormatoId;

	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;

	@Column(name = "fecha_fin")
	private LocalDate fechaFin;

	@Column(name = "fecha_insertado", nullable = false)
	private LocalDateTime fechaInsertado;

	@Column(name = "user_id_insertado")
	private Integer userIdInsertado;

	@Column(name = "str_ip_insertado", length = 20)
	private String strIpInsertado;

	@Column(name = "fecha_modificado", nullable = false)
	private LocalDateTime fechaModificado;

	@Column(name = "user_id_modificado")
	private Integer userIdModificado;

	@Column(name = "str_ip_modificado", length = 20)
	private String strIpModificado;

	@Column(name = "bAceptado")
	private Boolean bAceptado;

	@Column(name = "bAutoriza")
	private Boolean bAutoriza;

	// ====== Getters y Setters ======

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public Integer getCatTipoFormatoId() {
		return catTipoFormatoId;
	}

	public void setCatTipoFormatoId(Integer catTipoFormatoId) {
		this.catTipoFormatoId = catTipoFormatoId;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalDateTime getFechaInsertado() {
		return fechaInsertado;
	}

	public void setFechaInsertado(LocalDateTime fechaInsertado) {
		this.fechaInsertado = fechaInsertado;
	}

	public Integer getUserIdInsertado() {
		return userIdInsertado;
	}

	public void setUserIdInsertado(Integer userIdInsertado) {
		this.userIdInsertado = userIdInsertado;
	}

	public String getStrIpInsertado() {
		return strIpInsertado;
	}

	public void setStrIpInsertado(String strIpInsertado) {
		this.strIpInsertado = strIpInsertado;
	}

	public LocalDateTime getFechaModificado() {
		return fechaModificado;
	}

	public void setFechaModificado(LocalDateTime fechaModificado) {
		this.fechaModificado = fechaModificado;
	}

	public Integer getUserIdModificado() {
		return userIdModificado;
	}

	public void setUserIdModificado(Integer userIdModificado) {
		this.userIdModificado = userIdModificado;
	}

	public String getStrIpModificado() {
		return strIpModificado;
	}

	public void setStrIpModificado(String strIpModificado) {
		this.strIpModificado = strIpModificado;
	}

	public Boolean getBAceptado() {
		return bAceptado;
	}

	public void setBAceptado(Boolean bAceptado) {
		this.bAceptado = bAceptado;
	}

	public Boolean getBAutoriza() {
		return bAutoriza;
	}

	public void setBAutoriza(Boolean bAutoriza) {
		this.bAutoriza = bAutoriza;
	}

}

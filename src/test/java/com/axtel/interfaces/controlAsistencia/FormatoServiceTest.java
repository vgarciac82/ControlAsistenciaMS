package com.axtel.interfaces.controlAsistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.axtel.interfaces.controlAsistencia.entity.Formato;
import com.axtel.interfaces.controlAsistencia.repository.FormatoRepository;
import com.axtel.interfaces.controlAsistencia.service.FormatoService;

class FormatoServiceTest {

    @Mock
    private FormatoRepository formatoRepository;

    @InjectMocks
    private FormatoService formatoService;

    private Formato formato;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        formato = new Formato();
        formato.setId(1);
        // Si la entidad tiene más campos, puedes inicializarlos aquí
    }

    @Test
    void testFindAll() {
        List<Formato> formatos = Arrays.asList(formato);
        when(formatoRepository.findAll()).thenReturn(formatos);

        List<Formato> result = formatoService.findAll();

        assertEquals(1, result.size());
        assertEquals(formato, result.get(0));
        verify(formatoRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Exists() {
        when(formatoRepository.findById(1)).thenReturn(Optional.of(formato));

        Optional<Formato> result = formatoService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(formato, result.get());
        verify(formatoRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotExists() {
        when(formatoRepository.findById(2)).thenReturn(Optional.empty());

        Optional<Formato> result = formatoService.findById(2);

        assertFalse(result.isPresent());
        verify(formatoRepository, times(1)).findById(2);
    }

    @Test
    void testSave() {
        when(formatoRepository.save(formato)).thenReturn(formato);

        Formato result = formatoService.save(formato);

        assertNotNull(result);
        assertEquals(formato, result);
        verify(formatoRepository, times(1)).save(formato);
    }

    @Test
    void testDeleteById() {
        doNothing().when(formatoRepository).deleteById(1);

        formatoService.deleteById(1);

        verify(formatoRepository, times(1)).deleteById(1);
    }

    @Test
    void testTieneOtroFormato_True() {
        Integer numeroEmpleado = 123;
        LocalDate fechaInicio = LocalDate.of(2025, 1, 1);
        LocalDate fechaFin = LocalDate.of(2025, 1, 10);

        when(formatoRepository.existeOtroFormatoEnRango(numeroEmpleado, fechaInicio, fechaFin)).thenReturn(true);

        boolean result = formatoService.tieneOtroFormato(numeroEmpleado, fechaInicio, fechaFin);

        assertTrue(result);
        verify(formatoRepository, times(1))
                .existeOtroFormatoEnRango(numeroEmpleado, fechaInicio, fechaFin);
    }

    @Test
    void testTieneOtroFormato_False() {
        Integer numeroEmpleado = 123;
        LocalDate fechaInicio = LocalDate.of(2025, 1, 1);
        LocalDate fechaFin = LocalDate.of(2025, 1, 10);

        when(formatoRepository.existeOtroFormatoEnRango(numeroEmpleado, fechaInicio, fechaFin)).thenReturn(false);

        boolean result = formatoService.tieneOtroFormato(numeroEmpleado, fechaInicio, fechaFin);

        assertFalse(result);
        verify(formatoRepository, times(1))
                .existeOtroFormatoEnRango(numeroEmpleado, fechaInicio, fechaFin);
    }
    
    @Test
    void testValidarEmpleadoEstaDeVacaciones() {
        int numeroEmpleado = 45;
        when(formatoRepository.tieneFormatoVacaciones(numeroEmpleado)).thenReturn(0);

        long resultado = formatoService.tieneFormatoVacacionesPorEmpleado(numeroEmpleado);

        assertEquals(2L, resultado);
        verify(formatoRepository, times(1)).tieneFormatoVacaciones(numeroEmpleado);
    }
}

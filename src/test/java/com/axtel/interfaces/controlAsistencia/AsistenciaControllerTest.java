package com.axtel.interfaces.controlAsistencia;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.axtel.interfaces.controlAsistencia.controller.AsistenciaController;
import com.axtel.interfaces.controlAsistencia.service.AsistenciaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AsistenciaController.class)
class AsistenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean   
    private AsistenciaService asistenciaService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    // -----------------------------------------------------------
    // TEST: /api/asistencia/actualizar
    // -----------------------------------------------------------
    @Test
    @DisplayName("✅ Debería actualizar asistencia exitosamente")
    void testActualizarAsistencia_Success() throws Exception {
        // Arrange
        AsistenciaController.AsistenciaRequest request = new AsistenciaController.AsistenciaRequest(
                45, "2025-10-01", "2025-10-05", "Comisión en Guadalajara");

        when(asistenciaService.actualizarAsistencia(45, "2025-10-01", "2025-10-05", "Comisión en Guadalajara"))
                .thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/asistencia/actualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.idEmpleado").value(45))
                .andExpect(jsonPath("$.concepto").value("Comisión en Guadalajara"))
                .andExpect(jsonPath("$.message").value("Asistencia revertida exitosamente"));

        verify(asistenciaService, times(1))
                .actualizarAsistencia(45, "2025-10-01", "2025-10-05", "Comisión en Guadalajara");
    }

    // -----------------------------------------------------------
    // TEST: /api/asistencia/revertir
    // -----------------------------------------------------------
    @Test
    @DisplayName("✅ Debería revertir asistencia exitosamente")
    void testRevertirAsistencia_Success() throws Exception {
        // Arrange
        AsistenciaController.AsistenciaRequest request =
                new AsistenciaController.AsistenciaRequest(45, "2025-10-01", "2025-10-05", null);

        when(asistenciaService.revertirAsistencia(45, "2025-10-01", "2025-10-05"))
                .thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/asistencia/revertir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Asistencia revertida exitosamente"));

        verify(asistenciaService, times(1))
                .revertirAsistencia(45, "2025-10-01", "2025-10-05");
    }

    // -----------------------------------------------------------
    // TEST: Caso de error interno
    // -----------------------------------------------------------
    @Test
    @DisplayName("❌ Debería manejar excepción interna en actualizarAsistencia")
    void testActualizarAsistencia_InternalError() throws Exception {
        // Arrange
        AsistenciaController.AsistenciaRequest request =
                new AsistenciaController.AsistenciaRequest(45, "2025-10-01", "2025-10-05", "Comisión");

        when(asistenciaService.actualizarAsistencia(anyInt(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Fallo en BD"));

        // Act & Assert
        mockMvc.perform(post("/api/asistencia/actualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error").value("INTERNAL_ERROR"))
                .andExpect(jsonPath("$.message").value("Error interno del servidor: Fallo en BD"));

        verify(asistenciaService, times(1))
                .actualizarAsistencia(anyInt(), anyString(), anyString(), anyString());
    }
}

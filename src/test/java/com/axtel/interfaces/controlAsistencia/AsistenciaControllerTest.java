package com.axtel.interfaces.controlAsistencia;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.axtel.interfaces.controlAsistencia.service.AsistenciaService;

@SpringBootTest
@AutoConfigureMockMvc
class AsistenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Simulamos el servicio para que no levante toda la app
    @MockBean
    private AsistenciaService asistenciaService;

    @Test
    void testActualizarAsistencia() throws Exception {
        // Simular comportamiento del servicio
        when(asistenciaService.actualizarAsistencia(anyInt(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        String requestBody = """
            {
              "idEmpleado": "45",
              "fechaInicio": "2025-10-01",
              "fechaFin": "2025-10-10",
              "concepto": "VIATICO"
            }
            """;

        mockMvc.perform(post("/api/asistencia/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.idEmpleado").value(45))
                .andExpect(jsonPath("$.concepto").value("VIATICO"));
    }

    @Test
    void testRevertirAsistencia() throws Exception {
        when(asistenciaService.revertirAsistencia(anyInt(), anyString(), anyString()))
                .thenReturn(true);

        String requestBody = """
            {
              "idEmpleado": "45",
              "fechaInicio": "2025-09-01",
              "fechaFin": "2025-09-05"
            }
            """;

        mockMvc.perform(post("/api/asistencia/revertir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.idEmpleado").value(45));
    }
}

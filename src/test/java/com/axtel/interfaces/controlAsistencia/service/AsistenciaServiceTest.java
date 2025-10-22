package com.axtel.interfaces.controlAsistencia.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.axtel.interfaces.controlAsistencia.repository.AsistenciaRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DisplayName("Pruebas de Integración Real para AsistenciaService")
class AsistenciaServiceTest {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private AsistenciaService asistenciaService;

    private Integer idEmpleadoValido;
    private String fechaInicioValida;
    private String fechaFinValida;
    private String conceptoValido;

    @BeforeEach
    void setUp() {
        idEmpleadoValido = 123;
        fechaInicioValida = "2024-01-01";
        fechaFinValida = "2024-01-31";
        conceptoValido = "Viáticos de viaje";
    }

    // ========== PRUEBAS REALES PARA revertirAsistencia ==========

    @Test
    @DisplayName("revertirAsistencia - Prueba real con stored procedure existente")
    void revertirAsistencia_PruebaRealConStoredProcedure() {
        System.out.println("\n=== PRUEBA REAL: sp_revertir_viatico ===");
        
        // Given - Usamos datos reales que pueden existir en la base de datos
        Integer idEmpleadoTest = 1; // ID que debería existir en la base de datos
        String fechaInicioTest = "2024-01-01";
        String fechaFinTest = "2024-01-31";

        try {
            // When - Ejecutamos el stored procedure real
            boolean resultado = asistenciaService.revertirAsistencia(idEmpleadoTest, fechaInicioTest, fechaFinTest);

            // Then - Verificamos que se ejecutó sin errores
            assertNotNull(resultado); // Debe devolver un valor booleano
            
            System.out.println("✅ ÉXITO: Stored procedure sp_revertir_viatico ejecutado correctamente");
            System.out.println("   - ID Empleado: " + idEmpleadoTest);
            System.out.println("   - Fecha Inicio: " + fechaInicioTest);
            System.out.println("   - Fecha Fin: " + fechaFinTest);
            System.out.println("   - Resultado: " + resultado);
            
        } catch (Exception e) {
            System.out.println("❌ ERROR: Fallo en stored procedure sp_revertir_viatico");
            System.out.println("   - Error: " + e.getMessage());
            System.out.println("   - Posible causa: El stored procedure fue modificado por otro equipo");
            fail("El stored procedure sp_revertir_viatico falló: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("revertirAsistencia - Prueba con ID empleado inexistente")
    void revertirAsistencia_PruebaConIdEmpleadoInexistente() {
        System.out.println("\n=== PRUEBA: ID empleado inexistente ===");
        
        // Given - Usamos un ID que probablemente no existe
        Integer idEmpleadoInexistente = 99999;
        String fechaInicioTest = "2024-01-01";
        String fechaFinTest = "2024-01-31";

        try {
            // When - Ejecutamos con ID inexistente
            boolean resultado = asistenciaService.revertirAsistencia(idEmpleadoInexistente, fechaInicioTest, fechaFinTest);

            // Then - Debe ejecutarse sin errores (el SP maneja IDs inexistentes)
            assertNotNull(resultado);
            
            System.out.println("✅ ÉXITO: Stored procedure maneja correctamente ID inexistente");
            System.out.println("   - ID Empleado: " + idEmpleadoInexistente);
            System.out.println("   - Resultado: " + resultado);
            
        } catch (Exception e) {
            System.out.println("❌ ERROR: Fallo con ID empleado inexistente");
            System.out.println("   - Error: " + e.getMessage());
            fail("El stored procedure no maneja correctamente IDs inexistentes: " + e.getMessage());
        }
    }

   

    @Test
    @DisplayName("revertirAsistencia - Validación de parámetros nulos")
    void revertirAsistencia_ValidacionParametrosNulos() {
        System.out.println("\n=== PRUEBA: Validación de parámetros ===");
        
        // Test 1: ID empleado null
        try {
            asistenciaService.revertirAsistencia(null, fechaInicioValida, fechaFinValida);
            fail("Debería haber lanzado IllegalArgumentException para ID null");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ ÉXITO: Validación ID null funciona: " + e.getMessage());
        }

        // Test 2: ID empleado 0
        try {
            asistenciaService.revertirAsistencia(0, fechaInicioValida, fechaFinValida);
            fail("Debería haber lanzado IllegalArgumentException para ID 0");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ ÉXITO: Validación ID 0 funciona: " + e.getMessage());
        }

        // Test 3: Fecha inicio null
        try {
            asistenciaService.revertirAsistencia(idEmpleadoValido, null, fechaFinValida);
            fail("Debería haber lanzado IllegalArgumentException para fecha inicio null");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ ÉXITO: Validación fecha inicio null funciona: " + e.getMessage());
        }

        // Test 4: Fecha fin null
        try {
            asistenciaService.revertirAsistencia(idEmpleadoValido, fechaInicioValida, null);
            fail("Debería haber lanzado IllegalArgumentException para fecha fin null");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ ÉXITO: Validación fecha fin null funciona: " + e.getMessage());
        }
    }

    // ========== PRUEBAS REALES PARA actualizarAsistencia ==========

    @Test
    @DisplayName("actualizarAsistencia - Prueba real con stored procedure existente")
    void actualizarAsistencia_PruebaRealConStoredProcedure() {
        System.out.println("\n=== PRUEBA REAL: sp_asistenciasViaticos ===");
        
        // Given - Usamos datos reales
        Integer idEmpleadoTest = 1;
        String fechaInicioTest = "2024-01-01";
        String fechaFinTest = "2024-01-31";
        String conceptoTest = "Viáticos de viaje";

        try {
            // When - Ejecutamos el stored procedure real
            boolean resultado = asistenciaService.actualizarAsistencia(idEmpleadoTest, fechaInicioTest, fechaFinTest, conceptoTest);

            // Then - Verificamos que se ejecutó sin errores
            assertNotNull(resultado);
            
            System.out.println("✅ ÉXITO: Stored procedure sp_asistenciasViaticos ejecutado correctamente");
            System.out.println("   - ID Empleado: " + idEmpleadoTest);
            System.out.println("   - Fecha Inicio: " + fechaInicioTest);
            System.out.println("   - Fecha Fin: " + fechaFinTest);
            System.out.println("   - Concepto: " + conceptoTest);
            System.out.println("   - Resultado: " + resultado);
            
        } catch (Exception e) {
            System.out.println("❌ ERROR: Fallo en stored procedure sp_asistenciasViaticos");
            System.out.println("   - Error: " + e.getMessage());
            System.out.println("   - Posible causa: El stored procedure fue modificado por otro equipo");
            fail("El stored procedure sp_asistenciasViaticos falló: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("actualizarAsistencia - Prueba con concepto null")
    void actualizarAsistencia_PruebaConConceptoNull() {
        System.out.println("\n=== PRUEBA: Concepto null ===");
        
        try {
            boolean resultado = asistenciaService.actualizarAsistencia(idEmpleadoValido, fechaInicioValida, fechaFinValida, null);
            assertNotNull(resultado);
            
            System.out.println("✅ ÉXITO: Stored procedure acepta concepto null");
            System.out.println("   - Resultado: " + resultado);
            
        } catch (Exception e) {
            System.out.println("❌ ERROR: Stored procedure no acepta concepto null");
            System.out.println("   - Error: " + e.getMessage());
            fail("El stored procedure debería aceptar concepto null: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("actualizarAsistencia - Prueba con concepto vacío")
    void actualizarAsistencia_PruebaConConceptoVacio() {
        System.out.println("\n=== PRUEBA: Concepto vacío ===");
        
        try {
            boolean resultado = asistenciaService.actualizarAsistencia(idEmpleadoValido, fechaInicioValida, fechaFinValida, "");
            assertNotNull(resultado);
            
            System.out.println("✅ ÉXITO: Stored procedure acepta concepto vacío");
            System.out.println("   - Resultado: " + resultado);
            
        } catch (Exception e) {
            System.out.println("❌ ERROR: Stored procedure no acepta concepto vacío");
            System.out.println("   - Error: " + e.getMessage());
            fail("El stored procedure debería aceptar concepto vacío: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("actualizarAsistencia - Validación de parámetros")
    void actualizarAsistencia_ValidacionParametros() {
        System.out.println("\n=== PRUEBA: Validación de parámetros actualizarAsistencia ===");
        
        // Test: ID empleado negativo
        try {
            asistenciaService.actualizarAsistencia(-1, fechaInicioValida, fechaFinValida, conceptoValido);
            fail("Debería haber lanzado IllegalArgumentException para ID negativo");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ ÉXITO: Validación ID negativo funciona: " + e.getMessage());
        }
    }

    // ========== PRUEBAS DE DETECCIÓN DE CAMBIOS ==========

    @Test
    @DisplayName("DETECCIÓN - Verificar que ambos stored procedures existen y funcionan")
    void deteccion_CambioStoredProcedures() {
        System.out.println("\n=== PRUEBA DE DETECCIÓN DE CAMBIOS EN STORED PROCEDURES ===");
        
        // Esta prueba detectará si el otro equipo modificó los stored procedures
        // y causó que fallen las llamadas
        
        boolean revertirFunciona = false;
        boolean actualizarFunciona = false;
        
        try {
            asistenciaService.revertirAsistencia(1, "2024-01-01", "2024-01-31");
            revertirFunciona = true;
            System.out.println("✅ sp_revertir_viatico: FUNCIONANDO");
        } catch (Exception e) {
            System.out.println("❌ sp_revertir_viatico: FALLA - " + e.getMessage());
            System.out.println("   ⚠️  POSIBLE CAMBIO POR OTRO EQUIPO");
        }
        
        try {
            asistenciaService.actualizarAsistencia(1, "2024-01-01", "2024-01-31", "Test");
            actualizarFunciona = true;
            System.out.println("✅ sp_asistenciasViaticos: FUNCIONANDO");
        } catch (Exception e) {
            System.out.println("❌ sp_asistenciasViaticos: FALLA - " + e.getMessage());
            System.out.println("   ⚠️  POSIBLE CAMBIO POR OTRO EQUIPO");
        }
        
        if (!revertirFunciona || !actualizarFunciona) {
            System.out.println("\n🚨 ALERTA: Uno o más stored procedures han fallado");
            System.out.println("   - Verificar cambios realizados por otro equipo");
            System.out.println("   - Revisar parámetros y estructura de los SP");
            fail("Los stored procedures han fallado - posible cambio por otro equipo");
        } else {
            System.out.println("\n✅ TODOS LOS STORED PROCEDURES FUNCIONAN CORRECTAMENTE");
        }
    }
}
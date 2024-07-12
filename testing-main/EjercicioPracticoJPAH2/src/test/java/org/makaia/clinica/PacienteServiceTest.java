package org.makaia.clinica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makaia.clinica.exceptions.ApiException;
import org.makaia.clinica.models.Paciente;
import org.makaia.clinica.repositories.PacienteRepository;
import org.makaia.clinica.services.PacienteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PacienteServiceTest{
        //agrego dependencias
        private PacienteService pacienteService;
        private PacienteRepository pacienteRepository;

        @BeforeEach
        public void setUp(){
                //Mock -> simulaciones
                this.pacienteRepository = mock(PacienteRepository.class);
            this.pacienteService = new PacienteService(pacienteRepository);
        }

        @Test
        public void crearPacienteCuandoEsNulo(){
                //Arrange
                Paciente paciente = null;
                //Act-assert
                assertThrows(ApiException.class, () -> pacienteService.crear(paciente));
        }

        @Test
        public void crearPacienteCuandoNombreEsNulo(){
                //Arrange
                Paciente paciente = new Paciente();
                paciente.setDni(133);
                paciente.setApellido("Lala");
                //Act-assert
                assertThrows(ApiException.class, () -> pacienteService.crear(paciente));
        }

        @Test void crearUsuarioCuandoExisteEnBd(){
                //Arrange
                Paciente paciente = new Paciente(123, "Juan", "Salazar", "sab");
                when(pacienteRepository.existsById(any())).thenReturn(true);
                //Act-assert
                assertThrows(ApiException.class, () -> pacienteService.crear(paciente));
        }

        @Test void crearUsuarioNuevo(){
                //Arrange
                Paciente paciente = new Paciente(123, "Juan", "Salazar", "sab");
                when(pacienteRepository.existsById(any())).thenReturn(false);
                when(pacienteRepository.save(any())).thenReturn(paciente);
                //Act
                Paciente paciente1 = pacienteService.crear(paciente);
                //Assert
                assertEquals(123, paciente1.getDni());
        }
}

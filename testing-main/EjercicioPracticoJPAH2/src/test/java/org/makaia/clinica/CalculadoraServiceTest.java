package org.makaia.clinica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makaia.clinica.services.CalculadoraService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraServiceTest {

    //agregar las dependencias de la clase que voy testear.
    private CalculadoraService calculadoraService;

    //Por lo general aca no aplica el concepto de Inyeccion de dependencias.
    //Junit 5!
    //Crear las dependencias del testing.
    @BeforeEach
    public void setUp(){
        this.calculadoraService = new CalculadoraService();
    }

    //Generar los casos de prueba
    //casos de prueba para sumar.
    @Test
    public void sumarCuenadoPrimerNumeroEsZero(){
        //Arrange preparar los datos
        int a = 0;
        int b = 10;
        //Act -> accion
        int resultado = this.calculadoraService.sumar(a,b);
        //Assert (que espero, el resultado)
        assertEquals(0, resultado);
    }

    @Test
    public void sumarDosNumerosNegativos(){
        //Arrange
        int a = -1;
        int b = -3;
        //Act
        int resultado = this.calculadoraService.sumar(a,b);
        //Assert
        assertEquals(-4,resultado);
    }

    @Test
    public void sumarDosNumerosPositivos(){
        //Arrange
        int a = 1;
        int b = 3;
        //Act
        int resultado = this.calculadoraService.sumar(a,b);
        //Assert
        assertEquals(4,resultado);
    }

    @Test
    public void sumarDosNumerosPositivosNegativos(){
        //Arrange
        int a = -1;
        int b = 3;
        //Act
        int resultado = this.calculadoraService.sumar(a,b);
        //Assert
        assertEquals(2,resultado);
    }

    @Test
    public void dividirPorZero(){
        //Arrange
        int a = 10;
        int b = 0;
        //Act-assert
       Exception e = assertThrows(ArithmeticException.class, () -> this.calculadoraService.dividir(a,b));
       assertEquals("Division by zero is not allowed.", e.getMessage());
    }
}

package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        // Then ...
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidEmail(){
        //Given un email inálido
        RegisterRequest registro = new RegisterRequest(
                "Ignacio","nachoGmail.com",
                Role.USER, "Nacho1234");
        //When
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);
        // Then Debe haber una violación por el email
        assertEquals(1, violations.size()); //que la violación sea de un solo campo y del campo email
        assertTrue(violations.iterator().next().getPropertyPath().toString().equals("email"));
    }

    @Test
    public void testInvalidPassword(){
        //Given un email inálido
        RegisterRequest registro = new RegisterRequest(
                "Ignacio","nacho@Gmail.com",
                Role.USER, "mal");
        //When
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);
        // Then Debe haber una violación por el password
        assertEquals(1, violations.size()); //que la violación sea de un solo campo y del campo password
        assertTrue(violations.iterator().next().getPropertyPath().toString().equals("password"));
    }




}
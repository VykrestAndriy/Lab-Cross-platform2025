package org.example.newlib.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {

    private Request request;

    @BeforeEach
    void setUp() {
        request = new Request(1L, "TestDestination", 1000, "TestCargo");
    }

    @Test
    void testGetDescription() {
        assertEquals("Перевезти 1000кг TestCargo до TestDestination", request.getDescription());
    }

    @Test
    void testRequestCreation() {
        assertNotNull(request);
        assertEquals("TestDestination", request.getDestination());
        assertEquals(1000, request.getCargoWeight());
    }
}
package org.example.newlib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutobaseAppTest {

    @Test
    void testAppMainMethod() {
        assertDoesNotThrow(() -> AutobaseApp.main(new String[]{}));
    }
}
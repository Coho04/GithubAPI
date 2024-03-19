package de.goldendeveloper.githubapi.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHStateTest {

    @Test
    void shouldReturnCorrectStringForOpen() {
        GHState ghState = GHState.OPEN;

        assertEquals("open", ghState.toString());
    }

    @Test
    void shouldReturnCorrectStringForClosed() {
        GHState ghState = GHState.CLOSED;

        assertEquals("closed", ghState.toString());
    }

    @Test
    void shouldReturnCorrectStringForAll() {
        GHState ghState = GHState.ALL;

        assertEquals("all", ghState.toString());
    }

    @Test
    void shouldReturnCorrectEnumForOpen() {
        GHState ghState = GHState.fromString("OPEN");

        assertEquals(GHState.OPEN, ghState);
    }

    @Test
    void shouldReturnCorrectEnumForClosed() {
        GHState ghState = GHState.fromString("CLOSED");

        assertEquals(GHState.CLOSED, ghState);
    }

    @Test
    void shouldReturnCorrectEnumForAll() {
        GHState ghState = GHState.fromString("ALL");

        assertEquals(GHState.ALL, ghState);
    }

    @Test
    void shouldThrowExceptionForInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> GHState.fromString("INVALID"));
    }
}
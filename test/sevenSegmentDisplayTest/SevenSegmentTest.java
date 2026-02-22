package sevenSegmentDisplayTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sevenSegmentDisplay.SevenSegment;

import static org.junit.jupiter.api.Assertions.*;

public class SevenSegmentTest {
    SevenSegment sevenSegment;
    @BeforeEach
    public void setUp() {
        sevenSegment = new SevenSegment();
    }

    @Test
    public void testThatPowerIsOffByDefault() {
        assertFalse(sevenSegment.getPowerIsOn());
    }

    @Test
    public void testThatPowerTurnsOnWhenLastBitIsOne() {
        assertTrue(sevenSegment.setPowerIsOn("00000001"));
    }

    @Test
    public void testThatPowerCanBeTurnedOff() {
        sevenSegment.setPowerIsOn("00000001");
        assertFalse(sevenSegment.setPowerIsOn("00000000"));
    }

    @Test
    public void testThatPowerStateIsStoredCorrectly() {
        sevenSegment.setPowerIsOn("00000001");
        assertTrue(sevenSegment.getPowerIsOn());
    }

    @Test
    public void testThatOnlyLastBitDeterminesPowerState() {
        assertTrue(sevenSegment.setPowerIsOn("11111111"));
        assertFalse(sevenSegment.setPowerIsOn("11111110"));
    }

    @Test
    public void testThatEmptyStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn(""));
    }

    @Test
    public void testThatStringLongerThanEightCharactersThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("000000000"));
    }

    @Test
    public void testThatNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn(null));
    }

    @Test
    public void testThatStringShorterThanEightCharactersThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("0000000"));
    }

    @Test
    public void testThatNonBinaryCharactersThrowException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("0000000X"));
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("X0000001"));
    }

    @Test
    public void testThatDefaultSegmentStringIsAllZeros() {
        assertEquals("00000000", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThatDisplaySetsBinaryPatternForZero() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("11111100");
        assertEquals("11111100", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThatDisplaySetsBinaryPatternForOne() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("01100000");
        assertEquals("01100000", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThatDisplaySetsInputValue() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("10110110");
        assertEquals("10110110", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThatDisplayWhenPowerIsOffThrowsException() {
        assertThrows(IllegalStateException.class, () -> sevenSegment.display("11111100"));
    }

    @Test
    public void testThatDisplayWithInvalidInputThrowsException() {
        sevenSegment.setPowerIsOn("00000001");
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.display("1111110X"));
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.display("1111110"));
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.display(null));
    }

    @Test
    public void testThatPrintWorksWhenPowerIsOn() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("11111100");
        assertDoesNotThrow(() -> sevenSegment.print());
    }

    @Test
    public void testThatPrintWhenPowerIsOffThrowsException() {
        assertThrows(IllegalStateException.class, () -> sevenSegment.print());
    }
}

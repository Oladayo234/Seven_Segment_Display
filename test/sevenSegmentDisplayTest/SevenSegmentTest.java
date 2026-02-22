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
    public void testThatPower_IsOff_ByDefault() {
        assertFalse(sevenSegment.getPowerIsOn());
    }

    @Test
    public void testThat_PowerTurnsOn_WhenLastBitIsOne() {
        assertTrue(sevenSegment.setPowerIsOn("00000001"));
    }

    @Test
    public void testThat_PowerCanBe_TurnedOff() {
        sevenSegment.setPowerIsOn("00000001");
        assertFalse(sevenSegment.setPowerIsOn("00000000"));
    }

    @Test
    public void testThat_PowerState_IsStoredCorrectly() {
        sevenSegment.setPowerIsOn("00000001");
        assertTrue(sevenSegment.getPowerIsOn());
    }

    @Test
    public void testThatOnly_LastBit_DeterminesPowerState() {
        assertTrue(sevenSegment.setPowerIsOn("11111111"));
        assertFalse(sevenSegment.setPowerIsOn("11111110"));
    }

    @Test
    public void testThat_EmptyString_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn(""));
    }

    @Test
    public void testThat_StringLongerThan_EightCharacters_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("000000000"));
    }

    @Test
    public void testThat_Null_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn(null));
    }

    @Test
    public void testThat_StringShorterThan_EightCharacters_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("0000000"));
    }

    @Test
    public void testThat_NonBinaryCharacters_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("0000000X"));
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.setPowerIsOn("X0000001"));
    }

    @Test
    public void testThat_DefaultSegment_StringIsAllZeros() {
        assertEquals("00000000", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThat_DisplaySetsBinary_PatternForZero() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("11111100");
        assertEquals("11111100", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThat_DisplaySetsBinary_PatternForOne() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("01100000");
        assertEquals("01100000", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThat_DisplaySets_InputValue() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("10110110");
        assertEquals("10110110", sevenSegment.getSevenSegment());
    }

    @Test
    public void testThat_DisplayWhenPowerIsOff_ThrowsException() {
        assertThrows(IllegalStateException.class, () -> sevenSegment.display("11111100"));
    }

    @Test
    public void testThat_DisplayWithInvalidInput_ThrowsException() {
        sevenSegment.setPowerIsOn("00000001");
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.display("1111110X"));
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.display("1111110"));
        assertThrows(IllegalArgumentException.class, () -> sevenSegment.display(null));
    }

    @Test
    public void testThat_PrintWorksWhen_PowerIsOn() {
        sevenSegment.setPowerIsOn("00000001");
        sevenSegment.display("11111100");
        assertDoesNotThrow(() -> sevenSegment.print());
    }

    @Test
    public void testThat_PrintWhenPowerIsOff_ThrowsException() {
        assertThrows(IllegalStateException.class, () -> sevenSegment.print());
    }
}

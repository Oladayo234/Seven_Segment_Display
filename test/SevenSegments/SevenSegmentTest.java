package SevenSegments;

import sevenSegment.Segment;
import sevenSegment.SevenSegments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SevenSegmentTest {
    SevenSegments sevenSegments;

    @BeforeEach
    public void startWithThis() {
        sevenSegments = new SevenSegments();
        for (Segment segment : Segment.values()) {
            segment.setState(false);
        }
        sevenSegments.setMasterSwitch(true);
    }

    @Test
    public void testThatAllSegmentsAreOffInitially() {
        for (Segment segment : Segment.values()) {
            assertFalse(sevenSegments.getSegment(segment));
        }
    }

    @Test
    public void testThatSegmentAIsOn() {
        assertFalse(sevenSegments.getSegment(Segment.A));
        sevenSegments.setSegment(Segment.A, true);
        assertTrue(sevenSegments.getSegment(Segment.A));
    }

    @Test
    public void testThatSegmentACanBeTurnedOff() {
        assertFalse(sevenSegments.getSegment(Segment.A));
        sevenSegments.setSegment(Segment.A, true);
        assertTrue(sevenSegments.getSegment(Segment.A));
        sevenSegments.setSegment(Segment.A, false);
        assertFalse(sevenSegments.getSegment(Segment.A));
    }

    @Test
    public void testThatSegmentBCanBeTurnedOff() {
        assertFalse(sevenSegments.getSegment(Segment.B));
        sevenSegments.setSegment(Segment.B, true);
        assertTrue(sevenSegments.getSegment(Segment.B));
        sevenSegments.setSegment(Segment.B, false);
        assertFalse(sevenSegments.getSegment(Segment.B));
    }

    @Test
    public void testMasterSwitchStateIsPreserved() {
        assertTrue(sevenSegments.getMasterSwitch());
        sevenSegments.setMasterSwitch(false);
        assertFalse(sevenSegments.getMasterSwitch());
        sevenSegments.setMasterSwitch(true);
        assertTrue(sevenSegments.getMasterSwitch());
    }

    @Test
    public void testMasterSwitchTurnsOffAllSegments() {
        sevenSegments.setDigit("1111111");
        for (Segment segment : Segment.values()) {
            assertTrue(sevenSegments.getSegment(segment));
        }
        sevenSegments.setMasterSwitch(false);
        for (Segment segment : Segment.values()) {
            assertFalse(sevenSegments.getSegment(segment));
        }
    }

    @Test
    public void testMasterSwitchCanTurnDisplayBackOn() {
        sevenSegments.setDigit("1111111");
        sevenSegments.setMasterSwitch(false);
        sevenSegments.setMasterSwitch(true);
        for (Segment segment : Segment.values()) {
            assertTrue(sevenSegments.getSegment(segment));
        }
    }

    @Test
    public void testSegmentsRetainStateWhenMasterSwitchOff() {
        sevenSegments.setDigit("1111111");
        sevenSegments.setMasterSwitch(false);
        for (Segment segment : Segment.values()) {
            assertTrue(Segment.getRawState(segment));
        }
        for (Segment segment : Segment.values()) {
            assertFalse(sevenSegments.getSegment(segment));
        }
    }

    @Test
    public void testCannotSetSegmentsWhenMasterSwitchOff() {
        sevenSegments.setMasterSwitch(false);
        assertThrows(IllegalStateException.class, () -> sevenSegments.setSegment(Segment.A, true));
        assertThrows(IllegalStateException.class, () -> sevenSegments.setDigit("1111111"));
    }

    @Test
    public void testResetAllWithMasterSwitchOn() {
        sevenSegments.setDigit("1111111");
        sevenSegments.resetAll();
        for (Segment segment : Segment.values()) {
            assertFalse(sevenSegments.getSegment(segment));
        }
        assertTrue(sevenSegments.getMasterSwitch());
    }

    @Test
    public void testResetAllWithMasterSwitchOff() {
        sevenSegments.setDigit("1111111");
        sevenSegments.setMasterSwitch(false);
        sevenSegments.resetAll();
        for (Segment segment : Segment.values()) {
            assertFalse(Segment.getRawState(segment));
        }
        assertFalse(sevenSegments.getMasterSwitch());
    }

    @Test
    public void testThatAllSegmentsAreOn() {
        sevenSegments.setDigit("1111111");
        for (Segment segment : Segment.values()) {
            assertTrue(sevenSegments.getSegment(segment));
        }
    }

    @Test
    public void testThat_Digit0_IsCorrect() {
        sevenSegments.setDigit("1111110");
        assertTrue(sevenSegments.getSegment(Segment.A));
        assertTrue(sevenSegments.getSegment(Segment.B));
        assertTrue(sevenSegments.getSegment(Segment.C));
        assertTrue(sevenSegments.getSegment(Segment.D));
        assertTrue(sevenSegments.getSegment(Segment.E));
        assertTrue(sevenSegments.getSegment(Segment.F));
        assertFalse(sevenSegments.getSegment(Segment.G));
    }

    @Test
    public void testThat_Digit1_IsCorrect() {
        sevenSegments.setDigit("0110000");
        assertFalse(sevenSegments.getSegment(Segment.A));
        assertTrue(sevenSegments.getSegment(Segment.B));
        assertTrue(sevenSegments.getSegment(Segment.C));
        assertFalse(sevenSegments.getSegment(Segment.D));
        assertFalse(sevenSegments.getSegment(Segment.E));
        assertFalse(sevenSegments.getSegment(Segment.F));
        assertFalse(sevenSegments.getSegment(Segment.G));
    }

    @Test
    public void testThat_Digit2_IsCorrect() {
        sevenSegments.setDigit("1101101");
        assertTrue(sevenSegments.getSegment(Segment.A));
        assertTrue(sevenSegments.getSegment(Segment.B));
        assertFalse(sevenSegments.getSegment(Segment.C));
        assertTrue(sevenSegments.getSegment(Segment.D));
        assertTrue(sevenSegments.getSegment(Segment.E));
        assertFalse(sevenSegments.getSegment(Segment.F));
        assertTrue(sevenSegments.getSegment(Segment.G));
    }

    @Test
    public void testThat_Digit3_IsCorrect() {
        sevenSegments.setDigit("1111001");
        assertTrue(sevenSegments.getSegment(Segment.A));
        assertTrue(sevenSegments.getSegment(Segment.B));
        assertTrue(sevenSegments.getSegment(Segment.C));
        assertTrue(sevenSegments.getSegment(Segment.D));
        assertFalse(sevenSegments.getSegment(Segment.E));
        assertFalse(sevenSegments.getSegment(Segment.F));
        assertTrue(sevenSegments.getSegment(Segment.G));
    }

    @Test
    public void testThat_Digit4_IsCorrect() {
        sevenSegments.setDigit("0110011");
        assertFalse(sevenSegments.getSegment(Segment.A));
        assertTrue(sevenSegments.getSegment(Segment.B));
        assertTrue(sevenSegments.getSegment(Segment.C));
        assertFalse(sevenSegments.getSegment(Segment.D));
        assertFalse(sevenSegments.getSegment(Segment.E));
        assertTrue(sevenSegments.getSegment(Segment.F));
        assertTrue(sevenSegments.getSegment(Segment.G));
    }

    @Test
    public void testThat_Digit5_IsCorrect() {
        sevenSegments.setDigit("1011011");
        assertTrue(sevenSegments.getSegment(Segment.A));
        assertFalse(sevenSegments.getSegment(Segment.B));
        assertTrue(sevenSegments.getSegment(Segment.C));
        assertTrue(sevenSegments.getSegment(Segment.D));
        assertFalse(sevenSegments.getSegment(Segment.E));
        assertTrue(sevenSegments.getSegment(Segment.F));
        assertTrue(sevenSegments.getSegment(Segment.G));
    }
}

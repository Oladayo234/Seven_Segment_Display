package sevenSegment;

public class SevenSegments {

    public void setSegment(Segment segment, boolean state) {
        if (!Segment.getMasterSwitch())
            throw new IllegalStateException("Cannot set segments when master switch is off");
        segment.setState(state);
    }

    public boolean getSegment(Segment segment) {
        return segment.getState();
    }

    public void setDigit(String binary) {
        Segment.setFromBinary(binary);
    }

    public void setMasterSwitch(boolean enabled) {
        Segment.setMasterSwitch(enabled);
    }

    public boolean getMasterSwitch() {
        return Segment.getMasterSwitch();
    }

    public void resetAll() {
        for (Segment segment : Segment.values()) {
            segment.setState(false);
        }
    }
}

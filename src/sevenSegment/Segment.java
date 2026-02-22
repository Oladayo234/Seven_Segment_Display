package sevenSegment;

public enum Segment {
    A(false), B(false), C(false), D(false), E(false), F(false), G(false);

    private boolean state;
    private static boolean masterSwitch = true;

    Segment(boolean state) {
        this.state = state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return masterSwitch && this.state;
    }

    public static void setMasterSwitch(boolean enabled) {
        masterSwitch = enabled;
    }

    public static boolean getMasterSwitch() {
        return masterSwitch;
    }

    public static boolean getRawState(Segment segment) {
        return segment.state;
    }

    public static void setFromBinary(String binary) {
        if (!masterSwitch)
            throw new IllegalStateException("Cannot set segments when master switch is off");
        if (binary == null || binary.length() != 7)
            throw new IllegalArgumentException("Binary string must be exactly 7 characters");
        for (char bitValue : binary.toCharArray()) {
            if (bitValue != '0' && bitValue != '1')
                throw new IllegalArgumentException("Binary string must contain only 0s and 1s");
        }
        Segment[] segments = Segment.values();
        for (int index = 0; index < 7; index++) {
            segments[index].setState(binary.charAt(index) == '1');
        }
    }
}

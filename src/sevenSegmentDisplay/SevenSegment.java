package sevenSegmentDisplay;

public class SevenSegment {
    private String sevenSegment = "00000000";
    private boolean powerIsOn = false;

    public boolean getPowerIsOn() {
        return powerIsOn;
    }

    public String getSevenSegment() {
        return sevenSegment;
    }

    public boolean setPowerIsOn(String powerIsOn) {
        validateBinaryInput(powerIsOn);
        return this.powerIsOn = powerIsOn.charAt(powerIsOn.length() - 1) == '1';
    }

    public void display(String input) {
        validatePowerIsOn();
        validateBinaryInput(input);
        sevenSegment = input;
    }

    public void print() {
        validatePowerIsOn();
        boolean top = sevenSegment.charAt(0) == '1';
        boolean topLeft = sevenSegment.charAt(1) == '1';
        boolean topRight = sevenSegment.charAt(2) == '1';
        boolean middle = sevenSegment.charAt(3) == '1';
        boolean bottomLeft = sevenSegment.charAt(4) == '1';
        boolean bottomRight = sevenSegment.charAt(5) == '1';
        boolean bottom = sevenSegment.charAt(6) == '1';

        System.out.println(top    ? " ====== " : "        ");
        for (int index = 0; index < 4; index++) {
            System.out.println((topLeft ? "|" : " ") + "      " + (topRight ? "|" : " "));
        }
        System.out.println(middle ? " ====== " : "        ");
        for (int index = 0; index < 4; index++) {
            System.out.println((bottomLeft ? "|" : " ") + "      " + (bottomRight ? "|" : " "));
        }
        System.out.println(bottom ? " ====== " : "        ");
    }

    private void validatePowerIsOn() {
        if (!powerIsOn) {
            throw new IllegalStateException("Cannot operate while power is off");
        }
    }

    private static void validateLength(String input) {
        if (input.length() != 8) {
            throw new IllegalArgumentException("Input must be exactly 8 characters");
        }
    }

    private static void validateBinaryCharacters(String input) {
        for (char character : input.toCharArray()) {
            if (character != '1' && character != '0') {
                throw new IllegalArgumentException("Input must contain only '1' or '0'");
            }
        }
    }

    private static void validateIsEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    private static void validateIsNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    private static void validateBinaryInput(String input) {
        validateIsNull(input);
        validateIsEmpty(input);
        validateLength(input);
        validateBinaryCharacters(input);
    }
}

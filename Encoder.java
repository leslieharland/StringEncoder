class Encoder {
    private static final String HELLO_WORLD = "HELLO WORLD";
    private static final String REFERENCE_STRING = createReferenceTableString();
    private char firstCharacter;

    public String encode(String plainText) {
        char startingCharacter = plainText.charAt(0);
        setFirstCharacter(startingCharacter);
        String offsetString = rotate(Encoder.createOffsetString(), startingCharacter);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < plainText.length(); ++i) {
            char letter = plainText.charAt(i);
            if (letter == ' ') {
                output.append(' ');
            } else {
                output.append(offsetString.charAt(REFERENCE_STRING.indexOf(letter)));
            }
        }
        return output.toString();
    }

    public String decode(String encodedText) {
        String offsetString = rotate(Encoder.createOffsetString(), firstCharacter);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < encodedText.length(); ++i) {
            char letter = encodedText.charAt(i);
            if (letter == ' ') {
                output.append(' ');
            } else {
                output.append(REFERENCE_STRING.charAt(offsetString.indexOf(letter)));
            }
        }
        return output.toString();
    }

    private void setFirstCharacter(char firstCharacter) {
        this.firstCharacter = firstCharacter;
    }

    private static String createOffsetString() {
        StringBuilder characterSb = new StringBuilder();
        characterSb.append("/");

        int size = 65 + 26;
        for (int i = 65; i < size; ++i) {
            char c = (char) i;
            characterSb.append(c);
        }

        for (int i = 48; i <= 57; ++i) {
            char c = (char) i;
            characterSb.append(c);
        }
        characterSb.append("()*+,-.");

        return characterSb.toString();
    }

    private static String createReferenceTableString() {
        StringBuilder characterSb = new StringBuilder();

        int size = 65 + 26;
        for (int i = 65; i < size; ++i) {
            char c = (char) i;
            characterSb.append(c);
        }

        for (int i = 48; i <= 57; ++i) {
            char c = (char) i;
            characterSb.append(c);
        }
        characterSb.append("()*+,-.");
        characterSb.append("/");

        return characterSb.toString();
    }

    private String rotate(String offsetString, char startingCharacter) {
        int positionStart = REFERENCE_STRING.indexOf(startingCharacter);
        while (offsetString.indexOf('A') != positionStart) {
            offsetString = offsetString.substring(offsetString.length() - 1)
                    + offsetString.substring(0, offsetString.length() - 1);
        }
        return offsetString;
    }

    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        System.out.println(encoder.encode(HELLO_WORLD));
        System.out.println(encoder.decode(encoder.encode(HELLO_WORLD)));
    }
}
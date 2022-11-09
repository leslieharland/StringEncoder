class Encoder {
    private static final String HELLO_WORLD = "HELLO WORLD";
    private static final String REFERENCE_STRING = createReferenceTableString();

    public String encode(String plainText) {
        char startingCharacter = plainText.charAt(0);
        int offsetNumber = (startingCharacter - 'A') - 1;
        String offsetString = padLeft(Encoder.createOffsetString(), startingCharacter);
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
        return "";
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

    private String padLeft(String offsetString, char startingCharacter) {
        return String.format("%" + (startingCharacter - 'A' - 1) + 's', "") + offsetString.trim();
    }

    public static void main(String[] args) {
        String offsetString = Encoder.createOffsetString();
        Encoder encoder = new Encoder();
        System.out.println(encoder.encode(HELLO_WORLD));
    }
}
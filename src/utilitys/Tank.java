package utilitys;

public class Tank {
    private final char[][][] content;


    public Tank() {
        content = new char[20][25][27];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 25; j++) {
                for (int k = 0; k < 27; k++) {
                    content[i][j][k] = 'g';
                }
            }
        }
    }

    public char[] takeContent(int amount) {
        return removeGin(amount);
    }

    public char[] removeGin(int amount) {
        char[] extractedGin = new char[amount];
        int extractedCount = 0;


        for (int i = 0; i < content.length && extractedCount < amount; i++) {
            for (int j = 0; j < content[i].length && extractedCount < amount; j++) {
                for (int k = 0; k < content[i][j].length && extractedCount < amount; k++) {
                    if (content[i][j][k] == 'g') {
                        extractedGin[extractedCount] = 'g';
                        content[i][j][k] = ' ';
                        extractedCount++;
                    }
                }
            }
        }
        return extractedGin;
    }

    public char[][][] getContent() {
        return content;
    }
}

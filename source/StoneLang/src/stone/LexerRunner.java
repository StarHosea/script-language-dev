package stone;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LexerRunner {
    private static final String codePath = "E:\\WorkSpace\\+Local\\StoneLang\\test.stone";

    public static void main(String[] args) {
        try {
            Lexer l = new Lexer(new FileReader(codePath));
            for (Token t; (t = l.read()) != Token.EOF; ) {
                System.out.println("=> " + t.getText());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

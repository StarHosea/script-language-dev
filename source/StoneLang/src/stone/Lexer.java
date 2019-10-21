package stone;

import stone.ast.ParseException;

import javax.swing.*;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词法分析器
 */
public class Lexer {
    /**
     * 整体结构： \s*( (注释)|(整型字面量)|(标识符) )?
     * 说明：（）用于匹配提取
     * <p>
     * 正则： //.+
     * 匹配：注释
     * <p>
     * 正则：[0-9]+
     * 匹配：整型字面量
     * <p>
     * 正则：[A-Z_a-z][A-Z_a-z0-9]*
     * 匹配：标识符
     * 说明：以字母或下划线开头，以字母数字下划线结尾
     * <p>
     * 正则：[标识符]|==|<=|>=|&&|\|\||\p{Punct}
     * 匹配: 表达式
     * 说明：提取 [标识符] == <= >= || !@等符号
     * <p>
     * 正则："(\\"|\\\\|\\n|[^"])*"
     * 匹配: 字符串字面量
     */
    public static final String regexPat = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader reader) {
        this.hasMore = true;
        this.reader = new LineNumberReader(reader);
    }

    /**
     * 读取一个Token
     *
     * @return
     */
    public Token read() throws ParseException {
        if (fillQueue(0)) {
            return queue.remove(0);
        } else {
            return Token.EOF;
        }
    }

    public Token peek(int index) throws ParseException {
        if (fillQueue(index)) {
            return queue.get(index);
        } else {
            return Token.EOF;
        }
    }


    private boolean fillQueue(int size) {
        while (size >= queue.size()) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    protected void readLine() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }

        if (line == null) {
            hasMore = false;
            return;
        }
        int lineNumber = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNumber, matcher);
                pos = matcher.end();
            } else {
                throw new ParseException("bad token at line" + lineNumber);
            }
        }
        queue.add(new IdToken(lineNumber, Token.EOL));
    }

    private void addToken(int lineNumber, Matcher matcher) {
        String m = matcher.group(1);
        if (m != null) { //匹配的不是空白符
            if (matcher.group(2) == null) { //匹配的不是注释
                Token token;
                if (matcher.group(3) != null) {
                    token = new NumToken(lineNumber, Integer.parseInt(m));
                } else if (matcher.group(4) != null) {
                    token = new StrToken(lineNumber, toStringLiteral(m));
                } else {
                    token = new IdToken(lineNumber, m);
                }
                queue.add(token);
            }
        }
    }

    private String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                char c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\') {
                    c = s.charAt(++i);
                } else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    protected static class NumToken extends Token {
        private int value;

        public NumToken(int line, int value) {
            super(line);
            this.value = value;
        }

        @Override
        public boolean isNumber() {
            return true;
        }

        @Override
        public String getText() {
            return Integer.toString(value);
        }

        @Override
        public int getNumber() {
            return value;
        }
    }

    protected static class StrToken extends Token {
        private String literal;

        public StrToken(int line, String str) {
            super(line);
            literal = str;
        }

        @Override
        public String getText() {
            return literal;
        }

        @Override
        public boolean isString() {
            return true;
        }
    }

    protected static class IdToken extends Token {
        private String text;

        protected IdToken(int line, String id) {
            super(line);
            text = id;
        }

        @Override
        public boolean isIdentifier() {
            return true;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}

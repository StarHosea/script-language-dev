package stone.ast;

public class ParseException extends StoneException {

    public ParseException(String m) {
        super(m);
    }

    public ParseException(Throwable e) {
        super(e);
    }
}

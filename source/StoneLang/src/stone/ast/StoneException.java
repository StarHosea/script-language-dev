package stone.ast;

public class StoneException extends RuntimeException {
    public StoneException(String m) {
        super(m);
    }

    public StoneException(Throwable e) {
        super(e);
    }
}

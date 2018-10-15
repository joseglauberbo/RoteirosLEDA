package forma;

public class DoubleInvalidoException extends Exception {

	public DoubleInvalidoException () {
		super(); 
	}
	
	public DoubleInvalidoException (String msgErro) {
		super(msgErro);
	}
}

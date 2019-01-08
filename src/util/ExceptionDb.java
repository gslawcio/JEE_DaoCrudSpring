package util;

public class ExceptionDb extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExceptionDb (Throwable e) {
		super(e);
	}
}

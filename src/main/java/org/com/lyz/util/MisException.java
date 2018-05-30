package org.com.lyz.util;

public class MisException extends RuntimeException{

	/**
	 * 上午11:02:21
	 */
	private static final long serialVersionUID = 1L;

	public MisException(String msg) {
        super(msg);
    }

    public MisException(Throwable throwable) {
        super(throwable);
    }

    public MisException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

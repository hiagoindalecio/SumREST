package io.swagger.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")
public class ApiException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3260912889738403557L;
	private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}

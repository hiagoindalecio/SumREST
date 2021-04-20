package io.swagger.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")
public class NotFoundException extends ApiException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2710990673475101001L;
	private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
	public int getNotFoundCode() {
		return code;
	}
}
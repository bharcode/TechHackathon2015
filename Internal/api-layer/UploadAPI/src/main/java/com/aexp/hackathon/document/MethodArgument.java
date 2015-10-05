package com.aexp.hackathon.document;

public class MethodArgument {
	private String argumentName;
	private String argumentType;
	private String argumentInfo;

	public MethodArgument(String argumentName, String argumentType, String argumentInfo) {
		super();
		this.argumentName = argumentName;
		this.argumentType = argumentType;
		this.argumentInfo = argumentInfo;
	}

	public String getArgumentName() {
		return argumentName;
	}

	public void setArgumentName(String argumentName) {
		this.argumentName = argumentName;
	}

	public String getArgumentType() {
		return argumentType;
	}

	public void setArgumentType(String argumentType) {
		this.argumentType = argumentType;
	}

	public String getArgumentInfo() {
		return argumentInfo;
	}

	public void setArgumentInfo(String argumentInfo) {
		this.argumentInfo = argumentInfo;
	}
	@Override
	public String toString() {
		return new StringBuffer("{")
			.append(this.argumentName).append(": ")
			.append(this.argumentType).append(", ")
			.append(this.argumentInfo).append("}").toString();
	}
}


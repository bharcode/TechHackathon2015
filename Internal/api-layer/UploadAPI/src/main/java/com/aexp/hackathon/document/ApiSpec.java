package com.aexp.hackathon.document;

public class ApiSpec {

	private String packageName;
	private String methodName;
	private String accessSpecifiers;
	private MethodArgument[] inputArgs;
	private MethodArgument outputArgs;
	private String methodInfo;

	private String apiUrl;
	private String apiName;
	private String methodType;
	private String requestPayload;
	private String responsePayload;
	private String contentType;

	// Must have no-argument constructor
	public ApiSpec() {
	}

	public ApiSpec(String apiUrl, String apiName, String methodType, String requestPayload, String responsePayload, String contentType) {
		super();
		this.apiUrl = apiUrl;
		this.apiName = apiName;
		this.methodType = methodType;
		this.requestPayload = requestPayload;
		this.responsePayload = responsePayload;
		this.contentType = contentType;
	}
	

	public ApiSpec(String packageName, String methodName, String accessSpecifiers, MethodArgument[] inputArgs,
			MethodArgument outputArgs, String methodInfo) {
		super();
		this.packageName = packageName;
		this.methodName = methodName;
		this.accessSpecifiers = accessSpecifiers;
		this.inputArgs = inputArgs;
		this.outputArgs = outputArgs;
		this.methodInfo = methodInfo;
	}

	public ApiSpec(String packageName, String methodName, String accessSpecifiers, MethodArgument[] inputArgs,
			MethodArgument outputArgs, String methodInfo, String apiUrl, String apiName, String methodType,
			String requestPayload, String responsePayload) {
		super();
		this.packageName = packageName;
		this.methodName = methodName;
		this.accessSpecifiers = accessSpecifiers;
		this.inputArgs = inputArgs;
		this.outputArgs = outputArgs;
		this.methodInfo = methodInfo;
		this.apiUrl = apiUrl;
		this.apiName = apiName;
		this.methodType = methodType;
		this.requestPayload = requestPayload;
		this.responsePayload = responsePayload;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getAccessSpecifiers() {
		return accessSpecifiers;
	}

	public void setAccessSpecifiers(String accessSpecifiers) {
		this.accessSpecifiers = accessSpecifiers;
	}

	public MethodArgument[] getInputArgs() {
		return inputArgs;
	}

	public void setInputArgs(MethodArgument[] inputArgs) {
		this.inputArgs = inputArgs;
	}

	public MethodArgument getOutputArgs() {
		return outputArgs;
	}

	public void setOutputArgs(MethodArgument outputArgs) {
		this.outputArgs = outputArgs;
	}

	public String getMethodInfo() {
		return methodInfo;
	}

	public void setMethodInfo(String methodInfo) {
		this.methodInfo = methodInfo;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getRequestPayload() {
		return requestPayload;
	}

	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}

	public String getResponsePayload() {
		return responsePayload;
	}

	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}

	@Override
	public String toString() {
		return new StringBuffer("package: ").append(this.packageName).append(" method: ").append(this.methodName)
				.append("accessSpecifiers").append(this.accessSpecifiers).append("inputArgs: ").append(this.inputArgs)
				.append("outputArgs: ").append(this.outputArgs).toString();
	}
}
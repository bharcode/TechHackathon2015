package com.aexp.hackathon;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.json.JSONArray;
import org.json.JSONObject;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class JarExplorer {
	JarFile jarFile;
	JSONObject jarObject = new JSONObject();
	JSONArray classesArray = new JSONArray();

	public JarExplorer() {
		// TODO Auto-generated constructor stub
	}

	public void setJarFile(String path){
		try {
			this.jarFile = new JarFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String printMethodStubs() throws Exception {
		String[] jarDetails = jarFile.getName().split("/");
		String jarName = jarDetails[jarDetails.length-1];
		jarObject.put("jarName", jarName);
		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();

			String entryName = entry.getName();
			if (entryName.endsWith(".class") && !entryName.contains("$")) {
				ClassNode classNode = new ClassNode();

				InputStream classFileInputStream = jarFile.getInputStream(entry);
				try {
					ClassReader classReader = new ClassReader(classFileInputStream);
					classReader.accept(classNode, 0);
				} finally {
					classFileInputStream.close();
				}
				describeClass(classNode);
			}
		}
		jarObject.put("classes", classesArray);
		return jarObject.toString();
	}

	public String describeClass(ClassNode classNode) {
		StringBuilder classDescription = new StringBuilder();

		Type classType = Type.getObjectType(classNode.name);

		if(!((classNode.access & Opcodes.ACC_PUBLIC) != 0) || ((classNode.access & Opcodes.ACC_ABSTRACT) != 0) || ((classNode.access & Opcodes.ACC_INTERFACE) != 0))
			return classDescription.toString();

		if ((classNode.access & Opcodes.ACC_PUBLIC) != 0) {
			classDescription.append("public ");
		}

		//	    if ((classNode.access & Opcodes.ACC_ABSTRACT) != 0) {
		//	        classDescription.append("abstract ");
		//	    }

		if ((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
			classDescription.append("interface ");
		} else {
			classDescription.append("class ");
		}


		JSONArray methodArray = new JSONArray();

		classDescription.append(classType.getClassName()).append("\n");
		classDescription.append("{\n");

		// The method signatures (e.g. - "public static void main(String[]) throws Exception")
		@SuppressWarnings("unchecked")
		List<MethodNode> methodNodes = classNode.methods;

		for (MethodNode methodNode : methodNodes) {
			String methodDescription = describeMethod(methodNode);
			if(null == methodDescription || "null".equalsIgnoreCase(methodDescription) || "".equalsIgnoreCase(methodDescription))
				continue;
			JSONObject jObj = new JSONObject();
			jObj.put("name", methodDescription);
			methodArray.put(jObj);
			classDescription.append("\t").append(methodDescription).append("\n");
		}
		classDescription.append("}\n");
	/*	if(methodArray.length() != 0 && !classType.getClassName().contains(".pojo.") && !classType.getClassName().contains(".model.") ){ */
		if(methodArray.length() != 0 ){
			JSONObject classObj = new JSONObject();
			classObj.put("name", classType.getClassName());
			classObj.put("methods", methodArray);
			classesArray.put(classObj);
		}

		return classDescription.toString();
	}

	public String describeMethod(MethodNode methodNode) {
		StringBuilder methodDescription = new StringBuilder();

		Type returnType = Type.getReturnType(methodNode.desc);
		Type[] argumentTypes = Type.getArgumentTypes(methodNode.desc);

		//TODO
		//1. Remove Constructors from the list
		//2. Remove Static Methods (Main)

		@SuppressWarnings("unchecked")
		List<String> thrownInternalClassNames = methodNode.exceptions;

		if(!((methodNode.access & Opcodes.ACC_PUBLIC) != 0) || ((methodNode.access & Opcodes.ACC_STATIC) != 0) 
				|| ((methodNode.access & Opcodes.ACC_ABSTRACT) != 0) || ((methodNode.access & Opcodes.ACC_SYNCHRONIZED) != 0))
			return methodDescription.toString();

		if ((methodNode.access & Opcodes.ACC_PUBLIC) != 0) {
			methodDescription.append("public ");
		}

		//	    if ((methodNode.access & Opcodes.ACC_PRIVATE) != 0) {
		//	        methodDescription.append("private ");
		//	    }
		//
		//	    if ((methodNode.access & Opcodes.ACC_PROTECTED) != 0) {
		//	        methodDescription.append("protected ");
		//	    }

		methodDescription.append(returnType.getClassName());
		methodDescription.append(" ");
		methodDescription.append(methodNode.name);

		String methodSign = methodDescription.toString();
		if(methodSign.contains(" static ") || methodSign.contains("<init>"))
			return null;

		methodDescription.append("(");
		for (int i = 0; i < argumentTypes.length; i++) {
			Type argumentType = argumentTypes[i];
			if (i > 0) {
				methodDescription.append(", ");
			}
			methodDescription.append(argumentType.getClassName());
			//	        methodDescription.append(" " + argumentType.getInternalName());

		}
		methodDescription.append(")");

		if (!thrownInternalClassNames.isEmpty()) {
			methodDescription.append(" throws ");
			int i = 0;
			for (String thrownInternalClassName : thrownInternalClassNames) {
				if (i > 0) {
					methodDescription.append(", ");
				}
				methodDescription.append(Type.getObjectType(thrownInternalClassName).getClassName());
				i++;
			}
		}

		return methodDescription.toString();
	}

//	public static void main(String[] args){
//		JarExplorer jr = new JarExplorer();
//
//		try {
//			//	jr.jarFile = new JarFile("/Users/abakliwa/Documents/workspace/HackathonDemo/target/DemoProject-1.0.jar");
//			jr.setJarFile("/Users/abakliwa/Documents/workspace/Hackathon/gdas.jar");
//			jr.printMethodStubs();
//			System.err.println(jr.jarObject.toString(3));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}

package com.znsx.test.cms.classloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javax.imageio.stream.FileImageInputStream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * Transformer
 * 
 * @author MIKE
 *         <p />
 *         Create at 2016年1月28日 下午3:35:19
 */
public class Transformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		if (className.endsWith("BeforeModel")) {
			System.out.println("do transform ...");
			try {
				// set build class path
				CtClass.debugDump = "E:/agentTest/build";
				CtClass ctClass = ClassPool.getDefault().getCtClass(
						"com.znsx.test.cms.classloader.BeforeModel");
				ClassFile cf = ctClass.getClassFile();
				ConstPool constPool = cf.getConstPool();

				// modify class annotation
				AnnotationMemberValue m1 = new AnnotationMemberValue(constPool);
				Annotation a1 = new Annotation("javax.ejb.ActivationConfigProperty",
						constPool);
				a1.addMemberValue("propertyName", new StringMemberValue(
						"destinationType", constPool));
				a1.addMemberValue("propertyValue", new StringMemberValue(
						"javax.jms.Queue", constPool));
				m1.setValue(a1);

				AnnotationMemberValue m2 = new AnnotationMemberValue(constPool);
				Annotation a2 = new Annotation("javax.ejb.ActivationConfigProperty",
						constPool);
				a2.addMemberValue("propertyName", new StringMemberValue(
						"messageSelector", constPool));
				a2.addMemberValue("propertyValue", new StringMemberValue(
						"Platform=2410000000000", constPool));
				m2.setValue(a2);

				AnnotationMemberValue m3 = new AnnotationMemberValue(constPool);
				Annotation a3 = new Annotation("javax.ejb.ActivationConfigProperty",
						constPool);
				a3.addMemberValue("propertyName", new StringMemberValue(
						"destination", constPool));
				a3.addMemberValue("propertyValue", new StringMemberValue(
						"QueueCMS", constPool));
				m3.setValue(a3);

				MemberValue[] elements = new MemberValue[] { m1, m2, m3 };
				ArrayMemberValue array = new ArrayMemberValue(constPool);
				array.setValue(elements);

				Annotation latest = new Annotation("javax.ejb.MessageDriven", constPool);
				latest.addMemberValue("activationConfig", array);

				AnnotationsAttribute aa = new AnnotationsAttribute(constPool,
						AnnotationsAttribute.visibleTag);
				aa.addAnnotation(latest);
				// aa.setAnnotation(latest);
				cf.addAttribute(aa);

				// modify instanceName
				CtMethod ctMethod = ctClass
						.getDeclaredMethod("getInstanceName");
				ctMethod.setBody("return \"after_instance\";");

				// modify method annotation
				Annotation methodAt = new Annotation("javax.persistence.Column", constPool);
				methodAt.addMemberValue("name", new StringMemberValue("after",
						constPool));
				AnnotationsAttribute methodaa = new AnnotationsAttribute(
						constPool, AnnotationsAttribute.visibleTag);
				methodaa.addAnnotation(methodAt);
				cf.getMethod("getInstanceName").addAttribute(methodaa);

				ctClass.rebuildClassFile();
				byte[] bytes = ctClass.toBytecode();
				ctClass.defrost();
				System.out.println("transform done");
				return bytes;
			} catch (Exception e) {
				e.printStackTrace();
			}

			// FileImageInputStream in = null;
			// try {
			// File file = new File("E:/agentTest/BeforeModel.class");
			// long length = file.length();
			// in = new FileImageInputStream(file);
			// byte[] bytes = new byte[(int) length];
			// int off = 0;
			// int len = 1024;
			// int count = 0;
			// while (off < length) {
			// count = in.read(bytes, off, (int) length);
			// if (count > 0) {
			// off += count;
			// } else {
			// break;
			// }
			// }
			// System.out.println("transform done !");
			// return bytes;
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// } finally {
			// if (null != in) {
			// try {
			// in.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			// }
			// }

		}
		return null;
	}
}

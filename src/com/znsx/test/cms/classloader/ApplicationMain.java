package com.znsx.test.cms.classloader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ApplicationMain {

	public static void main(String[] args) throws Exception {
		int count = 1;
		// TODO Auto-generated method stub
		while (true) {
			BeforeModel bm = new BeforeModel();
			System.out.println(bm.getInstanceName());
			Annotation[] annotations = bm.getClass().getAnnotations();
			if (null == annotations || annotations.length <= 0) {
				System.out.println("Class Annotations is null");
			} else {
				for (Annotation a : annotations) {
					System.out.println("Class: " + a.toString());
				}
			}

			Method[] methods = bm.getClass().getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals("getInstanceName")) {
					Annotation[] mas = method.getAnnotations();
					if (mas != null && mas.length > 0) {
						System.out.println("Method: " + mas[0].toString());
					} else {
						System.out.println("Method Annotations is null");
					}
				}
			}
			Thread.sleep(10000);
			if (count++ == 2) {
				ClassloaderTest.main(null);
			}
		}
	}
}

package com.indracompany.struts.annotations;

import java.lang.reflect.Field;
import javax.ejb.EJB;
import org.apache.struts.action.Action;

import com.indracompany.util.ServiceLocator;

class InjectionHelper {

    synchronized static void injectEJB(Action instance, Class<?> cls) {

		Field[] fields = cls.getDeclaredFields();

		for (Field f : fields) {

			EJB ejbAnnotation = f.getAnnotation(EJB.class);

			if (ejbAnnotation != null) {

				Class<?> type = f.getType();

				try {
					Object ejb = ServiceLocator.getBean(type);
					f.setAccessible(true);
					f.set(instance, ejb);
				} catch (Exception e) {
					throw new RuntimeException("Failed to inject @EJB into Struts Action", e);
				}
			}
		}

		cls = cls.getSuperclass();

		if (cls != null) {
			injectEJB(instance, cls);
		}
	}

}

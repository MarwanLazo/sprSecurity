package com.sprSecurity.spring.oval;

import java.lang.reflect.Field;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

/**
 * @author Marwan
 *
 */
public class CustomNotNullCheck extends AbstractAnnotationCheck<CustomNotNull> {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
		if (valueToValidate == null)
			return false;
		Field[] fields = valueToValidate.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				Field fld = fields[i];
				fld.setAccessible(true);
				if (fld.isAnnotationPresent(NotNull.class)) {
					Object value = fld.get(valueToValidate);
					if (value == null) {
						return false;
					}
				}

				if (fld.isAnnotationPresent(MinLength.class)) {
					if (fld.getAnnotation(MinLength.class).value() < 1) {
						return false;
					}
				}

			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}

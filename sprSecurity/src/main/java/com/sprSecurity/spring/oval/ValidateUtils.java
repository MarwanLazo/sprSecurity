package com.sprSecurity.spring.oval;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;

public class ValidateUtils {


	private static AnnotationsConfigurer				annotationsConfigurer;
	private static BeanValidationAnnotationsConfigurer	beanValidationAnnotationsConfigurer;
	private static Validator							validator;
	
public static void validate (Object object) {
		
		annotationsConfigurer = new AnnotationsConfigurer();
		beanValidationAnnotationsConfigurer = new BeanValidationAnnotationsConfigurer();
		validator = new Validator(annotationsConfigurer, beanValidationAnnotationsConfigurer);
		
		List<ConstraintViolation> errors = validator.validate(object);
		if (errors.size() != 0) {
			for (ConstraintViolation constraintViolation : errors) {
				System.err.println(constraintViolation.getMessage());
			}
		}
	}
}

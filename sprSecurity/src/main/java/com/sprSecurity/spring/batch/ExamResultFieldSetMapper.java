package com.sprSecurity.spring.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ExamResultFieldSetMapper implements  FieldSetMapper<ExamResult>{

	@Override
	  public ExamResult mapFieldSet(FieldSet fieldSet) throws BindException {
        ExamResult result = new ExamResult();
        result.setStudentName(fieldSet.readString(0));
        result.setDob(fieldSet.readString(1));
        result.setPercentage(fieldSet.readDouble(2));
        return result;
    }
}

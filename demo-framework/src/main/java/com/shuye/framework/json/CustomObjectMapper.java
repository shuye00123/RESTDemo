/**
 * 
 */
package com.shuye.framework.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shuye.framework.util.StringUtil;

/**
 * @author shuye
 *
 */
public class CustomObjectMapper extends ObjectMapper {
	private boolean camelCaseToLowerCaseWithUnderscores = false;
	private String dateFormatPattern;
	
	public void setCamelCaseToLowerCaseWithUnderscores(boolean camelCaseToLowerCaseWithUnderscores) {
		this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;
	}
	
	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	public void init(){
		setSerializationInclusion(JsonInclude.Include.NON_NULL);
		configure(SerializationFeature.INDENT_OUTPUT, true);
		if(camelCaseToLowerCaseWithUnderscores){
			setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		if(StringUtil.isNotEmpty(dateFormatPattern)){
			DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
            setDateFormat(dateFormat);
		}
	}
}

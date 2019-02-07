package com.nss.simplexweb.user.model;

import java.io.Serializable;

import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class EndPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String patternsCondition;
	private RequestMethodsRequestCondition methodsCondition;
	private String consumesCondition;
	private String producesCondition;
	private String paramsCondition;
	private String headersCondition;
	private String customCondition;
}

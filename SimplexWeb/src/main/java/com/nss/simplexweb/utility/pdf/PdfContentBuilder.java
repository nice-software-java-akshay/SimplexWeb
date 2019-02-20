package com.nss.simplexweb.utility.pdf;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nss.simplexweb.enums.PROJECT;

@Service
public class PdfContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public PdfContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildPdfContent(HashMap<String, HashMap<String, String>> messeageMap, final String TEMPLATE_PATH) {
        Context context = new Context();
        if(messeageMap != null) {
        	/*for(Map.Entry<String, HashMap<String, String>> entry : messeageMap.entrySet()) {
        		if(entry.getValue() != null) {
        			for(Map.Entry<String, HashMap<String, String>> entry1 : messeageMap.entrySet()) {
        				
        			}
        		}
            }*/
        	context.setVariable(PROJECT.CONTEXT_.name(), messeageMap);
        }
        
        return templateEngine.process(TEMPLATE_PATH, context);
    }

}
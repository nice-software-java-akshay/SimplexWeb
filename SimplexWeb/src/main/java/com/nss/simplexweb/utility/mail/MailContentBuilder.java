package com.nss.simplexweb.utility.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nss.simplexweb.enums.PROJECT;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildMilContent(HashMap<String, HashMap<String, String>> messeageMap, final String TEMPLATE_PATH) {
        Context context = new Context();
        if(messeageMap != null) {
        	/*for(Map.Entry<String, String> entry : messeageMap.entrySet()) {
            	context.setVariable(entry.getKey().toString(), String.valueOf(entry.getValue()));
            }*/
        	context.setVariable(PROJECT.CONTEXT_.name(), messeageMap);
        }
        return templateEngine.process(TEMPLATE_PATH, context);
    }

}
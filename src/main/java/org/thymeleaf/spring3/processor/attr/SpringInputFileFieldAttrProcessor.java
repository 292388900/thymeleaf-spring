/*
 * =============================================================================
 * 
 *   Copyright (c) 2011, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.spring3.processor.attr;

import java.util.Map;

import org.springframework.web.servlet.support.BindStatus;
import org.thymeleaf.Arguments;
import org.thymeleaf.processor.applicability.AndApplicabilityFilter;
import org.thymeleaf.processor.applicability.AttrValueApplicabilityFilter;
import org.thymeleaf.processor.applicability.IApplicabilityFilter;
import org.thymeleaf.processor.applicability.TagNameApplicabilityFilter;
import org.thymeleaf.processor.attr.AttrProcessResult;
import org.thymeleaf.templateresolver.TemplateResolution;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



/**
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 * @since 1.0
 *
 */
public final class SpringInputFileFieldAttrProcessor 
        extends AbstractSpringFieldAttrProcessor {

    
    public static final String FILE_INPUT_TYPE_ATTR_VALUE = "file";
    

    public SpringInputFileFieldAttrProcessor() {
        super();
    }

    


    @Override
    protected IApplicabilityFilter getApplicabilityFilter() {
        final IApplicabilityFilter tagApplicabilityFilter = new TagNameApplicabilityFilter(INPUT_TAG_NAME);
        final IApplicabilityFilter typeApplicabilityFilter = new AttrValueApplicabilityFilter(INPUT_TYPE_ATTR_NAME, FILE_INPUT_TYPE_ATTR_VALUE); 
        return new AndApplicabilityFilter(tagApplicabilityFilter, typeApplicabilityFilter);
    }




    @Override
    protected AttrProcessResult doProcess(final Arguments arguments,
            final TemplateResolution templateResolution, final Document document,
            final Element element, final Attr attribute, final BindStatus bindStatus,
            final Map<String, Object> localVariables) {
        
        String name = bindStatus.getExpression();
        name = (name == null? "" : name);
        
        final String id = computeId(arguments, templateResolution, element, name, false);
        
        element.setAttribute("id", id);
        element.setAttribute("name", name);
        
        return AttrProcessResult.forRemoveAttribute(localVariables);         
        
    }

    

}

/*
 * Copyright 2012 the CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.sastruts.html5.taglib.html;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.apache.struts.taglib.html.XhtmlTag;

/**
 * Extended XhtmlTag.
 *  
 * @author shinsuke
 *
 */
public class SXhtmlTag extends XhtmlTag implements DynamicAttributes {

    /** a default serial version uid  */
    private static final long serialVersionUID = 1L;

    private Map<String, Object> attributes = new HashMap<String, Object>();

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute(java.lang.String, java.lang.String, java.lang.Object)
     */
    @Override
    public void setDynamicAttribute(final String uri, final String name,
            final Object value) throws JspException {
        if (uri == null) {
            attributes.put(name, value);
        }
    }

    @Override
    public void release() {
        super.release();
        attributes.clear();
    }

}

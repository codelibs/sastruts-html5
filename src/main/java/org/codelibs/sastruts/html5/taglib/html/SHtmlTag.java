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
import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.apache.struts.Globals;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.HtmlTag;
import org.codelibs.sastruts.html5.util.TagUtil;

/**
 * Extended HtmlTag.
 * 
 * @author shinsuke
 *
 */
public class SHtmlTag extends HtmlTag implements DynamicAttributes {

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

    @SuppressWarnings("deprecation")
    @Override
    protected String renderHtmlStartElement() {
        final StringBuffer sb = new StringBuffer("<html");

        String language = null;
        String country = "";

        if (locale) {
            // provided for 1.1 backward compatibility, remove after 1.2
            language = getCurrentLocale().getLanguage();
        } else {
            final Locale currentLocale = TagUtils.getInstance().getUserLocale(
                    pageContext, Globals.LOCALE_KEY);

            language = currentLocale.getLanguage();
            country = currentLocale.getCountry();
        }

        final boolean validLanguage = language != null && language.length() > 0;
        final boolean validCountry = country.length() > 0;

        if (xhtml) {
            pageContext.setAttribute(Globals.XHTML_KEY, "true",
                    PageContext.PAGE_SCOPE);

            sb.append(" xmlns=\"http://www.w3.org/1999/xhtml\"");
        }

        if ((lang || locale || xhtml) && validLanguage) {
            sb.append(" lang=\"");
            sb.append(language);
            if (validCountry) {
                sb.append("-");
                sb.append(country);
            }
            sb.append("\"");
        }

        if (xhtml && validLanguage) {
            sb.append(" xml:lang=\"");
            sb.append(language);
            if (validCountry) {
                sb.append("-");
                sb.append(country);
            }
            sb.append("\"");
        }

        if (!attributes.isEmpty()) {
            sb.append(TagUtil.renderAttributes(attributes));
        }

        sb.append(">");

        return sb.toString();
    }

    @Override
    public void release() {
        super.release();
        attributes.clear();
    }

}

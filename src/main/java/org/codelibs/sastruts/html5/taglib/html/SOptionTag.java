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

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.OptionTag;
import org.apache.struts.taglib.html.SelectTag;
import org.codelibs.sastruts.html5.util.TagUtil;

/**
 * Extended OptionTag.
 * 
 * @author shinsuke
 *
 */
public class SOptionTag extends OptionTag implements DynamicAttributes {

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
    protected String renderOptionElement() throws JspException {
        final StringBuffer results = new StringBuffer("<option value=\"");

        results.append(value);
        results.append("\"");
        if (disabled) {
            results.append(" disabled=\"disabled\"");
        }
        if (selectTag().isMatched(value)) {
            results.append(" selected=\"selected\"");
        }
        if (getStyle() != null) {
            results.append(" style=\"");
            results.append(getStyle());
            results.append("\"");
        }
        if (getStyleId() != null) {
            results.append(" id=\"");
            results.append(getStyleId());
            results.append("\"");
        }
        if (getStyleClass() != null) {
            results.append(" class=\"");
            results.append(getStyleClass());
            results.append("\"");
        }

        if (!attributes.isEmpty()) {
            results.append(TagUtil.renderAttributes(attributes));
        }

        results.append(">");

        results.append(text());

        results.append("</option>");
        return results.toString();
    }

    /**
     * Acquire the select tag we are associated with.
     * @throws JspException
     */
    private SelectTag selectTag() throws JspException {
        final SelectTag selectTag = (SelectTag) pageContext
                .getAttribute(Constants.SELECT_KEY);

        if (selectTag == null) {
            final JspException e = new JspException(
                    messages.getMessage("optionTag.select"));

            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }

        return selectTag;
    }

    @Override
    public void release() {
        super.release();
        attributes.clear();
    }

}

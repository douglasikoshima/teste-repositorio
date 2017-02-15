package br.com.vivo.fo.commons.utils.presentation.menu;

import org.apache.struts.taglib.html.BaseHandlerTag;

public abstract class HtmlBaseHandlerTag extends BaseHandlerTag {

    private static final long serialVersionUID = 1L;

    public HtmlBaseHandlerTag() {
    }

    protected String getPropertyValue(String value) {
        if (value != null && value.trim().startsWith("${")) {
            return (String) super.pageContext.getAttribute(value.substring(value.indexOf("{") + 1, value.lastIndexOf("}")));
        } else {
            return value;
        }
    }
}

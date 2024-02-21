package com.example.util;

import org.apache.commons.text.StringEscapeUtils;

public class HtmlUtil {

    public static String decodeHtmlEntities(String value) {
        return StringEscapeUtils.unescapeHtml4(value);
    }

}

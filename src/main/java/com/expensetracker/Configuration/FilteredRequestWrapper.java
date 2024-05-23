package com.expensetracker.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class FilteredRequestWrapper extends HttpServletRequestWrapper {

    private final String requestUri;

    public FilteredRequestWrapper(HttpServletRequest request, String requestUri) {
        super(request);
        this.requestUri = requestUri;
    }

    @Override
    public String getRequestURI() {
        return requestUri;
    }
}

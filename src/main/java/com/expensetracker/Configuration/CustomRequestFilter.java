package com.expensetracker.Configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        String requestUri = request.getRequestURI();

        // Check for %0A and replace it with an empty string
        String cleanUri = requestUri.replace("%0A", "");

        // Create a wrapped request with the cleaned URI
        HttpServletRequest wrappedRequest = new FilteredRequestWrapper(request, cleanUri);

        // Continue with the filter chain
        filterChain.doFilter(wrappedRequest, response);
    }
}

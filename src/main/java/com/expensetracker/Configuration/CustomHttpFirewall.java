package com.expensetracker.Configuration;

import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomHttpFirewall extends DefaultHttpFirewall {

    @Override
    public FirewalledRequest getFirewalledRequest(HttpServletRequest request) {
        FirewalledRequest firewalledRequest = super.getFirewalledRequest(request);
        // Allow newline characters
        String decodedUri = firewalledRequest.getServletPath();
        if (decodedUri.contains("%0A")) {
            throw new RequestRejectedException("The request was rejected because it contains a potentially malicious newline character");
        }
        return firewalledRequest;
    }
}
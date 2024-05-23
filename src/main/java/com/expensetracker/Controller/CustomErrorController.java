package com.expensetracker.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController, CustomErrorControllerImpl {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");

        // Handle different status codes and exceptions
        if (statusCode != null) {
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404 Not Found";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500 Internal Server Error";
            }
        }

        return "Error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

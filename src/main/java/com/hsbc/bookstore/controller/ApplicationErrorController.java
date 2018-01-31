/*
 * SCEE - PIN Point
 *
 * AbstractControllers.java
 *
 * 2008 SCEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.controller;
// ---- Import Statements -----------------------------------------------------

import com.hsbc.bookstore.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * Created Date: 31/01/2017 09:12
 */
@Slf4j
@RestController
public class ApplicationErrorController extends AbstractErrorController {
    // ---- Static ------------------------------------------------------------
    protected static final String APPLICATION_JSON = "application/json";
    private static final String REQUEST_URL = "requesturl";

    private static final String PATH = "/error";
    public ApplicationErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes,  Collections.emptyList());
    }

    @RequestMapping(path = "/error", produces = APPLICATION_JSON)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleError(
            HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> body = getErrorAttributes(request, true);
        String requestId = (String)request.getAttribute("requestid");
        String errorMessage = (String)body.get("message");
        String errorCode = "82500";
        if(response.getStatus() == 404) {
            errorMessage = "Resource not found on the server";
            errorCode = "82404";
        }
        else if(response.getStatus() == 401) {
            errorMessage = "unauthorized_user :" + errorMessage;
            errorCode = "82401";
        }

        return new ResponseEntity<>( ErrorResponse.builder()
                        .errorMessage(errorMessage)
//                        .trace((String)body.get("trace"))
                        .errorCode(errorCode)
                        .sid(MDC.get("sid"))
                        .node(MDC.get("node"))
                        .url(MDC.get(REQUEST_URL))
                        .requestId(requestId).build(), getStatus(request));

    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}

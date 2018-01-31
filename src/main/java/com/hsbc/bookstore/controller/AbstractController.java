/*
 * SCEE - PIN Point
 *
 * AbstractControllers.java
 *
 * ©2008 SCEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.controller;
// ---- Import Statements -----------------------------------------------------

import com.fasterxml.jackson.databind.JsonMappingException;
import com.hsbc.bookstore.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * Created Date: 31/01/2018 09:12
 */
@Slf4j
@RestController
public class AbstractController {
    // ---- Static ------------------------------------------------------------
    protected static final String APPLICATION_JSON = "application/json";
    // ---- Constants ---------------------------------------------------------


    // ---- Member Variables --------------------------------------------------

    // ---- Constructors ------------------------------------------------------

    // ---- Other methods -----------------------------------------------------

    // ---- Exception Handling -----------------------------------------------------
    @ExceptionHandler({
            ConstraintViolationException.class,
            DataIntegrityViolationException.class,
            EntityNotFoundException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class,
            JsonMappingException.class,
            MethodArgumentTypeMismatchException.class,
            ObjectOptimisticLockingFailureException.class,
            StaleObjectStateException.class,
            UsernameNotFoundException.class

    }
    )
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleStaleObjectStateException(Exception exception) {
        log.error(exception.getMessage(), exception);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String errorMessage = exception.getMessage();
        String errorCode = "-9999";

        //Authorization Exceptions
        if (exception instanceof UsernameNotFoundException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            errorCode = "-70401";
            errorMessage = "ERROR : Invalid user (Authentication failed)";
        }
        if (exception instanceof ObjectOptimisticLockingFailureException && exception.getCause() instanceof StaleObjectStateException) {
            httpStatus = HttpStatus.CONFLICT;
            errorCode = "-11409";
            errorMessage = "ERROR : Stale Data lock";
        }


        //Bad Request Exceptions
        if (exception instanceof JsonMappingException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorCode = "-10400";
            errorMessage = "ERROR : Invalid data received and unable to parse it";
        } else if (exception instanceof HttpMediaTypeNotSupportedException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorCode = "-11400";
            errorMessage = "ERROR : Invalid data format received, expected (application/json)";
        } else if (exception instanceof HttpMessageNotReadableException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorCode = "-12400";
            errorMessage = "ERROR : Invalid data value received (eg. expecting number received character)";
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorCode = "-15400";
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) exception;
            String name = ex.getName();
            String type = ex.getRequiredType().getSimpleName();
            Object value = ex.getValue();
            errorMessage = String.format("'%s' should be a valid '%s' and '%s' isn't",
                    name, type, value);
        }


        if (exception instanceof DataIntegrityViolationException) {
            httpStatus = HttpStatus.CONFLICT;
            errorMessage = "Integrity constraint error. (Book Name or ISBN) already exists." ;
            errorCode = "-11409";
        } else if (exception instanceof ConstraintViolationException) {
            httpStatus = HttpStatus.CONFLICT;
            errorCode = "-12409";
        }


        if (exception instanceof EntityNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
            errorCode = "-22404";
        }

        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(errorMessage)
                .errorCode(errorCode)
                .sid(MDC.get("sid"))
                .node(MDC.get("node"))
                .url(MDC.get("requesturl"))
                .requestId(MDC.get("requestid")).build(), httpStatus);
    }

}
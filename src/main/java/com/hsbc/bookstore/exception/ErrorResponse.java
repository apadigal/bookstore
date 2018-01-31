/*
 * SIEE - PMDB Management
 *
 * ErrorResponse.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.exception;
// ---- Import Statements -----------------------------------------------------

import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * Created Date: 31/01/2018 09:12
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ErrorResponse {
    private String sid;
    private String node;
    private String requestId;
    private String errorCode;
    private String errorMessage;
    private String url;
    private String trace;

    public String getTimeStamp()
    {
        return LocalDateTime.now().toString();
    }
}
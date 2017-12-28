// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BadRequestController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public Map<String, String> returnError() {
        // FIXME: revisit this with reasonable error handling
        final Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "400");
        errorResponse.put("message", "bad request");

        return errorResponse;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}

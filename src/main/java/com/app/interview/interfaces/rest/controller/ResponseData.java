package com.app.interview.interfaces.rest.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData {
    private Object data;
    private ErrorResponse errorResponse;
}

package com.springproject.SpringProject.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        logger.error("Data integrity violation at {}: {}", request.getRequestURL(), e.getMessage());
        
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMessage", "A data error occurred. Please check your input and try again.");
        mv.addObject("errorCode", "DATA_ERROR");
        return mv;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        logger.error("Runtime exception at {}: {}", request.getRequestURL(), e.getMessage(), e);
        
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMessage", "An unexpected error occurred. Please try again later.");
        mv.addObject("errorCode", "RUNTIME_ERROR");
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e, HttpServletRequest request) {
        logger.error("Unexpected exception at {}: {}", request.getRequestURL(), e.getMessage(), e);
        
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMessage", "An error occurred while processing your request.");
        mv.addObject("errorCode", "GENERAL_ERROR");
        return mv;
    }
}

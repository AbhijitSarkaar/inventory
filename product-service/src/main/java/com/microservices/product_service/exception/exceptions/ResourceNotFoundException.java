package com.microservices.product_service.exception.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String entity;
    String property;
    String value;
    public ResourceNotFoundException(String entity, String property, String value) {
        super(entity + " with " + property + " = " + value + " not found");
    }
}

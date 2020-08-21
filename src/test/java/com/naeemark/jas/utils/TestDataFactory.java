package com.naeemark.jas.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDataFactory {


    public static String asJsonString(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

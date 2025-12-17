package com.sweet.springsecurity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {
    String message;
    List<String> error;
    Object data;
}

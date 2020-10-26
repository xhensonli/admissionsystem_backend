package org.enroll.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JsonResponse {


    public static final String OK = "000";
    public static final String SYSTEM_ERROR = "100";
    public static final String INVALID_REQUEST = "001";
    public static final String AUTH_ERR = "010";

    private String code;

    private Object data;

    private String message;


}

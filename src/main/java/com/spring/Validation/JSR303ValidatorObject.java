package com.spring.Validation;

import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class JSR303ValidatorObject {

    @NotNull
    private String type;
    @NotNull
    private String name;
    @NotNull
    private String error;

}

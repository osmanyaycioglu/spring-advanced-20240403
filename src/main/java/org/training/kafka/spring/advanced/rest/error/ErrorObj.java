package org.training.kafka.spring.advanced.rest.error;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
public class ErrorObj {
    private List<ErrorObj> errorObjs;
    private String errorStr;
    private Integer errorCode;


    public ErrorObj() {
    }

    @Builder(setterPrefix = "with")
    public ErrorObj(final List<ErrorObj> errorObjs,
                    final String errorStr,
                    final Integer errorCode) {
        this.errorObjs = errorObjs;
        this.errorStr  = errorStr;
        this.errorCode = errorCode;
    }
}

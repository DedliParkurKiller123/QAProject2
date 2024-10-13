package org.sec.jwtsecurityproject.config.excep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private Map<String,String> errors;
}

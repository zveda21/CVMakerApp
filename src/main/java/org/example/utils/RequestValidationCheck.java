package org.example.utils;

import io.javalin.http.BadRequestResponse;
import me.geso.tinyvalidator.ConstraintViolation;
import me.geso.tinyvalidator.Validator;
import org.example.request.BaseRequest;

import java.util.List;
import java.util.stream.Collectors;

public class RequestValidationCheck {
    public static void validationCheck(BaseRequest request) {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String message = violations.stream()
                    .map(v -> v.getName().concat(": ").concat(v.getMessage()))
                    .collect(Collectors.joining(", "));
            throw new BadRequestResponse(message);
        }
    }
}

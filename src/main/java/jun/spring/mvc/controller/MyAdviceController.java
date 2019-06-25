package jun.spring.mvc.controller;

import jun.spring.mvc.annotation.NameValidation;
import jun.spring.mvc.data.Grade;
import jun.spring.mvc.support.GradePropertyEditor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author playjun
 * @since 2019 06 21
 */
@ControllerAdvice(assignableTypes = MyController.class)
public class MyAdviceController {

    @InitBinder
    public void globalInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Grade.class, new GradePropertyEditor());
        webDataBinder.addValidators(new NameValidator());
    }

    static class NameValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            return clazz.isAnnotationPresent(NameValidation.class) && clazz.isAssignableFrom(String.class);
        }

        @Override
        public void validate(Object target, Errors errors) {
            ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
        }
    }

}



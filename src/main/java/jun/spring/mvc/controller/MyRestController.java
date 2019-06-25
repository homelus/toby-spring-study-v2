package jun.spring.mvc.controller;

import jun.spring.mvc.data.Car;
import jun.spring.mvc.data.CarParameter;
import jun.spring.mvc.data.Grade;
import jun.spring.mvc.data.Reservation;
import jun.spring.mvc.data.ReservationParameter;
import jun.spring.mvc.support.GradePropertyEditor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Optional.of;

/**
 * @author playjun
 * @since 2019 05 31
 */

@RestController
//@RequestMapping("/reservations")
public class MyRestController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimePropertyEditor());
        binder.registerCustomEditor(Grade.class, new GradePropertyEditor());
        if (binder.getTarget().getClass().isAssignableFrom(ReservationParameter.class)) {
            binder.addValidators(new ReservationValidator());
        }
        if (binder.getTarget().getClass().isAssignableFrom(CarParameter.class)) {
            binder.addValidators(new CarValidator());
        }
    }

    @GetMapping("/names")
    public List<Reservation> reservations() {
        return asList(new Reservation("jun"), new Reservation("min"));
    }

    @GetMapping("/customModel")
    public List<Reservation> customReservations(String name,
                                                @RequestParam LocalDateTime registration,
                                                @Validated Grade grade) {
        System.out.println("이름: " + name);
        System.out.println("등록일: " + registration.toString());
        System.out.println("등급: " + grade.getDesc());
        return Collections.emptyList();
    }

    @GetMapping("/compositeReservation")
    public List<Reservation> compositeReservations(@Validated ReservationParameter parameter) {
        return getReservations(parameter);
    }

    @GetMapping("/compositeCar")
    public List<Car> compositeCars(@Valid CarParameter parameter) {
        return getCars(parameter);
    }

    private List<Car> getCars(CarParameter parameter) {
        return Collections.emptyList();
    }

    @GetMapping("compositeModel/v2")
    public List<Reservation> compositeReservationV2(ReservationParameter parameter) {
        return getReservations(parameter);
    }

    private List<Reservation> getReservations(ReservationParameter parameter) {
        System.out.println("이름 : " + parameter.getName());
        System.out.println("번호 : " + parameter.getId());
        System.out.println("등록일 : " + parameter.getCurrentDate());
        System.out.println("등급 : " + of(parameter)
                .map(ReservationParameter::getGrade)
                .map(Grade::getDesc)
                .orElse("NONE"));
        System.out.println("숙박이름 : " + of(parameter)
                .map(ReservationParameter::getReservation)
                .map(Reservation::getReservationName)
                .orElse("NONE"));
        return Collections.emptyList();
    }

    private static class LocalDateTimePropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        }
    }

    private static class CarValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            System.out.println("Car 지원? : " + clazz.getName());
            return CarParameter.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            System.out.println("Car 검사: " + target);
        }
    }

    private static class ReservationValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            System.out.println("Reservation 지원? : " + clazz.getName());
            return ReservationParameter.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
//            ValidationUtils.rejectIfEmpty(errors, "reservation", "reservation.required");
            System.out.println("Reservation 검사 : " + target);
        }
    }




}



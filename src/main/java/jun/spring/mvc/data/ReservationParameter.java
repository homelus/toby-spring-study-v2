package jun.spring.mvc.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author playjun
 * @since 2019 06 12
 */
@Data
public class ReservationParameter {

    private int id;
    private String name;
    private Grade grade;
    private LocalDateTime currentDate;
    Reservation reservation;

}

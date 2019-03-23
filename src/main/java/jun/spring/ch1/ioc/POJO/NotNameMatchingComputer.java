package jun.spring.ch1.ioc.POJO;

import lombok.Data;

@Data
public class NotNameMatchingComputer {

    public NotNameMatchingComputer() {}

    public NotNameMatchingComputer(String name, Printer mainPrinter) {
        this.name = name;
        this.mainPrinter = mainPrinter;
    }

    String name;
    Printer mainPrinter;

}

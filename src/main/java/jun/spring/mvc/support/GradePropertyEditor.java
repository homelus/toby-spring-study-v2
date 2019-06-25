package jun.spring.mvc.support;

import jun.spring.mvc.data.Grade;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author playjun
 * @since 2019 06 21
 */
public class GradePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNotBlank(text) && StringUtils.isNumericSpace(text)) {
            setValue(Grade.valueOfGrade(Integer.valueOf(text)));
        }
    }
}

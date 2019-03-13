package jun.spring.ch1.property;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.springframework.beans.PropertyValuesEditor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.beans.propertyeditors.CharsetEditor;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

import java.util.Properties;

public class PropertyTest {

    @Test
    public void editorTest() {

        CharsetEditor ce;
    }

    @Test
    public void propertyTest() {
        ByteArrayPropertyEditor editor = new ByteArrayPropertyEditor();
        editor.setAsText("text");
//        editor.setValue("value".getBytes());
//        editor.setValue("value");
        editor.setSource("source");
        System.out.println("TEXT: " + editor.getAsText());
        System.out.println("SOURCE: " + editor.getSource());
        System.out.println("VALUE: " + editor.getValue());
    }

    @Test
    public void conversionTest() {
        ConversionServiceFactoryBean fb;
    }

    @Test
    public void propertiesTest() {
        Properties properties = new Properties();
        properties.setProperty("a1", "test");
        properties.setProperty("a2", "test2");
        properties.stringPropertyNames().forEach(System.out::println);
        System.out.println(properties.get("a1"));
        System.out.println(properties.get("a21"));
    }

    @Test
    public void propertyPlaceHolderConfigureTest() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
    }

}

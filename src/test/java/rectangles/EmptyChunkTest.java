package rectangles;

import net.itarray.automotion.internal.Errors;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;

import java.lang.reflect.Field;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createRootElement;

public class EmptyChunkTest {

    private ResponsiveUIChunkValidator empty;

    @Before
    public void setUp() {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        empty = temporary.findElements(emptyList());

    }

    @Test
    public void isInsideNotInsideOfAnyElement() {
        WebElement any = createRootElement();
        empty.insideOf(any, "Bla");
        assertThat(empty.validate()).isFalse();
        assertThat(hasErrorMessage("Set root web element")).isTrue();
    }

    private boolean hasErrorMessage(String expected) {
        Errors errors = getErrors(empty);
        boolean found = false;
        for (Object m : errors.getMessages()) {
            JSONObject messageObject = (JSONObject) m;
            JSONObject reason = (JSONObject) messageObject.get("reason");
            String message = (String) reason.get("message");
            if (expected.equals(message)) {
                found = true;
            }

        }
        return found;
    }

    private Errors getErrors(ResponsiveUIChunkValidator validator) {
        return (Errors) getField(getField(validator, "base"), "errors");
    }

    private Object getField(Object object, String name) {
        try {
            Class<?> theClass = object.getClass();
            while (theClass != null) {
                Field[] fields = theClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    if (name.equals(field.getName())) {
                        field.setAccessible(true);
                        return field.get(object);
                    }
                }
                theClass = theClass.getSuperclass();
            }
            throw new RuntimeException("no such field " + name + " in " + object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}

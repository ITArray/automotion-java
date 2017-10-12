package net.itarray.automotion.tests.grid;

import com.google.common.collect.Lists;
import net.itarray.automotion.internal.ResponsiveUIValidatorBase;
import net.itarray.automotion.validation.Scenario;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.NotValid;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Valid;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import rectangles.DummyDriverFacade;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;

@RunWith(Parameterized.class)
public class AnnotatedSpecificationTest {

    protected ChunkUIElementValidator chunkValidator;

    @Before
    public void setUp() {
        DummyDriverFacade driverFacade = new DummyDriverFacade();

        driverFacade.setPageSize(new Dimension(2000, 1000));

        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driverFacade);
        UISnapshot snapshot = uiValidator.snapshot();

        List<WebElement> webElements = chunk;
        chunkValidator =
                this.oneOrMore ? snapshot.findElements(webElements) : snapshot.findZeroOrMoreElements(webElements);
    }

    @Parameters(name = "{2}")
    public static Collection<Object[]> data() {

        ChunkRepository repository = new ChunkRepository();
        repository.addClass(ChunkUIElementValidator.class);

        Collection<Object[]> result = Lists.newArrayList();
        for (Method method : ChunkUIElementValidator.class.getDeclaredMethods()) {
            Valid valid = method.getAnnotation(Valid.class);
            if (valid != null) {
                for (Scenario scenario : valid.value()) {
                    for (String parameters : scenario.params()) {
                        String name = String.format("%s(%s) is valid on %s chunk", method.getName(), parameters, scenario.chunk());
                        List<WebElement> webElements = repository.get(scenario.chunk());
                        result.add(new Object[]{method, webElements, name, true, parseArgs(parameters), scenario.oneOrMore()});
                    }
                }
            }
            NotValid notValid = method.getAnnotation(NotValid.class);
            if (notValid != null) {
                for (Scenario scenario : notValid.value()) {
                    for (String parameters : scenario.params()) {
                        String name = String.format("%s(%s) is not valid on %s chunk", method.getName(), parameters, scenario.chunk());
                        List<WebElement> webElements = repository.get(scenario.chunk());
                        result.add(new Object[]{method, webElements, name, false, parseArgs(parameters), scenario.oneOrMore()});
                    }
                }
            }
        }
        return result;
    }

    public static Object[] parseArgs(String parameters1) {
        String parameterString = parameters1.trim();
        String[] parameters = parameterString.isEmpty() ? new String[0] : parameterString.split(",");
        List<Object> p = Lists.newArrayList();
        for (String parameter : parameters) {
            p.add(Integer.parseInt(parameter.trim()));
        }
        return p.toArray();
    }

    @Parameter
    public Method method;

    @Parameter(1)
    public List<WebElement> chunk;

    @Parameter(2)
    public String name;

    @Parameter(3)
    public boolean shouldBeValid;

    @Parameter(4)
    public Object[] arguments;

    @Parameter(5)
    public boolean oneOrMore;


    @Test
    public void valid() {
        try {
            method.invoke(chunkValidator, arguments);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        if (shouldBeValid) {
            assertValid();
        } else {
            assertInvalid();
        }
    }

    public void assertValid() {
        ResponsiveUIValidatorBase base = (ResponsiveUIValidatorBase) chunkValidator;
        String lastMessage = base.getErrors().getLastMessage();
        assertThat(lastMessage).describedAs("should be valid").isNull();
    }

    public void assertInvalid() {
        ResponsiveUIValidatorBase base = (ResponsiveUIValidatorBase) chunkValidator;
        String lastMessage = base.getErrors().getLastMessage();
        assertThat(lastMessage).describedAs("should not be valid").isNotNull();
    }
}

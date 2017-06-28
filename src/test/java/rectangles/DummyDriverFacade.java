package rectangles;

import net.itarray.automotion.internal.DriverFacade;
import org.openqa.selenium.Dimension;

import java.io.File;

public class DummyDriverFacade extends DriverFacade {
    public DummyDriverFacade() {
        super(null);
    }

    public static DriverFacade createWebDriver() {
        return new DummyDriverFacade();
    }

    @Override
    public File takeScreenshot() {
        throw new RuntimeException("should not happen");
    }

    @Override
    public boolean isAppiumWebContext() {
        throw new RuntimeException("should not happen");
    }

    @Override
    public boolean isAppiumNativeMobileContext() {
        throw new RuntimeException("should not happen");
    }

    @Override
    public Object executeScript(String script) {
        throw new RuntimeException("should not happen");
    }

    @Override
    public String getZoom() {
        return "100%";
    }

    @Override
    public Dimension retrievePageSize() {
        return new Dimension(RectangleFixture.pageWidth, RectangleFixture.pageHeight);
    }

    @Override
    public void setResolution(Dimension resolution) {
        throw new RuntimeException("should not happen");
    }

    @Override
    public Dimension getResolution() {
        return new Dimension(RectangleFixture.pageWidth, RectangleFixture.pageHeight);
    }

    @Override
    public void setZoom(int percentage) {
        throw new RuntimeException("should not happen");
    }
}

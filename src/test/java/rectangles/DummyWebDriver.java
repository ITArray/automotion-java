package rectangles;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class DummyWebDriver implements WebDriver, JavascriptExecutor {

    private final long windowWidth;
    private final long windowHeight;

    public DummyWebDriver(long windowWidth, long windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    @Override
    public void get(String s) {

    }

    @Override
    public String getCurrentUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void quit() {

    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }

    @Override
    public Object executeScript(String s, Object... objects) {
        if (s.toLowerCase().contains("width")) {
            return windowWidth;
        }
        if (s.toLowerCase().contains("height")) {
            return windowHeight;
        }

        return "100%";
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return null;
    }
}

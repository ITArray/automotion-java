package rectangles;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.Logs;

import java.util.List;
import java.util.Set;

public class DummyWebDriver implements WebDriver, JavascriptExecutor {

    private final long windowWidth;
    private final long windowHeight;

    public DummyWebDriver(long windowWidth, long windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public DummyWebDriver() {
        this(RectangleFixture.pageWidth, RectangleFixture.pageHeight);
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
        return new Options() {
            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public void deleteCookieNamed(String name) {

            }

            @Override
            public void deleteCookie(Cookie cookie) {

            }

            @Override
            public void deleteAllCookies() {

            }

            @Override
            public Set<Cookie> getCookies() {
                return null;
            }

            @Override
            public Cookie getCookieNamed(String name) {
                return null;
            }

            @Override
            public Timeouts timeouts() {
                return null;
            }

            @Override
            public ImeHandler ime() {
                return null;
            }

            @Override
            public Window window() {
                return new Window() {
                    @Override
                    public void setSize(Dimension targetSize) {

                    }

                    @Override
                    public void setPosition(Point targetPosition) {

                    }

                    @Override
                    public Dimension getSize() {
                        return new Dimension((int)windowWidth, (int)windowHeight);
                    }

                    @Override
                    public Point getPosition() {
                        return null;
                    }

                    @Override
                    public void maximize() {

                    }

                    @Override
                    public void fullscreen() {

                    }
                };
            }

            @Override
            public Logs logs() {
                return null;
            }
        };
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

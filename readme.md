[![Build Status](https://travis-ci.org/ITArray/automotion-java.svg?branch=master)](https://travis-ci.org/ITArray/automotion-java)

[![Maven Central](https://img.shields.io/maven-central/v/net.itarray/automotion.svg?label=Maven%20Central)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22net.itarray%22%20AND%20a%3A%22automotion%22)

# Automotion #
![alt tag](https://www.itarray.net/wp-content/uploads/2016/12/Automotion-2.jpg)

##### Official web site: https://automotion.itarray.net/
##### Example: https://github.com/ITArray/automotion-example
### Steps to connect ###
 - Repo:
   * add dependecy:

                <dependency>
                    <groupId>net.itarray</groupId>
                    <artifactId>automotion</artifactId>
                    <version>LATEST</version>
                </dependency>

### Responsive UI Validation ###
 - Responsive UI Validator allows to validate UI on web or mobile page using lots of criterias. Also it allows tu build thr HTMl report after validation.
            
            ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driver);
            UISnapshot mainSnapshot = uiValidator.snapshot("Main");
            
            boolean success = mainSnapshot.findElement(page.topSlider(), "Top Slider")
                    .isLeftAlignedWith(page.gridContainer(), "Grid Container")
                    .isBottomAlignedWith(page.topTextBlock(), "Text Block")
                    .hasWidth(between(percent(300, PAGE)).and(percent(500, PAGE)))
                    .hasEqualSizeAs(page.gridElements())
                    .isCenteredOnPageHorizontally()
                    .isCenteredOnPageVertically()
                    .isInsideOf(page.mainContainer(), "Main container", new Padding(10, 50, 10, 20))
                    .validate();
            
            uiValidator.generateReport("Report Name");
            
 - Description for each methods available in the framework:
    
    * Init method:
    
            snapshot(); // Method that defines start of new validation. Needs to be called each time before calling findElement(), findElements()
            
            snapshot("Scenario name"); // Method that defines start of new validation with specified name of scenario. Needs to be called each time before calling findElement(), findElements()
            
            setColorForRootElement(Color color); // Set color for main element. This color will be used for highlighting element in results
            
            setColorForHighlightedElements(Color color); // Set color for compared elements. This color will be used for highlighting elements in results
            
            setLinesColor(Color color); // Set color for grid lines. This color will be used for the lines of alignment grid in results
            
            setRetinaScaleFactor(double factor); // Set scale factor for retina and mobile screens
            
            findElement(WebElement element, String readableNameOfElement); // Main method to specify which element we want to validate (can be called only findElement() OR findElements() for single validation)
            
            findElements(List<WebElement> elements); // Main method to specify the list of elements that we want to validate (can be called only findElement() OR findElements() for single validation)
    
    * For single element findElement({element}, "name"):
    
           // position
           
            UIElementValidator isRightOf(WebElement element);
            UIElementValidator isRightOf(WebElement element, Condition<Scalar> distanceCondition);
           
            UIElementValidator isLeftOf(WebElement element);
            UIElementValidator isLeftOf(WebElement element, Condition<Scalar> distanceCondition);
           
            UIElementValidator isBelow(WebElement element);
            UIElementValidator isBelow(WebElement element, Condition<Scalar> distanceCondition);
           
            UIElementValidator isAbove(WebElement element);
            UIElementValidator isAbove(WebElement element, Condition<Scalar> distanceCondition);
           
            // area
           
            UIElementValidator isOverlapping(WebElement element, String readableName);
            UIElementValidator isNotOverlapping(WebElement element, String readableName);
            UIElementValidator isNotOverlapping(List<WebElement> elements);
           
            UIElementValidator isInsideOf(WebElement containerElement, String readableContainerName);
            UIElementValidator isInsideOf(WebElement containerElement, String readableContainerName, Padding padding);
           
            // alignment
           
            UIElementValidator isLeftAlignedWith(WebElement element, String readableName);
            UIElementValidator isLeftAlignedWith(List<WebElement> elements);
           
            UIElementValidator isRightAlignedWith(WebElement element, String readableName);
            UIElementValidator isRightAlignedWith(List<WebElement> elements);
           
            UIElementValidator isTopAlignedWith(WebElement element, String readableName);
            UIElementValidator isTopAlignedWith(List<WebElement> elements);
           
            UIElementValidator isBottomAlignedWith(WebElement element, String readableName);
            UIElementValidator isBottomAlignedWith(List<WebElement> elements);
           
            // size
           
            UIElementValidator hasWidth(Condition<Scalar> condition);
            UIElementValidator hasHeight(Condition<Scalar> condition);
           
            UIElementValidator hasEqualWidthAs(WebElement element, String readableName);
            UIElementValidator hasEqualWidthAs(List<WebElement> elements);
           
            UIElementValidator hasEqualHeightAs(WebElement element, String readableName);
            UIElementValidator hasEqualHeightAs(List<WebElement> elements);
           
            UIElementValidator hasEqualSizeAs(WebElement element, String readableName);
            UIElementValidator hasEqualSizeAs(List<WebElement> elements);
           
            UIElementValidator hasDifferentSizeAs(WebElement element, String readableName);
            UIElementValidator hasDifferentSizeAs(List<WebElement> elements);
           
            // offset?
           
            UIElementValidator hasLeftOffsetToPage(Condition<Scalar> condition);
            UIElementValidator hasRightOffsetToPage(Condition<Scalar> condition);
            UIElementValidator hasTopOffsetToPage(Condition<Scalar> condition);
            UIElementValidator hasBottomOffsetToPage(Condition<Scalar> condition);
           
            // css
           
            UIElementValidator hasCssValue(String cssProperty, String... args);
            UIElementValidator doesNotHaveCssValue(String cssProperty, String... args);
           
            // distribution
           
            UIElementValidator isCenteredOnPageHorizontally();
            UIElementValidator isCenteredOnPageVertically();
    * For list of elements findElements({element}):
            
            // areAlignedInColumns(numberOfColumns)
            ChunkUIElementValidator alignedAsGrid(int horizontalGridSize);
        
            ChunkUIElementValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);
        
            // area
        
            ChunkUIElementValidator doNotOverlap();
            ChunkUIElementValidator areInsideOf(WebElement containerElement, String readableContainerName);
        
            // size
        
            ChunkUIElementValidator haveEqualSize();
            ChunkUIElementValidator haveEqualWidth();
            ChunkUIElementValidator haveEqualHeight();
            ChunkUIElementValidator haveDifferentSizes();
            ChunkUIElementValidator haveDifferentWidths();
            ChunkUIElementValidator haveDifferentHeights();
        
            // alignment
        
            ChunkUIElementValidator areLeftAligned();
            ChunkUIElementValidator areRightAligned();
            ChunkUIElementValidator areTopAligned();
            ChunkUIElementValidator areBottomAligned();
        
            //
        
            ChunkUIElementValidator areCenteredOnPageVertically();
            ChunkUIElementValidator areCenteredOnPageHorizontally();
    
    * Generating results:
                
            validate(); // Call method to summarize and validate the results (can be called with drawMap(). In this case result will be only True or False)
            
            generateReport(); // Call method to generate HTML report
            
            generateReport("file report name"); // Call method to generate HTML report with specified file report name

### Possibilities ###

 - Helpers that are useful in the very different situations:
    * Generate UUID with specified length:
            
            Helper.getGeneratedStringWithLength(int length) - String
            
    * Create image file on fly:
    
            Helper.createFile(String filename) - File. Will be saved in the folder "target/" that is been created after building
            
    * Parse JSON text:
    
            Parser.getJSONValue(String textToParse, String key) - String
            
    * Parse XML text based on SAX algorithm:
        
            Parser.getXMLValue(String xmlToParse, String xpath) - String
            Parser.getXMLValues(String xmlToParse, String xpath) - List<String>
            
    * Smart Text finder. Can find the string even in the broken text with corrupted characters:
     
            TextFinder.textIsFound(String whatToFind, String whereToFind) - bool. Default derivation is 30% (0.3). It means that accuracy of searching will be 70%
            TextFinder.setDerivation(int newValue) - void. New value between 0 and 1.
            
    * Mail checker (IMAP). Possible to connect to any IMAP mail box (Gmail for example) and get the list of mails with access to the mail details.
     
            MailService mailService = new MailService();
            mailService
                       .setFolder(MailService.MailFolder.INBOX) // (INBOX, SPAM, TRASH)
                       .login(String IMAP_Server, int IMAP_Port, String email, String passwordToEmail);
            

            mailService.isLoggedIn()) - boolean
            mailService.getMessageCount() - integer
            mailService.getMessages() - Message[]
            mailService.getLastMessage() - Message - last message
            
    * Language validator. Algorithm based on semantic approach of languages validation on the web page. Allowed methods:
     
            LanguageChecker.getRecognisedLanguage(String textToValidate) - Optional<LdLocale> 
            Allowed method .get() to get access to the details. E.g LanguageChecker.getRecognisedLanguage(String textToValidate).get().getLanguage() will return "en"
            
            LanguageChecker.isCorrectLanguageOnThePage(WebDriver driver, String lang) - boolean. String lang is 2-chars abbreviature. E.g "en" or "es"
            
  - Web and mobile WebDriver Helpers that are useful in the very different situations:
  
    * All the methods are collected in both classes: MobileHelper and DriverHelper. The most useful pf them are:
     
            DriverHelper.scrollDownWeb(WebDriver driver) 
            DriverHelper.scrollUpWeb(WebDriver driver) 
            DriverHelper.scrollDownMobile(AppiumDriver driver)
            DriverHelper.scrollUpMobile(AppiumDriver driver)
            DriverHelper.scrollDownMobile(AppiumDriver driver, int duration) - duration not less than 500ms
            DriverHelper.scrollUpMobile(AppiumDriver driver, int duration) - duration not less than 500ms
            DriverHelper.scrollDownMobileElement(AppiumDriver driver, MobileElement element)
            DriverHelper.scrollUpMobileElement(AppiumDriver driver, MobileElement element)
            DriverHelper.scrollDownMobileElement(AppiumDriver driver, MobileElement element, int duration) - duration not less than 500ms
            DriverHelper.scrollUpMobileElement(AppiumDriver driver, MobileElement element, int duration) - duration not less than 500ms
            DriverHelper.zoomInOutPage(WebDriver driver, int zoomPercent) - zoom In/Out the page
            
            DriverHelper.hideKeyboard(AppiumDriver driver) - super method that perform hiding of keyboard for Android and iOS
            
            DriverHelper.click(WebDriver driver, WebElement element) - Smart click that will try to click few times
            
            DriverHelper.clickByLocation(WebDriver driver, WebElement element, ClickPoint clickPoint)
            DriverHelper.clickByLocation(AppiumDriver driver, MobileElement element, ClickPoint clickPoint) - methods for clickin on the elements with specified point of click.
            Allowed ClickPoint are: ClickPoint.TOP_LEFT, ClickPoint.TOP_RIGHT, ClickPoint.BOTTOM_LEFT, ClickPoint.BOTTOM_RIGHT, ClickPoint.CENTER
             
            DriverHelper.clickJQuery(WebDriver driver, WebElement element) - click method that is performed by triggering JQuery native method
            
            DriverHelper.waitForPageIsReady(WebDriver driver) - wait for page is loaded and all the backgrounded process are finished
            
            MobileHelper.turnOnWifi() - turn on WiFI on Android Mobile device
            MobileHelper.turnOffWifi() - turn off WiFI on Android Mobile device
            MobileHelper.turnOnMobileData() - turn on Mobile data on Android Mobile device
            MobileHelper.turnOffMobileData() - turn off Mobile data on Android Mobile device
            MobileHelper.turnOnAirplaneMode() - turn on Airplane mode on Android Mobile device
            MobileHelper.turnOffAirplaneMode() - turn off Airplane mode on Android Mobile device
            
            MobileHelper.openAndroidNotifications(AppiumDriver driver) - open notification tray on Android devices
            
            
 - API calls. This part is very important because allows to perform different kind of HTTP(s) request with parameters.
 
    * Example of sending POST request:
            
            new ConnectionFactory(String baseUrl).sendPost(Map map, String endpoint, String token, boolean withMediaFile) - return Map<Integer, String> where Integer is response code and String is response body
            Parameter map contains usual HTTP Pair-Values set of data in the representation of Java Map interface. 
            String endpoint - is the ending of URL e.g "/api/v1/users".
            Parameter boolean withMediaFile is for advanced usage if You want to generate and upload some file
            Parameter token - is authorisation token if we want to perform request with auth. If not, just leave this parameter as empty string
            
    * Example of sending GET request:
    
            new ConnectionFactory(String baseUrl).sendGet(String endpoint, String token) - return Map<Integer, String> where Integer is response code and String is response body
            String endpoint - is the ending of URL e.g "/api/v1/users?id=1"
            Parameter token - is authorisation token if we want to perform request with auth. If not, just leave this parameter as empty string
            
    * Example of sending PUT request:
            
            new ConnectionFactory(String baseUrl).sendPut(Map map, String endpoint, String token) - return Map<Integer, String> where Integer is response code and String is response body
            Parameter map contains usual HTTP Pair-Values set of data in the representation of Java Map interface. 
            String endpoint - is the ending of URL e.g "/api/v1/users".
            Parameter token - is authorisation token if we want to perform request with auth. If not, just leave this parameter as empty string
            
    * Example of sending DELETE request:
    
            new ConnectionFactory(String baseUrl).sendDelete(String endpoint, String token) - return Map<Integer, String> where Integer is response code and String is response body
            String endpoint - is the ending of URL e.g "/api/v1/users?id=1"
            Parameter token - is authorisation token if we want to perform request with auth. If not, just leave this parameter as empty string

            
### Usage as standalone framework with out of the box configuration ###

- Create instance of WebDriverFactory and call getDriver:

            WebDriverFactory driverFactory = new WebDriverFactory();
            WebDriver driver = driverFactory.getDriver();
            
- Extend Your page classes from BaseWebMobileElement class to have access to methods:

            getWebElement(final By by)
            getWebElement(ExpectedCondition<WebElement> expectedCondition)
            
            getMobileElement(final By by, int timeOfWaiting)
            getMobileElement(final By by)
            
            getWebElements(final By by)
            getWebElements(ExpectedCondition<List<WebElement>> expectedCondition)  

### Steps of using during test run ###

#### ! Do not forget to put Chrome and Gecko drivers into Your project src/test/resources/drivers ! ####

 - Specify env variables or system properties (example):
    * For Web local run:       
         
            IS_LOCAL=True
            BROWSER=Firefox|Chrome|IE|Safari|EDGE

    * For Web remote run:

            IS_REMOTE=True
            BROWSER=Firefox|Chrome|IE|Safari|EDGE
            EXECUTOR=http://{host}:{port}/wd/hub
            (optional available with Chrome only) MOBILE_DEVICE_EMULATION=Google Nexus 5|Apple iPhone 6|Samsung Galaxy S5

    * For Web Mobile run:

            IS_MOBILE=True
            PLATFORM=Android|iOS
            BROWSER=Chrome|Safari
            EXECUTOR=http://{host}:{port}/wd/hub
            DEVICE=Device name
            
    * For Web Headless run (with PhantomJS without browser):

            IS_HEADLESS=True
            BROWSER=Firefox|Chrome|IE|Safari
            PHANTOM_JS_PATH=C://phantomjs.exe

    * For Native Mobile run:

            IS_MOBILE=True
            PLATFORM=Android|iOS
            APP={path_to_app}
            EXECUTOR=http://{host}:{port}/wd/hub
            DEVICE=Device name

    * For Windows UWP:

            IS_MOBILE=True
            PLATFORM=Windows
            APP={path_to_app}
            EXECUTOR=http://{host}:{port}/wd/hub
            DEVICE=Device name or ID            
            
    
    
### Contact ###
Denys Zaiats
denys.zaiats@gmail.com

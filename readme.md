[![Build Status](https://travis-ci.org/ITArray/Automotion.svg?branch=master)](https://travis-ci.org/ITArray/Automotion)
[![Gratipay User](https://img.shields.io/gratipay/user/dougwilson.svg)](https://gratipay.com/~dzaiats/)

# Automotion #
![alt tag](https://www.itarray.net/wp-content/uploads/2016/12/Automotion-2.jpg)

##### Example: https://github.com/ITArray/automotion-example
### Steps to connect ###
 - Repo:
   * add dependecy:

                <dependency>
                    <groupId>net.itarray</groupId>
                    <artifactId>automotion</artifactId>
                    <version>LATEST</version>
                </dependency>

### Steps of adding to the project ###

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

### Responsive UI Validation ###
 - Responsive UI Validator allows to validate UI on web or mobile page using lots of criterias. Also it allows tu build thr HTMl report after validation.
            
            ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driver);
            
            
            boolean result = uiValidator.init()
                   .findElement({rootEelement}, "Name of element")
                   .sameOffsetLeftAs({element} "Panel 1")
                   .sameOffsetLeftAs({element} "Button 1")
                   .sameOffsetRightAs({element} "Button 2")
                   .sameOffsetRightAs({element}, "Button 3)
                   .withCssValue("border", "2px", "solid", "#FBDCDC")
                   .withCssValue("border-radius", "4px")
                   .withoutCssValue("color", "#FFFFFF")
                   .sameSizeAs({list_elements},)
                   .insideOf({element}, "Container")
                   .notOverlapWith({element}, "Other element")
                   .withTopElement({element}, 10, 15)
                   .changeMetricsUnitsTo(ResponsiveUIValidator.Units.PERCENT)
                   .widthBetween(50, 55)
                   .heightBetween(90, 95)
                   .drawMap()
                   .validate();
            
            
            uiValidator.generateReport();
            
 - Description for each methods available in the framework:
    
    * Init method:
    
            init(); // Method that defines start of new validation. Needs to be called each time before calling findElement(), findElements()
            
            init("Scenario name"); // Method that defines start of new validation with specified name of scenario. Needs to be called each time before calling findElement(), findElements()
            
            setColorForRootElement(Color color); // Set color for main element. This color will be used for highlighting element in results
            
            setColorForHighlightedElements(Color color); // Set color for compared elements. This color will be used for highlighting elements in results
            
            setLinesColor(Color color); // Set color for grid lines. This color will be used for the lines of alignment grid in results
            
            findElement(WebElement element, String readableNameOfElement); // Main method to specify which element we want to validate (can be called only findElement() OR findElements() for single validation)
            
            findElements(List<WebElement> elements); // Main method to specify the list of elements that we want to validate (can be called only findElement() OR findElements() for single validation)
    
    * For single element findElement({element}, "name"):
    
            insideOf(WebElement containerElement, String readableContainerName); // Verify that element is located inside of specified element
         
            withLeftElement(WebElement element); // Verify that element which located left to is correct
        
            withLeftElement(WebElement element, int minMargin, int maxMargin); // Verify that element which located left to is correct with specified margins
        
            withRightElement(WebElement element); // Verify that element which located right to is correct
        
            withRightElement(WebElement element, int minMargin, int maxMargin); // Verify that element which located right to is correct with specified margins
        
            withTopElement(WebElement element); // Verify that element which located top to is correct
        
            withTopElement(WebElement element, int minMargin, int maxMargin); // Verify that element which located top to is correct with specified margins
        
            withBottomElement(WebElement element); // Verify that element which located bottom to is correct
        
            withBottomElement(WebElement element, int minMargin, int maxMargin); // Verify that element which located bottom to is correct with specified margins
        
            notOverlapWith(WebElement element, String readableName); // Verify that element is NOT overlapped with specified element
        
            overlapWith(WebElement element, String readableName); // Verify that element is overlapped with specified element
        
            notOverlapWith(List<WebElement> elements); // Verify that element is NOT overlapped with every element is the list
        
            sameOffsetLeftAs(WebElement element, String readableName); // Verify that element has the same left offset as specified element
        
            sameOffsetLeftAs(List<WebElement> elements); // Verify that element has the same left offset as every element is the list
        
            sameOffsetRightAs(WebElement element, String readableName); // Verify that element has the same right offset as specified element
        
            sameOffsetRightAs(List<WebElement> elements); // Verify that element has the same right offset as every element is the list
        
            sameOffsetTopAs(WebElement element, String readableName); // Verify that element has the same top offset as specified element
        
            sameOffsetTopAs(List<WebElement> elements); // Verify that element has the same top offset as every element is the list
        
            sameOffsetBottomAs(WebElement element, String readableName); // Verify that element has the same bottom offset as specified element
        
            sameOffsetBottomAs(List<WebElement> elements); // Verify that element has the same bottom offset as every element is the list
        
            sameWidthAs(WebElement element, String readableName); // Verify that element has the same width as specified element
        
            sameWidthAs(List<WebElement> elements); // Verify that element has the same width as every element in the list
        
            minWidth(int width); // Verify that width of element is not less than specified
        
            maxWidth(int width); // Verify that width of element is not bigger than specified
        
            widthBetween(int min, int max); // Verify that width of element is in range
        
            sameHeightAs(WebElement element, String readableName); // Verify that element has the same height as specified element
        
            sameHeightAs(List<WebElement> elements); // Verify that element has the same height as every element in the list
        
            minHeight(int height); // Verify that height of element is not less than specified
        
            maxHeight(int height); // Verify that height of element is not bigger than specified
        
            sameSizeAs(WebElement element, String readableName); // Verify that element has the same size as specified element
        
            sameSizeAs(List<WebElement> elements); // Verify that element has the same size as every element in the list
        
            heightBetween(int min, int max); // Verify that height of element is in range
        
            minOffset(int top, int right, int bottom, int left); // Verify that min offset of element is not less than (min value is -10000)
        
            maxOffset(int top, int right, int bottom, int left); // Verify that max offset of element is not bigger than (min value is -10000)
        
            withCssValue(String cssProperty, String... args); // Verify that element has correct CSS values
        
            withoutCssValue(String cssProperty, String... args); // Verify that concrete CSS values are absent for specified element
        
            equalLeftRightOffset(); // Verify that element has equal left and right offsets (e.g. Bootstrap container)
        
            equalTopBottomOffset(); // Verify that element has equal top and bottom offset (aligned vertically in center)
         
            changeMetricsUnitsTo(ResponsiveUIValidator.Units units); // Change units to Pixels or % (Units.PX, Units.PERCENT)

    * For list of elements findElements({element}):
            
            insideOf(WebElement containerElement, String readableContainerName); // Verify that elements are located inside of specified element
            
            alignedAsGrid(int horizontalGridSize); // Verify that elements are aligned in a grid view width specified amount of columns
            
            alignedAsGrid(int horizontalGridSize, int verticalGridSize); // Verify that elements are aligned in a grid view width specified amount of columns and rows
            
            areNotOverlappedWithEachOther(); // Verify that every element in the list is not overlapped with another element from this list
            
            withSameSize(); // Verify that elements in the list have the same size
            
            withSameWidth(); // Verify that elements in the list have the same width
            
            withSameHeight(); // Verify that elements in the list have the same height
            
            sameRightOffset(); // Verify that elements in the list have the right offset
            
            sameLeftOffset(); // Verify that elements in the list have the same left offset
            
            sameTopOffset(); // Verify that elements in the list have the same top offset
            
            sameBottomOffset(); // Verify that elements in the list have the same bottom offset
            
            equalLeftRightOffset(); // Verify that every element in the list have equal right and left offset (aligned horizontally in center)
            
            equalTopBottomOffset(); // Verify that every element in the list have equal top and bottom offset (aligned vertically in center)
            
            changeMetricsUnitsTo(ResponsiveUIValidator.Units units); // Change units to Pixels or % (Units.PX, Units.PERCENT)
           
    * Generating results:
    
            drawMap(); // Methods needs to be called to collect all the results in JSON file and screenshots
            
            validate(); // Call method to summarize and validate the results (can be called with drawMap(). In this case result will be only True or False)
            
            generateReport(); // Call method to generate HTML report
            
            generateReport("file report name"); // Call method to generate HTML report with specified file report name

### Possibilities ###
 - Verification that elements are aligned correctly on the web or mobile page
    * Elements horizontally are aligned correctly:
        
            PageValidator.elementsAreAlignedHorizontally(List<WebElement> elements) - boolean
    
    * Elements vertically are aligned correctly:
    
            PageValidator.elementsAreAlignedVertically(List<WebElement> elements) - boolean
        
    * Elements are aligned properly in general:
    
            PageValidator.elementsAreAlignedProperly(List<WebElement> elements) - boolean
            
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

            
            
            
    
    
### Contact ###
Denys Zaiats
denys.zaiats@gmail.com

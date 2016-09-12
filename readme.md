# Automotion #
This is JAVA library for the running of mobile, web or API automated tests.

### Steps to connect ###
 - Repo:
   * add repository:

            <repository>
                  <id>io.automotion</id>
                  <url>https://raw.github.com/dzaiats/java.automation.library/mvn-repo/</url>
                  <snapshots>
                      <enabled>true</enabled>
                      <updatePolicy>always</updatePolicy>
                  </snapshots>
              </repository>
   * add dependecy:

                <dependency>
                    <groupId>io.automotion</groupId>
                    <artifactId>automation.framework</artifactId>
                    <version>1.1.0</version>
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


 - Specify env variables (example):
    * For Web local run::

            export IS_LOCAL=True
            export BROWSER=Firefox|Chrome|IE|Safari

    * For Web remote run:

            export IS_REMOTE=True
            export BROWSER=Firefox|Chrome|IE|Safari
            export EXECUTOR=http://{host}:{port}/wd/hub

    * For Web Mobile run:

            export IS_MOBILE=True
            export PLATFORM=Android|iOS
            export BROWSER=Chrome|Safari
            export EXECUTOR=http://{host}:{port}/wd/hub
            export DEVICE=Device name

    * For Native Mobile run:

            export IS_MOBILE=True
            export PLATFORM=Android|iOS
            export APP={path_to_app}
            export EXECUTOR=http://{host}:{port}/wd/hub
            export DEVICE=Device name

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
            mailService.login(String IMAP_Server, int IMAP_Port, String email, String passwordToEmail);

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
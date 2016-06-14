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
                    <version>1.0.2</version>
                </dependency>

### Steps of adding to the project ###

- Create instance of WebDriverFactory and call getDriver::

            WebDriverFactory driverFactory = new WebDriverFactory();
            WebDriver driver = driverFactory.getDriver();

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

### Contact ###
Denys Zaiats
denys.zaiats@gmail.com
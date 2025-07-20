# Selenium Hybrid Automation Framework 

A comprehensive Selenium Hybrid Test Automation Framework using Java,selenium, TestNG, Maven, Log4j2, Apache POI, and ExtentReports, git/github ,docker,jenkins.

---

## Repository Links

- 📁 **My Repo:** [Selenium-Hybrid-Framework](https://github.com/harshitgupta1271/Selenium-Hybrid-Framework)

---

## 📁 Project Structure

opencart/
├── src/test/java/
│ ├── pageObjects/
│ ├── testBase/
│ ├── testCases/
│ └── utilities/
├── src/test/resources/
│ ├── config.properties
│ └── log4j2.xml
├── testData/
├── logs/
├── reports/
├── screenshots/
├── pom.xml
└── TestNG.xml



---

## 📦 Maven Dependencies

- [selenium-java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)  
- [poi](https://mvnrepository.com/artifact/org.apache.poi/poi)  
- [poi-ooxml](https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml)  
- [log4j-core](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core)  
- [log4j-api](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api)  
- [commons-io](https://mvnrepository.com/artifact/commons-io/commons-io)  
- [commons-lang3](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3)  
- [testng](https://mvnrepository.com/artifact/org.testng/testng)  
- [extentreports](https://mvnrepository.com/artifact/com.aventstack/extentreports)  

---

## 🛠️ Framework Development Steps

### 1. **Account Registration Test Case**
- `BasePage` in `pageObjects` — reusable constructor.
- `HomePage`, `RegistrationPage` classes extend `BasePage`.
- `AccountRegistrationTest` under `testCases`.
- `BaseClass` under `testBase` — contains reusable methods.
- Add random string/number generators in `BaseClass`.

### 2. **Logging with Log4j2**
- Add `log4j2.xml` in `resources`.
- Update `BaseClass` with logger setup.
- Add logs in `AccountRegistrationTest`.

### 3. **Cross Browser & Parallel Execution**
- Use `TestNG.xml` with parameterized browser and OS.
- Modify `setup()` in `BaseClass` accordingly.
- Create XML files for parallel testing.

### 4. **Config Properties**
- Create `config.properties` in `resources`.
- Load in `setup()` of `BaseClass`.
- Replace hardcoded values in tests.

### 5. **Login Test Case**
- Create `LoginPage`, `MyAccountPage`, and update `HomePage`.
- Create `LoginTest` and update `testng.xml`.

### 6. **Data-Driven Login Test**
- Prepare Excel in `testData/`.
- `ExcelUtility` in `utilities`.
- Add logout to `MyAccountPage`.
- Create `DataProviders` class.
- Create `LoginDataDrivenTest`.

### 7. **Test Grouping**
- Use TestNG groups: sanity, regression, etc.
- Group `setup()` and `teardown()` as well.
- Add a new `grouping.xml`.

### 8. **Extent Reports Integration**
- Create `ExtentReportUtility` in `utilities`.
- Add `captureScreen()` in `BaseClass`.
- Update `testng.xml` with listener class.
- Make WebDriver static in `BaseClass`.

### 9. **Run Failed Test Cases**
- Re-run using `testng-failed.xml` inside `test-output`.

---

## 🧪 Selenium Grid Setup

### 🔌 Standalone Mode
```bash
java -jar selenium-server-4.15.0.jar standalone


## Open http://localhost:4444/ to view sessions.


Config File
execution_env=local/remote


Hub & Node Setup (Multiple Machines)

# On Hub:
java -jar selenium-server-4.15.0.jar hub

# On Node:
java -jar selenium-server-4.15.0.jar node --hub http://<hub-ip>:4444


Selenium Grid with Docker

⚙️ Docker Setup (Mac M1/M2)
[Install Docker](https://www.docker.com/products/docker-desktop/)
Pull Images:

docker pull selenium/hub
docker pull --platform=linux/amd64 selenium/node-chrome
docker pull --platform=linux/amd64 selenium/node-firefox

## Create Network & Run Containers:

docker network create grid

docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub

docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub \
-e SE_EVENT_BUS_PUBLISH_PORT=4442 \
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome

docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub \
-e SE_EVENT_BUS_PUBLISH_PORT=4442 \
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-firefox

View Grid:
Open: http://localhost:4444/grid/console
🧾 Docker Compose YML

version: '3'
services:
  selenium-hub:
    image: selenium/hub
    ports:
      - "4442:4442"
      - "4443:4443"
      - "4444:4444"
    networks:
      - grid

  node-chrome:
    image: selenium/node-chrome
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
    networks:
      - grid

  node-firefox:
    image: selenium/node-firefox
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
    networks:
      - grid

networks:
  grid:
    driver: bridge


docker-compose up          # Start Grid
docker-compose down        # Stop Grid
docker-compose up --scale node-chrome=3  # Scale Chrome Nodes


Run Tests via Maven

🧱 pom.xml Plugins


<build>
  <pluginManagement>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.14.0</version>
        <configuration>
          <release>17</release>
        </configuration>
      </plugin>

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.5.3</version>
        <configuration>
          <suiteXmlFiles>
            <suiteXmlFile>master.xml</suiteXmlFile>
          </suiteXmlFiles>
        </configuration>
      </plugin>
    </plugins>
  </pluginManagement>
</build>


Run From CLI
mvn test


Maven Setup on Mac M2

nano ~/.zshrc
# Add:
export MAVEN_HOME=/Users/harshit/Downloads/apache-maven-3.9.11
export PATH=$MAVEN_HOME/bin:$PATH

# Apply:
source ~/.zshrc

# Verify:
mvn -v



Git & GitHub Workflow

✅ Initial Setup
git init
git config --global user.name "Harshit Gupta"
git config --global user.email "hgupta1271@gmail.com"
git remote add origin https://github.com/harshitgupta1271/Selenium-Hybrid-Framework.git




 Push Code
git add -A
git commit -m "Initial Commit"
git push origin master


Use Personal Access Token (PAT) instead of password.
🔄 Daily Updates
git add -A
git commit -m "Your commit message"
git push origin master
⬇️ Clone & Pull
git clone <repo-url>
git pull https://github.com/harshitgupta1271/Selenium-Hybrid-Framework.git


Jenkins Setup on Mac

🔧 Setup
java -jar jenkins.war
# Admin Password: xyz
👤 Create Admin User
Username: harshitGupta
Password: xyz
Email: hgupta1271@gmail.com
🛠 Tool Configuration
JDK: /opt/homebrew/Cellar/openjdk@17/17.0.16/libexec/openjdk.jdk/Contents/Home
Git: /opt/homebrew/bin/git
Maven: /Users/harshit/Tools/apache-maven-3.9.11
▶️ Job Setup
Create new Maven job.
Configure Git repo: https://github.com/harshitgupta1271/Selenium-Hybrid-Framework.git
Set Goals: maven test
Click on Build Now.
You can also use a local pom.xml path instead of Git URL.

🧪 Edge Browser Setup (Manual on Mac)

System.setProperty("webdriver.edge.driver", "/usr/local/bin/msedgedriver");
Edge WebDriver must match the Edge browser version. Use:

msedgedriver --version
⚠️ Safari Limitation

Safari works only in serial testing, not in parallel or remote grid testing. Debugging needed.



commands.txt



# 1. Run Standalone Grid
cd /Users/harshit/Desktop/seleniumwebdriver/Opencart_v1.0.1
java -jar selenium-server-4.20.0.jar standalone

# 2. Start Grid on Docker
cd /Users/harshit/Desktop/seleniumwebdriver/Opencart_v1.0.1
docker-compose up

# 3. Remove Containers on Docker
cd /Users/harshit/Desktop/seleniumwebdriver/Opencart_v1.0.1
docker-compose down

# 4. Run Maven Tests (pom.xml)
cd /Users/harshit/Desktop/seleniumwebdriver/Opencart_v1.0.1
mvn test
   
   
  

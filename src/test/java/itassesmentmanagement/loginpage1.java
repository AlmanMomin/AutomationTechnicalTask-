package itassesmentmanagement;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class loginpage1 {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            testValidLogin();
            testInvalidLogin();
            testForgotPassword();
            testSignInWithMicrosoft();
        } catch (Exception e) {
            logError(e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // Test case for valid login
    private static void testValidLogin() {
        try {
            driver.get("https://itassetmanagementsoftware.com/rolepermission/admin/login");

            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Username']")));
            usernameField.click();
            usernameField.sendKeys("Admin");

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Continue']")));
            continueButton.click();

            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[placeholder='Enter Password']")));
            passwordField.click();
            passwordField.sendKeys("Test@123");

            WebElement signInButton = driver.findElement(By.xpath("//button[@id='btnSubmit']"));
            signInButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Please enter valid username')]")));
            if (errorMessage.isDisplayed()) {
                logError("Error: Please enter valid username");
            }
        } catch (Exception e) {
            logError("Please enter valid username: " + e.getMessage());
        }
    }

    // Test case for invalid login
    private static void testInvalidLogin() {
        try {
            driver.get("https://itassetmanagementsoftware.com/rolepermission/admin/login");

            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Username']")));
            usernameField.click();
            usernameField.sendKeys("InvalidUser");

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Continue']")));
            continueButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Please enter valid username to continue')]")));
            if (errorMessage.isDisplayed()) {
                logError("Please enter valid username to continue");
            }

        } catch (Exception e) {
            logError("Please enter valid username to continue: " + e.getMessage());
        }
    }

    // Test case for Forgot Password
    private static void testForgotPassword() {
        try {
            driver.get("https://itassetmanagementsoftware.com/rolepermission/admin/login");

            WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot password?")));
            forgotPasswordLink.click();

            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[placeholder='Enter Your Email Address']")));
            emailField.click();
            emailField.sendKeys("alman.momin94@gmail.com");

            WebElement sendButton = driver.findElement(By.xpath("//button[@id='btnSubmit']"));
            sendButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Given email address not have')]")));
            if (errorMessage.isDisplayed()) {
                logError("Given email address not have an account with us.");
            }

        } catch (Exception e) {
            logError("Given email address not have an account with us.: " + e.getMessage());
        }
    }

    // Test case for signing in with Microsoft
    private static void testSignInWithMicrosoft() {
        try {
            driver.get("https://itassetmanagementsoftware.com/rolepermission/admin/login");

            WebElement microsoftSignInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign in with Microsoft")));
            microsoftSignInLink.click();

            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='i0116']")));
            emailField.click();
            emailField.sendKeys("alman@correctcloud.io");

            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Next']")));
            nextButton.click();

            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
            passwordField.click();
            passwordField.sendKeys("Hamid@123");

            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']")));
            signInButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sorry, but we’re having')]")));
            if (errorMessage.isDisplayed()) {
                logError("Error: Sorry, but we’re having issues signing you in.");
            }
        } catch (Exception e) {
            logError("Sorry, but we’re having issues signing you in.: " + e.getMessage());
        }
    }

    private static void logError(String errorMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("error_log.txt", true))) {
            writer.write(errorMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

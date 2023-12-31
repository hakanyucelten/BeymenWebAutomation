package StepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class StepDefinitions {

    private WebDriver driver;

    private WebDriverWait wait;
    String excelFilePath = "C:/Users/yucel/IdeaProjects/BeymenWebAutomation/src/test/java/StepDefinitions/beymenExcel.xlsx";
    String textFilePath = "C:/Users/yucel/IdeaProjects/BeymenWebAutomation/src/test/java/StepDefinitions/infoOutput.txt";
    ExcelReader excelReader = new ExcelReader(excelFilePath);

    String[] variables = excelReader.readFirstRow();

    String sort = variables[0];
    String gomlek = variables[1];
    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20); // Maximum wait time of 20 seconds

         driver.manage().window().maximize();
        driver.get("https://www.beymen.com/");
    }

    @Given("user is on the Beymen homepage")
    public void userIsOnGoogleHomepage() {

        logger.info("This is an info message");

    }

    @When("user searches for sort")
    public void userSearchesFor() {
        WebElement findheader = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='o-header__search--wrapper']")));
        findheader.click();
        WebElement inputbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"o-searchSuggestion__input\"]")));

        inputbox.sendKeys(sort);
        inputbox.sendKeys(Keys.ENTER);
    }
    @And("user clear search button")
    public void userClearSearchButton() {

        WebElement inputbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"o-header__search--input\" and @placeholder=\"Ürün, Marka Arayın\"]")));
        inputbox.clear();
    }

    @Then("user searches for gomlek")
    public void userSearchForGomlek(){
        WebElement inputbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"o-header__search--input\" and @placeholder=\"Ürün, Marka Arayın\"]")));
        inputbox.sendKeys(gomlek);
        inputbox.sendKeys(Keys.ENTER);
    }
    @And("user clicks on the cookie button")
    public void userClicksOnLink() {
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
        cookieButton.click();
    }

    @And("user clicks on the erkek button")
    public void userClicksOnSignInButton() {
        WebElement erkekButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='a-primaryButton genderPopup__button' and @id='genderManButton']")));
        erkekButton.click();
    }

    @And("choose product and check price")
    public void chooseproduct() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"o-productCard__figure--img lazyload b-loaded\"][1]")));
        product.click();
        WebElement productPrice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='m-price__new' and @id='priceNew']")));
        String productPriceText = productPrice.getText().trim();
        WebElement productInfo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='o-productDetail__description']")));



        String productInfoText = productInfo.getText().trim();
        String[] output = new String[2];
        output[0] = productPriceText;
        output[1] = productInfoText;
        FileWriterClass.writeToFile(textFilePath, output);
        WebElement chooseSize = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sizes']/div/span[2]")));

        chooseSize.click();


        WebElement addtoChart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='m-addBasketFavorite__basket btn' and @id='addBasket']")));

        addtoChart.click();
        WebElement gotoChart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='m-notification__button btn']")));
        gotoChart.click();

        WebElement chartValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='m-orderSummary__value']")));
        String chartValueText = chartValue.getText().trim();

// Comparing the values
        if (productPriceText.equals(chartValueText)) {
            System.out.println("Product price is the same as the Add to Cart value.");
        } else {
            System.out.println("Product price is different from the Add to Cart value.");
        }


    }

    @Then("go back to item page and increase number of item")
    public void increaseItem() {
        WebElement gotoItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='m-basket__productInfoCategory']")));
        gotoItem.click();
        WebElement chooseSize = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sizes']/div/span[2]")));

        chooseSize.click();
        WebElement addtoChart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='m-addBasketFavorite__basket btn' and @id='addBasket']")));

        addtoChart.click();
        WebElement gotoChart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='m-notification__button btn']")));
        gotoChart.click();
        String adet = "2 adet";
        WebElement ItemPiece = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='quantitySelect0-key-0']")));
        ItemPiece.click();

        String chartValueText = ItemPiece.getText().trim();
// Comparing the values
        if (adet.equals(chartValueText)) {
            System.out.println("Item piece increased");
        } else {
            System.out.println("Item piece is still same");
        }



    }
        @Then("delete item from chart")
        public void deleteItem(){
            WebElement deleteChart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='m-basket__remove' and @id='removeCartItemBtn0-key-0']")));
            deleteChart.click();
            WebElement emptyChart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='m-empty__messageTitle']")));
            String emptyChartText = emptyChart.getText().trim();
            String emptyText = "Sepetinizde Ürün Bulunmamaktadır";
            if (emptyText.equals(emptyChartText)) {
                System.out.println("Chart is empty");
            } else {
                System.out.println("Chart is not empty");
            }

        }

    @After
    public void tearDown() {

       if (driver != null) {
           driver.quit();
        }
    }

}

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocators() {

        driver.get("https://www.saucedemo.com/");
        //find by id Username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // find by name Password
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        // find by Class name (if space, use only first class)
        driver.findElement(By.className("submit-button")).click();
        // tagname
        // linktext
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        // partiallinktext
        driver.findElement(By.partialLinkText("Fleece Jacket"));
        //поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
        By.xpath("//span[@name='value']");
        //поиск по тексту, например By.xpath("//tag[text()='text']");
        //поиск по частичному совпадению атрибута, например
        //By.xpath("//tag[contains(@attribute,'text')]");
        //поиск по частичному совпадению текста, например
        //By.xpath("//tag[contains(text(),'text')]");
        //ancestor, например //*[text()='Enterprise Testing']//ancestor::div
        //descendant
        //following
        //parent
        //preceding
        //Подсказка: XPath Axes
        //*поиск элемента с условием AND, например
        ////input[@class='_2zrpKA_1dBPDZ'and@type='text']


    }

}

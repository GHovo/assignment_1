package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest{

    @BeforeMethod
    public void setup(){

    }

    @Test
    //@Parameters("resolution")
    public void testPicsartSearch(String resolution) {
        System.out.println("555");
    }
}

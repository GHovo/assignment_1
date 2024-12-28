package dataprovider;

import org.testng.annotations.DataProvider;

public class ResolutionsDataProvider {

    @DataProvider(name = "resolutionProvider", parallel = true)
    public static Object[][] getResolution() {
        return new Object[][] {
                {1024, 768},
                {1220, 900},
                {1366, 768},
        };
    }
}


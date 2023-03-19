package ServiceClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

class CategoriesAnalystTest {

    CategoriesAnalyst categoriesAnalyst;

    CategoryListFromFile categoryListFromFile = Mockito.mock(CategoryListFromFile.class);

    @BeforeEach
    void beforeEach() {
        String[] testList = {"еда", "быт", "другое"};
        Mockito.when(categoryListFromFile.getAllCategories()).thenReturn(testList);
        categoriesAnalyst = new CategoriesAnalyst(categoryListFromFile);
    }

    @Test
    void test_downloadDataForAnalysis1_inTheBeginning() {
        int expectedSum = 0;

        String givenCategory = "другое";
        int checkSum = categoriesAnalyst.getMapToAnalysis().get(givenCategory);

        Assertions.assertEquals(expectedSum, checkSum);
    }

    @Test
    void test_downloadDataForAnalysis1_once() {
        int expectedSum = 100;

        int givenSum = 100;
        String givenCategory = "еда";
        categoriesAnalyst.downloadDataForAnalysis(givenCategory, givenSum);
        int checkSum = categoriesAnalyst.getMapToAnalysis().get(givenCategory);

        Assertions.assertEquals(expectedSum, checkSum);
    }

    @Test
    void test_downloadDataForAnalysis1_twice() {
        int expectedSum = 200;

        int givenSum = 100;
        String givenCategory = "быт";
        categoriesAnalyst.downloadDataForAnalysis(givenCategory, givenSum);
        categoriesAnalyst.downloadDataForAnalysis(givenCategory, givenSum);
        int checkSum = categoriesAnalyst.getMapToAnalysis().get(givenCategory);

        Assertions.assertEquals(expectedSum, checkSum);
    }

    @Test
    void test_doAnalytics_inTheBeginning() {
        Map<String, MaxCategory> recievedMap = categoriesAnalyst.getCurrentAnalytics();

        Assertions.assertTrue(recievedMap.isEmpty());
    }

    @Test
    void test_doAnalytics_maxValue() {
        int givenSum = 100;
        String givenCategory = "быт";
        categoriesAnalyst.downloadDataForAnalysis(givenCategory, givenSum);

        int value = Collections.max(categoriesAnalyst.getMapToAnalysis().values());
        int expectedValue = 100;

        Assertions.assertEquals(expectedValue, value);
    }

    @Test
    void test_doAnalytics_mapSize() {
        int expected = 1;

        int givenSum = 100;
        String givenCategory = "быт";
        categoriesAnalyst.downloadDataForAnalysis(givenCategory, givenSum);

        categoriesAnalyst.doAnalytics();

        Assertions.assertEquals(expected, categoriesAnalyst.getCurrentAnalytics().size());
    }

    @Test
    void test_doAnalytics_containsKey() {
        String expected = "MaxCategory";

        int givenSum = 100;
        String givenCategory = "быт";
        categoriesAnalyst.downloadDataForAnalysis(givenCategory, givenSum);

        categoriesAnalyst.doAnalytics();

        Assertions.assertTrue(categoriesAnalyst.getCurrentAnalytics().containsKey(expected));
    }

    @Test
    void test_doAnalytics_containsValue() {
        MaxCategory expectedMaxCategory = new MaxCategory("быт", 100);

        int givenSum = 100;
        String givenCategory = "быт";
        categoriesAnalyst.downloadDataForAnalysis(givenCategory, givenSum);

        categoriesAnalyst.doAnalytics();

        Assertions.assertTrue(categoriesAnalyst.getCurrentAnalytics().get("MaxCategory").equals(expectedMaxCategory));
    }

    @Test
    void test_doAnalytics_correctAnalytics() {
        MaxCategory expectedMaxCategory = new MaxCategory("быт", 100);

        categoriesAnalyst.downloadDataForAnalysis("быт", 100);
        categoriesAnalyst.downloadDataForAnalysis("другое", 50);

        categoriesAnalyst.doAnalytics();

        Assertions.assertTrue(categoriesAnalyst.getCurrentAnalytics().get("MaxCategory").equals(expectedMaxCategory));
    }
}
package ServiceClasses;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CategoriesAnalyst {

    private Map<String, Integer> mapToAnalysis = new HashMap<>();
    private Map<String, MaxCategory> currentAnalytics;

    public CategoriesAnalyst(CategoryListFromFile categoryListFromFile) {
        for (String cat : categoryListFromFile.getAllCategories()) {
            mapToAnalysis.put(cat, 0);
        }
        currentAnalytics = new HashMap<>();
    }

    public void downloadDataForAnalysis(String category, int sum) {
        int value = mapToAnalysis.get(category);
        value += sum;
        mapToAnalysis.put(category, value);
    }

    public void doAnalytics() {
        int maxValue = Collections.max(mapToAnalysis.values());
        for (Map.Entry<String, Integer> kv : mapToAnalysis.entrySet()) {
            if (kv.getValue().equals(maxValue)) {
                MaxCategory maxCategory = new MaxCategory(kv.getKey(), kv.getValue());
                currentAnalytics.put("MaxCategory", maxCategory);
            }
        }
    }

    public Map<String, MaxCategory> getCurrentAnalytics() {
        return currentAnalytics;
    }

    public Map<String, Integer> getMapToAnalysis() {
        return mapToAnalysis;
    }


}

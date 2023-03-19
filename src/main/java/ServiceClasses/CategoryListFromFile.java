package ServiceClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CategoryListFromFile {

    private Map<String, String> categoriesMap = new HashMap<>();
    private final String other = "другое";

    public CategoryListFromFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] categoryPair = line.split("\t");
                categoriesMap.put(categoryPair[0], categoryPair[1]);
            }
        }
    }

    public boolean isInList(String name) {
        return categoriesMap.containsKey(name.toLowerCase());
    }

    public String getCategoryOrOther(String name) {
        String recievedCategory;
        if (this.isInList(name.toLowerCase())) {
            recievedCategory = categoriesMap.get(name.toLowerCase());
        } else {
            recievedCategory = other;
        }
        return recievedCategory;
    }

    public String[] getAllCategories() {
        Set<String> allCategoriesSet = new HashSet<>(categoriesMap.values());
        allCategoriesSet.add(other);
        return allCategoriesSet.toArray(new String[0]);
    }
}

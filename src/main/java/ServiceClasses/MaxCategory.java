package ServiceClasses;

import java.util.Objects;

public class MaxCategory {
    private String category;
    private int sum;

    public MaxCategory(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaxCategory that = (MaxCategory) o;
        return Objects.equals(category, that.category);
    }
}

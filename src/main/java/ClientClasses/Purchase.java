package ClientClasses;

public class Purchase {

    private String title;
    private String date;
    private int sum;

    public Purchase(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}

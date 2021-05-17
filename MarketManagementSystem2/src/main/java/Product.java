public class Product {
    String code = "000";
    private String name;
    private double price;
    private Category category;
    private long count;
    static long codeGeneration = 0;

    public Product() {
    }

    public Product(String name, double price, Category category, long count) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.count = count;
        codeGeneration ++;
        code += codeGeneration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", count=" + count +
                '}';
    }
}

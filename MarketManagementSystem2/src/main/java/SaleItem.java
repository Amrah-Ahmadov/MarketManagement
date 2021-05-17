public class SaleItem {
    String number = "0000";
    private String saleProduct;
    private int countOfProduct;
    static long codeForSaleItem = 0;

    public SaleItem() {
    }

    public SaleItem(String saleProduct, int countOfProduct) {

        this.saleProduct = saleProduct;
        this.countOfProduct = countOfProduct;
        codeForSaleItem++;
        number += codeForSaleItem;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(String saleProduct) {
        this.saleProduct = saleProduct;
    }

    public int getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(int countOfProduct) {
        this.countOfProduct = countOfProduct;
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "number='" + number + '\'' +
                ", saleProduct='" + saleProduct + '\'' +
                ", countOfProduct=" + countOfProduct +
                '}';
    }
}

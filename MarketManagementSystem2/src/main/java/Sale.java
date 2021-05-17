import java.time.LocalDateTime;
import java.util.List;

public class Sale {
    String number = "0000";
    private double totalSalesPrice;
    private List<SaleItem> saleItemList;
    private LocalDateTime saleDateTime;
    static long codeForSale = 0;

    public Sale() {
    }

    public Sale(double totalSalesPrice, List<SaleItem> saleItemList, LocalDateTime saleDateTime) {
        this.totalSalesPrice = totalSalesPrice;
        this.saleItemList = saleItemList;
        this.saleDateTime = saleDateTime;
        codeForSale++;
        number += codeForSale;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getTotalSalesPrice() {
        return totalSalesPrice;
    }

    public void setTotalSalesPrice(double totalSalesPrice) {
        this.totalSalesPrice = totalSalesPrice;
    }

    public List<SaleItem> getSaleItemList() {
        return saleItemList;
    }

    public void setSaleItemList(List<SaleItem> saleItemList) {
        this.saleItemList = saleItemList;
    }

    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }

    public void setSaleDateTime(LocalDateTime saleDateTime) {
        this.saleDateTime = saleDateTime;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "number='" + number + '\'' +
                ", totalSalesPrice=" + totalSalesPrice +
                ", saleItemList=" + saleItemList +
                ", saleDateTime=" + saleDateTime +
                '}';
    }
}

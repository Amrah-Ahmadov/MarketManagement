import java.time.LocalDate;
import java.util.List;

public interface IMarketable {
    List<Sale> salesList = null;

    static void addSale(List<String> saleItem, List<Integer> countsOfProduct) {

    }

    static void restitudeProductFromSale(String saleNumber, String productName, int count) {

    }

    static void restutudeWholeSale(String saleNumber) {

    }

    static void showSalesforDateRange(LocalDate startDate, LocalDate endDate) {

    }

    static void showSalesforGivenDate(LocalDate date) {

    }

    static void showSaleforPriceRange(double startPrice, double endPrice) {

    }

    static Sale returnSaleForSaleNumber(String saleNumber) {
        return null;
    }

    static void addNewProduct(String name, double price, Category category, long count) {

    }

    static void modifyExistingProductsParamethers(String productCode, String name, double price, Category category, long count) {

    }

    static void showProductForCategory(Category category) {

    }

    static void showProductForPriceRange(double startPrice, double endPrice) {

    }

    static void showProductForName(String name) {

    }
}

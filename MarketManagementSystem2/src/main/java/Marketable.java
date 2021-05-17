import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Marketable implements IMarketable{
    static Warehause warehause = new Warehause();

    public static void addSale(List<String> saleItem, List<Integer> countsOfProduct) {
        double totalSalePrice = 0;
        List<SaleItem> saleItemList = new ArrayList<>();
        for(int i = 0; i < saleItem.size(); i++){
            saleItemList.add(addSaleItem(saleItem.get(i), countsOfProduct.get(i)));
        }
        for(int i = 0; i < saleItemList.size(); i++){
            Product product = warehause.returnProductIfExist(saleItemList.get(i).getSaleProduct());
            totalSalePrice += product.getPrice() * saleItemList.get(i).getCountOfProduct();
        }
        Sale sale = new Sale(totalSalePrice, saleItemList, LocalDateTime.now());
        salesList.add(sale);
    }

    public static SaleItem addSaleItem(String saleItemName, int countOfProduct){
        Product product = warehause.returnProductIfExist(saleItemName);
        if(product.equals(null)){
            System.out.println("Anbarda bele bir mehsul movcud deyil");
            return null;
        }else{
            long tempNum = product.getCount() - countOfProduct;
            if(tempNum >= 0){
                product.setCount(tempNum);
                SaleItem item = new SaleItem(product.getName(), countOfProduct);
                return item;
            }else{
                System.out.println("Daxil etdiyiniz mesuldan artiq anbarda yoxdur");
                return null;
            }
        }
    }




    public static void restitudeProductFromSale(String saleNumber, String productName, int count) {
        Sale sale = returnSaleForSaleNumber(saleNumber);
        List<SaleItem> saleItemList = sale.getSaleItemList();
        for(int i = 0; i < saleItemList.size(); i++){
            if(saleItemList.get(i).getSaleProduct().equalsIgnoreCase(productName)){
                int productsCount = saleItemList.get(i).getCountOfProduct();
                productsCount -= count;
                //elave et !
                if(productsCount > 0){
                    saleItemList.get(i).setCountOfProduct(productsCount);
                    Product product = warehause.returnProductIfExist(productName);
                    long countOfProdInWarehause = product.getCount();
                    countOfProdInWarehause += count;
                }else if(productsCount <= 0){
                    System.out.println(saleItemList.get(i) + " qaytarildi");
                    saleItemList.remove(i);
                    Product product = warehause.returnProductIfExist(productName);
                    long countOfProdInWarehause = product.getCount();
                    countOfProdInWarehause += count;

                }
            }
        }
        sale.setSaleItemList(saleItemList);
        restutudeWholeSale(saleNumber);
        salesList.add(sale);

    }


    public static void restutudeWholeSale(String saleNumber) {
        for(int i = 0; i < salesList.size(); i++){
            if(salesList.get(i).number.equals(saleNumber)){
                salesList.remove(i);
            }
        }
    }
    public static void showAllSales(){
        for(int i = 0; i < salesList.size(); i++){
            System.out.println("Satisin nomresi: " + salesList.get(i).getNumber() + " Satisin meblegi: " +  salesList.get(i).getTotalSalesPrice() + " Mehsul sayi " + salesList.get(i).getTotalSalesPrice() + " Tarixi: " + salesList.get(i).getSaleDateTime());
        }
    }


    public static void showSalesforDateRange(LocalDateTime startDate, LocalDateTime endDate) {
       for(int i = 0; i < salesList.size(); i++){
           if(salesList.get(i).getSaleDateTime().isAfter(startDate) && salesList.get(i).getSaleDateTime().isBefore(endDate)){
               System.out.println("Satisin nomresi: " + salesList.get(i).getNumber() + " Satisin meblegi: " +  salesList.get(i).getTotalSalesPrice() + " Mehsul sayi " + salesList.get(i).getTotalSalesPrice() + " Tarixi: " + salesList.get(i).getSaleDateTime());
           }
       }
    }


    public static void showSalesforGivenDate(LocalDate date) {
        for(int i = 0; i < salesList.size(); i++){
            LocalDateTime localDateTime = salesList.get(i).getSaleDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            if(localDate.compareTo(date) == 0){
                System.out.println("Satisin nomresi: " + salesList.get(i).getNumber() + " Satisin meblegi: " +  salesList.get(i).getTotalSalesPrice() + " Mehsul sayi " + salesList.get(i).getTotalSalesPrice() + " Tarixi: " + salesList.get(i).getSaleDateTime());
            }
        }
    }


    public static void showSaleforPriceRange(double startPrice, double endPrice) {
        for(int i = 0; i < salesList.size(); i++){
            if(salesList.get(i).getTotalSalesPrice() >= startPrice && salesList.get(i).getTotalSalesPrice() <= endPrice){
                System.out.println("Satisin nomresi: " + salesList.get(i).getNumber() + " Satisin meblegi: " +  salesList.get(i).getTotalSalesPrice() + " Mehsul sayi " + salesList.get(i).getTotalSalesPrice() + " Tarixi: " + salesList.get(i).getSaleDateTime());
            }
        }
    }


    public static Sale returnSaleForSaleNumber(String saleNumber) {
        Sale sale = null;
        if(salesList.size() == 0){
            System.out.println("Hec bir satis olunmayib");
        }else{
            for(int i = 0; i < salesList.size(); i++){
                if(salesList.get(i).number.equals(saleNumber)){
                    sale = salesList.get(i);
                }
            }
        }
        if(sale.equals(null)){
            System.out.println("Bele bir nomreli satis tepilmadi");
            return null;
        }else{
            return sale;
        }
    }

    public static void addNewProduct(String name, double price, Category category, long count){
            Product product = new Product(name, price, category, count);
        System.out.println(product.getName() + " adli mehsul yaradildi, kodu: " + product.getCode());
        warehause.getProductWarehause().add(product);
    }


    public static void modifyExistingProductsParamethers(String productCode, String name, double price, Category category, long count) {
           Product product = warehause.getProductByCode(productCode);
           product.setName(name);
           product.setPrice(price);
           product.setCategory(category);
           product.setCount(count);
    }

    public static void deleteProduct(String productCode){
        warehause.delete(productCode);
    }

    public static void showAllProducts(){
        warehause.showAll();
    }


    public static void showProductForCategory(Category category) {
        warehause.showProductByCategory(category);
    }


    public static void showProductForPriceRange(double startPrice, double endPrice) {
        warehause.showProductByPriceRange(startPrice, endPrice);
    }


    public static void showProductForName(String name) {
        warehause.showProductByNameMatch(name);
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int commandNum;
        System.out.println("Mehsullar uzerinde emeliyyat aparmaq ucun 1 reqemini daxil edin: ");
        System.out.println("Satislar uzerinde emeliyyat aparmaq ucun 2 reqemini daxil edin: ");
        System.out.println("Sistemden cixmaq ucun 3 reqemini daxil edin: ");
        commandNum = scan.nextInt();
        if(commandNum == 1){
            System.out.println("Yeni mehsul elave etmek ucun 1 reqemini daxil edin: ");
            System.out.println("Mehsul uzerinde duzelis etmek ucun 2 reqemini daxil edin:");
            System.out.println("Mehsulu silmek ucun 3 reqemini daxil edin: ");
            System.out.println("Butun mehsullari gostermek ucun 4 reqemini daxil edin: ");
            System.out.println("Categoriyasina gore mehsullari gostermek ucun 5 reqemini daxil edin:");
            System.out.println("Qiymet araligina gore mehsullari gostermek ucun 6 reqemini daxil edin:");
            System.out.println("Mehsullar arasinda ada gore axtaris etmek ucun 7 reqemini daxil edin:");

            commandNum = scan.nextInt();

            if(commandNum == 1){

                System.out.println("Mehsul kateqoriyasini secin: ");
                System.out.println("ALCOHOL ucun 1 reqemini daxil edin: ");
                System.out.println("CANNED_GOODS ucun 2 reqemini daxil edin: ");
                System.out.println("MEAT_AND_SEAFOODS ucun 3 reqemini daxil edin: ");
                System.out.println("TOBACCO ucun 4 reqemini daxil edin: ");
                System.out.println("BREAD_AND_BAKERY ucun 5 reqemini daxil edin: ");
                System.out.println("DAIRY_EGGS_AND_CHEESE ucun 6 reqemini daxil edin: ");
                System.out.println("BEVERAGES ucun 7 reqemini daxil edin: ");
                System.out.println("FRUITS_AND_VEGETABLES ucun 8 reqemini daxil edin: ");
                System.out.println("FROZEN_FOODS ucun 9 reqemini daxil edin: ");

                commandNum = scan.nextInt();
                System.out.println("Mehsulun adini daxil edin: ");
                String sn;
                sn = scan.nextLine();
                System.out.println("Mehsulun deyerini daxil edin: ");
                double qiymet;
                qiymet = scan.nextDouble();
                System.out.println("Mehsulun sayini daxil edin: ");
                long say;
                say = scan.nextLong();
                Category kateqoriya;
                switch(commandNum) {
                    case 1:
                        kateqoriya = Category.ALCOHOL;
                        break;
                    case 2:
                        kateqoriya = Category.CANNED_GOODS;
                        break;
                    case 3:
                        kateqoriya = Category.MEAT_AND_SEAFOODS;
                        break;
                    case 4:
                        kateqoriya = Category.TOBACCO;
                        break;
                    case 5:
                        kateqoriya = Category.BREAD_AND_BAKERY;
                        break;
                    case 6:
                        kateqoriya = Category.DAIRY_EGGS_AND_CHEESE;
                        break;
                    case 7:
                        kateqoriya = Category.BEVERAGES;
                        break;
                    case 8:
                        kateqoriya = Category.FRUITS_AND_VEGETABLES;
                        break;
                    default:
                        kateqoriya = Category.FROZEN_FOODS;
                        break;
                }
                addNewProduct(sn, qiymet, kateqoriya, say);

            }
            else if(commandNum ==2 ){
             //   String productCode, String name, double price, Category category, long count
                System.out.println("Mehsul kodunu daxil edin:");
                String code = scan.nextLine();
                System.out.println("Mehsulun adini daxil edin: ");
                String ad = scan.nextLine();
                System.out.println("Mehsulun qiymetini daxil edin: ");
                double qiymet = scan.nextDouble();
                System.out.println("Mehsul kateqoriyasini secin: ");
                System.out.println("ALCOHOL ucun 1 reqemini daxil edin: ");
                System.out.println("CANNED_GOODS ucun 2 reqemini daxil edin: ");
                System.out.println("MEAT_AND_SEAFOODS ucun 3 reqemini daxil edin: ");
                System.out.println("TOBACCO ucun 4 reqemini daxil edin: ");
                System.out.println("BREAD_AND_BAKERY ucun 5 reqemini daxil edin: ");
                System.out.println("DAIRY_EGGS_AND_CHEESE ucun 6 reqemini daxil edin: ");
                System.out.println("BEVERAGES ucun 7 reqemini daxil edin: ");
                System.out.println("FRUITS_AND_VEGETABLES ucun 8 reqemini daxil edin: ");
                System.out.println("FROZEN_FOODS ucun 9 reqemini daxil edin: ");
                commandNum = scan.nextInt();
                Category kateqoriya;
                switch(commandNum) {
                    case 1:
                        kateqoriya = Category.ALCOHOL;
                        break;
                    case 2:
                        kateqoriya = Category.CANNED_GOODS;
                        break;
                    case 3:
                        kateqoriya = Category.MEAT_AND_SEAFOODS;
                        break;
                    case 4:
                        kateqoriya = Category.TOBACCO;
                        break;
                    case 5:
                        kateqoriya = Category.BREAD_AND_BAKERY;
                        break;
                    case 6:
                        kateqoriya = Category.DAIRY_EGGS_AND_CHEESE;
                        break;
                    case 7:
                        kateqoriya = Category.BEVERAGES;
                        break;
                    case 8:
                        kateqoriya = Category.FRUITS_AND_VEGETABLES;
                        break;
                    default:
                        kateqoriya = Category.FROZEN_FOODS;
                        break;
                }
                long say = scan.nextLong();
                modifyExistingProductsParamethers(code, ad, qiymet, kateqoriya, say);

            } else if(commandNum == 3){

                System.out.println("Mehsulun kodunu daxil edin:");
                String mehsulKodu = scan.nextLine();
                deleteProduct(mehsulKodu);
            }else if(commandNum == 4){
                showAllProducts();
            }else if(commandNum == 5){
                System.out.println("Mehsul kateqoriyasini secin: ");
                System.out.println("ALCOHOL ucun 1 reqemini daxil edin: ");
                System.out.println("CANNED_GOODS ucun 2 reqemini daxil edin: ");
                System.out.println("MEAT_AND_SEAFOODS ucun 3 reqemini daxil edin: ");
                System.out.println("TOBACCO ucun 4 reqemini daxil edin: ");
                System.out.println("BREAD_AND_BAKERY ucun 5 reqemini daxil edin: ");
                System.out.println("DAIRY_EGGS_AND_CHEESE ucun 6 reqemini daxil edin: ");
                System.out.println("BEVERAGES ucun 7 reqemini daxil edin: ");
                System.out.println("FRUITS_AND_VEGETABLES ucun 8 reqemini daxil edin: ");
                System.out.println("FROZEN_FOODS ucun 9 reqemini daxil edin: ");
                commandNum = scan.nextInt();
                Category kateqoriya;
                switch(commandNum) {
                    case 1:
                        kateqoriya = Category.ALCOHOL;
                        break;
                    case 2:
                        kateqoriya = Category.CANNED_GOODS;
                        break;
                    case 3:
                        kateqoriya = Category.MEAT_AND_SEAFOODS;
                        break;
                    case 4:
                        kateqoriya = Category.TOBACCO;
                        break;
                    case 5:
                        kateqoriya = Category.BREAD_AND_BAKERY;
                        break;
                    case 6:
                        kateqoriya = Category.DAIRY_EGGS_AND_CHEESE;
                        break;
                    case 7:
                        kateqoriya = Category.BEVERAGES;
                        break;
                    case 8:
                        kateqoriya = Category.FRUITS_AND_VEGETABLES;
                        break;
                    default:
                        kateqoriya = Category.FROZEN_FOODS;
                        break;
                }
                showProductForCategory(kateqoriya);
            }else if(commandNum == 6){

                double baslangic = scan.nextDouble();
                double son = scan.nextDouble();
                showProductForPriceRange(baslangic,son);
            }else{

                String ad = scan.nextLine();
                showProductForName(ad);
            }
        }else if(commandNum == 2){
            System.out.println("Yeni satis elave etmek ucun 1 reqemini daxil edin:");
            System.out.println("Satisdaki hansisa mehsulun geri qaytarilmasi ucun 2 reqemini daxil edin:");
            System.out.println("Satisin silinmesi ucun 3 reqemini daxil edin:");
            System.out.println("Butun satislarin ekrana cixarilmasi ucun 4 reqemini daxil edin:");
            System.out.println("Verilen tarix araligina gore satislarin gosterilmesi ucun 5 reqemini daxil edin:");
            System.out.println("Verilen mebleg araligina gore satislarin gosterilmesi ucun 6 reqemini daxil edin:");
            System.out.println("Verilmis bir tarixde olan satislarin gosterilmesi  ucun 7 reqemini daxil edin:");
            System.out.println("Verilmis nomreye esasen hemin nomreli satisin melumatlarinin gosterilmesi ucun 8 reqemini daxil edin:");

            commandNum = scan.nextInt();
            if(commandNum == 1){

                List<String> saleItems = new ArrayList<>();
                List<Integer> countsOfProducts = new ArrayList<>();

                while(scan.hasNext()){
                    String saleItem = scan.nextLine();
                    int countOfProd = scan.nextInt();
                    saleItems.add(saleItem);
                    countsOfProducts.add(countOfProd);
                }
                addSale(saleItems, countsOfProducts);
            }else if(commandNum == 2){

                System.out.println("Mehsulun nomresini daxil edin: ");
                String number = scan.nextLine();
                System.out.println("Mehsulun adini daxil edin:");
                String mehsulAdi = scan.nextLine();
                System.out.println("sayi daxil edin:");
                int say = scan.nextInt();
                restitudeProductFromSale(number, mehsulAdi, say);
            }else if(commandNum == 3){
                /// restutudeWholeSale(String saleNumber)
                System.out.println("Satis nomresini daxil edin:");
                String satisNomresi = scan.nextLine();
                restutudeWholeSale(satisNomresi);
            }else if(commandNum == 4){
                showAllSales();

            }else if(commandNum == 5){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                System.out.println("ilkin tarixi qeyd edin:");
                String firstDate = scan.nextLine();
                LocalDate localDate1 = LocalDate.parse(firstDate, formatter);
                System.out.println("ikinci tarixi daxil edin:");
                String secondDate = scan.nextLine();
                LocalDate localDate2 = LocalDate.parse(firstDate, formatter);
                showSalesforDateRange(localDate1.atStartOfDay(), localDate2.atStartOfDay());
            }else if(commandNum == 6){

                System.out.println("Mebleg araliginin birinci ededini daxil edin: ");
                double firstNum = scan.nextDouble();
                System.out.println("Mebleg araliginin ikinci ededini daxil edin: ");
                double secondNum = scan.nextDouble();
                showSaleforPriceRange(firstNum, secondNum);
            }else if(commandNum == 7){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                System.out.println("tarixi qeyd edin:");
                String givenDate = scan.nextLine();
                LocalDate localDate1 = LocalDate.parse(givenDate, formatter);

                showSalesforGivenDate(localDate1);
            }else{

                System.out.println("Satis nomresini daxil edin:");
                String satisNom = scan.nextLine();
                returnSaleForSaleNumber(satisNom);
            }
        }else{
            System.out.println("proqram basa catdi");
        }
    }
}

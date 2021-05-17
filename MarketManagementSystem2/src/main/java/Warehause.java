import java.util.ArrayList;
import java.util.List;

public class Warehause {
    private List<Product> productWarehause;

    public Warehause() {
        this.productWarehause = new ArrayList<>();
    }

    public List<Product> getProductWarehause() {
        return productWarehause;
    }

    public void setProductWarehause(List<Product> productWarehause) {
        this.productWarehause = productWarehause;
    }

    public Product getProductByCode(String code){
        boolean exist = false;
        Product tempProduct = null;
        for(int i = 0; i < productWarehause.size(); i++){
            if(productWarehause.get(i).getCode().equals(code)){
                exist = true;
                tempProduct = productWarehause.get(i);
            }
        }
        if(exist){
            return tempProduct;
        }else{
            return null;
        }
    }
    public void delete(String code){
        boolean exist = false;
        for(int i = 0; i < productWarehause.size(); i++){
            if(productWarehause.get(i).getCode().equals(code)){
                exist = true;
                productWarehause.remove(i);
            }
        }
        if(exist){
            System.out.println("silindi");
        }else{
            System.out.println("silmek istediyiniz mehsul movcud deyil !");
        }
    }

    public void showAll(){
        if(productWarehause.size() != 0){
            for(int i = 0; i < productWarehause.size(); i++){
                System.out.println("Mehsulun kodu: " + productWarehause.get(i).getCode() + " Mehsulun adi: " + productWarehause.get(i).getName() + " Mehsulun kateqoriyasi: "+ productWarehause.get(i).getCategory()+ " Mehsulun sayi: " + productWarehause.get(i).getCount()+ " Mehsulun qiymeti: " + productWarehause.get(i).getPrice());
            }
        }else{
            System.out.println("Anbar bosdur");
        }
    }
    public void showProductByCategory(Category category){
        for(Product p: productWarehause){
            if(p.getCategory().equals(category)){
                System.out.println("Mehsulun kodu: " + p.getCode() + " Mesulun adi: " + p.getName() + " Mehsulun kateqoriyasi: " + p.getCategory() + " Mehsulun sayi: " + p.getCount() + " Mehsulun qiymeti: " + p.getPrice());
            }
        }
    }

    public void showProductByPriceRange(double startPrice, double endPrice){
        for(Product p: productWarehause){
            if(p.getPrice() >= startPrice && p.getPrice() <= endPrice){
                System.out.println("Mehsulun kodu: " + p.getCode() + " Mesulun adi: " + p.getName() + " Mehsulun kateqoriyasi: " + p.getCategory() + " Mehsulun sayi: " + p.getCount() + " Mehsulun qiymeti: " + p.getPrice());
            }
        }
    }
    public void showProductByNameMatch(String name){
        for(Product p: productWarehause){
            if(p.getName().toLowerCase().contains(name.toLowerCase())){
                System.out.println("Mehsulun kodu: " + p.getCode() + " Mesulun adi: " + p.getName() + " Mehsulun kateqoriyasi: " + p.getCategory() + " Mehsulun sayi: " + p.getCount() + " Mehsulun qiymeti: " + p.getPrice());

            }
        }
    }

    public Product returnProductIfExist(String name){
        Product prod = null;
        for(int i = 0; i < productWarehause.size(); i++){
            if(productWarehause.get(i).getName().equalsIgnoreCase(name)){
                prod = productWarehause.get(i);
            }
        }
        return prod;
    }

    @Override
    public String toString() {
        return "Warehause{" +
                "productWarehause=" + productWarehause +
                '}';
    }
}

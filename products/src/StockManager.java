import java.util.ArrayList;

public class StockManager {
    ArrayList<Product> products = new ArrayList<Product>();

    //遍历产品,打印出所有产品的详细信息
    public void printProductDetails(){
        for(Product product:products){
            product.toString();
        }
    }

    //在容器中搜索与其参数ID相匹配的ID的产品
    public Product findProduct(String id){
        for(Product product:products){
            if (id.equals(product.ID)){
                return product;
            }
        }
        return null;
    }

    //找到ID匹配的产品,返回该产品当前的数量
    public int numberInStock(String id){
        Product product = findProduct(id);
       if(product != null){
           return product.quantity;
       }
        return 0;
    }

    //通过ID找到产品,增加指定的库存数量
    public int delivery(String id,int num){
        Product product = findProduct(id);
        if(product != null){
            product.increaseQuantity(num);
            return 1;
        }
        return 0;
    }

    //打印出所有库存水平低于给定值的产品的详情
    public void printLowStockProducts(int low){
        for(Product product:products){
            if(product.quantity < low){
                product.toString();
            }
        }
    }

    //新产品的ID如果与已有产品列表的ID相同就无法加入
    public void addProduct(Product p) {
        Product product = findProduct(p.ID);
        if (product == null) {
            products.add(p);
        }
        else{
            System.out.println("已存在该产品的ID，无法添加.");
        }
    }

    //根据产品的名称在容器中搜索相匹配的产品
    public Product findProductByName(String name){
        for(Product product:products){
            if(name.equals(product.name)){
                product.toString();
                return product;
            }
        }
        return null;
    }
}

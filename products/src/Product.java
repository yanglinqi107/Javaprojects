public class Product {
    String ID = new String();
    String name = new String();
    int quantity = 0;

    public int SellOne(){
        if(quantity - 1 > -1){
            return quantity ;
        }
        else{
            return 0;
        }
    }

    public void increaseQuantity(int num){
         quantity += num;
    }

    public String toString(){
        System.out.println("----------------");
        System.out.println("产品ID:"+ ID);
        System.out.println("产品名称:"+ name);
        System.out.println("产品库存:"+ quantity);
        return null;
    }

    public void setID(String id) { ID = id; }

    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(int num){
        quantity = num;
    }

    public String getID() { return ID; }

    public String getName() { return name; }

    public int getQuantity() { return quantity; }
}

import java.util.Scanner;

public class StockDemo {
    public static void main(String[] args){
        StockManager test = new StockManager();
        Scanner scan = new Scanner(System.in);
        boolean quitFlag = true;
        String id = new String();
        String name = new String();
        int num;

        while(quitFlag){
            System.out.print("\n");
            System.out.println("1.添加产品");
            System.out.println("2.查找产品(ID)并显示库存");
            System.out.println("3.查找产品(ID)并添加库存");
            System.out.println("4.查找产品(名字)");
            System.out.println("5.显示库存低于某数的产品");
            System.out.println("6.显示所有产品");
            System.out.println("0.退出");
            String input = new String();
            input = scan.nextLine();
            switch(input){
                case "1":
                    Product product = new Product();
                    System.out.println("请输入ID:");
                    id = scan.nextLine();
                    product.setID(id);

                    System.out.println("请输入名称:");
                    name = scan.nextLine();
                    product.setName(name);

                    System.out.println("请输入库存:");
                    do{
                        num = scan.nextInt();
                        scan.nextLine();
                        if(num < 0){
                            System.out.println("请重输库存");
                        }
                    }while(num < 0);
                    product.setQuantity(num);

                    test.addProduct(product);
                    break;

                case "2":
                    System.out.println("请输入产品ID:");
                    id = scan.nextLine();
                    num = test.numberInStock(id);
                    if(num == 0){
                        System.out.println("该ID不存在");
                    }
                    else{
                        System.out.println("库存:"+ num);
                    }
                    break;

                case "3":
                    System.out.println("请输入产品ID:");
                    id = scan.nextLine();
                    System.out.println("请输入添加的数目:");
                    do{
                        num = scan.nextInt();
                        scan.nextLine();
                        if(num < 0){
                            System.out.println("请重输数值");
                        }
                    }while(num < 0);
                    num = test.delivery(id,num);
                    if(num == 0){
                        System.out.println("该ID不存在");
                    }
                    else{
                        System.out.println("添加成功");
                    }
                    break;

                case "4":
                    System.out.println("输入产品名称:");
                    name = scan.nextLine();
                    product = test.findProductByName(name);
                    if(product == null){
                        System.out.println("未找到");
                    }
                    break;

                case "5":
                    System.out.println("请输入阈值:");
                    do{
                        num = scan.nextInt();
                        scan.nextLine();
                        if(num < 0){
                            System.out.println("请重输阈值");
                        }
                    }while(num < 0);
                    test.printLowStockProducts(num);
                    break;

                case "6":
                    test.printProductDetails();
                    break;

                case "0":
                    quitFlag = false;
                    break;

                default:
                    System.out.println("请输入正确的序号.");
            }
        }
    }
}

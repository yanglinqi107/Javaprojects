//Words2.java

class Book2{
    private int pages = 300;
    public void setPages(int pages){this.pages = pages;}
    public int getPages(){return pages;}
}

class Dictionary2 extends Book2{
    private int definitions = 5000;
    public double computeRatio(){return definitions / getPages();}
    public void setDefinitions(int definitions){this.definitions = definitions;}
    public int getDefinitions() {
        return definitions;
    }
}
public class Words2 {
    public static void main(String[] args){
        Dictionary2 xinhua = new Dictionary2();
        System.out.println("页数:" + xinhua.getPages());
        System.out.println("词条数:" + xinhua.getDefinitions());
        System.out.println("每页词条数:" + xinhua.computeRatio());
    }
}

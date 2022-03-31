//Words.java

class Book{
    protected int pages = 300;
    public void setPages(int pages){this.pages = pages;}
    public int getPages(){return pages;}
}

class Dictionary extends Book{
    private int definitions = 5000;
    public double computeRatio(){return definitions / pages;}
    public void setDefinitions(int definitions){this.definitions = definitions;}
    public int getDefinitions() {
        return definitions;
    }
}
public class Words {
    public static void main(String[] args){
        Dictionary xinhua = new Dictionary();
        System.out.println("页数:" + xinhua.getPages());
        System.out.println("词条数:" + xinhua.getDefinitions());
        System.out.println("每页词条数:" + xinhua.computeRatio());
    }
}

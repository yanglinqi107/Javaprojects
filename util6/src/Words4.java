//Words4.java

class Book4{
    protected int pages = 300;
    public void setPages(int pages){this.pages = pages;}
    public int getPages(){return pages;}
}

class Dictionary4 extends Book4{
    private int definitions = 5000;
    public double computeRatio(){return definitions / pages;}
    public void setDefinitions(int definitions){this.definitions = definitions;}
    public int getDefinitions() {
        return definitions;
    }
    public void setPages(int pages){
        System.out.println("Dictionary.setPages()");
        this.pages = pages;
    }
}
public class Words4 {
    public static void main(String[] args){
        Book4 xinhua = new Dictionary4();
        xinhua.setPages(123);
        System.out.println("页数:" + xinhua.getPages());
    }
}

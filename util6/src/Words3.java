//Words3.java

class Book3{
    private int pages = 300;
    public void setPages(int pages){this.pages = pages;}
    public int getPages(){return pages;}
}

class Dictionary3 extends Book3{
    private int definitions = 5000;
    public double computeRatio(){return definitions / getPages();}
    public void setDefinitions(int definitions){this.definitions = definitions;}
    public int getDefinitions() {
        return definitions;
    }
    public void setPages(int pages){
        if(getPages() + pages % 2 == 0){
            super.setPages(getPages() + pages);
        }
        else{
            super.setPages(getPages() + pages +1);
        }
    }
}
public class Words3 {
    public static void main(String[] args){
        Dictionary3 xinhua = new Dictionary3();
        xinhua.setPages(123);
        System.out.println("页数:" + xinhua.getPages());
        System.out.println("词条数:" + xinhua.getDefinitions());
        System.out.println("每页词条数:" + xinhua.computeRatio());
    }
}

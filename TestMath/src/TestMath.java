//TestMath.java

public class TestMath {
    public static void main(String[] args) {
        System.out.println("E:" + Math.E);
        System.out.println("PI:" + Math.PI);

        //abs()
        System.out.println("abs(1234.56):"+ Math.abs(1234.56));
        System.out.println("abs(-0.0):"+ Math.abs(-0.0));

        //ceil()
        System.out.println("ceil(5.04):"+ Math.ceil(5.04));
        System.out.println("ceil(-5.04):"+ Math.ceil(-5.04));
        System.out.println("ceil(10):"+ Math.ceil(10));
        System.out.println("ceil(-0.03):"+ Math.ceil(-0.03));

        //floor()
        System.out.println("floor(5.04):"+ Math.floor(5.04));
        System.out.println("floor(-5.04):"+ Math.floor(-5.04));
        System.out.println("floor(10):"+ Math.floor(10));
        System.out.println("floor(-0.03):"+ Math.floor(-0.03));

        //min() and max()
        System.out.println("min(-1.5,1.5):"+ Math.min(-1.5,1.5));
        System.out.println("max(-1.5,1.5):"+ Math.max(-1.5,1.5));
        System.out.println("min(-0.0,0.0):"+ Math.min(-0.0,0.0));

        //random()
        System.out.println("random():"+ Math.random());

        //round()
        System.out.println("round(1.5):"+ Math.round(1.5));
        System.out.println("round(-1.5):"+ Math.round(-1.5));

        //sqrt()
        System.out.println("sqrt(45):"+ Math.sqrt(45));
        System.out.println("sqrt(-45):"+ Math.sqrt(-45));

        //trig functions
        System.out.println("sin(90):"+ Math.sin(90));
        System.out.println("cos(90):"+ Math.cos(90));
        System.out.println("tan(90):"+ Math.tan(90));
        System.out.println("asin(-0):"+ Math.asin(-0));
        System.out.println("acos(-0):"+ Math.acos(-0));
        System.out.println("atan(90):"+ Math.atan(90));
        System.out.println("toRadians(90):"+ Math.toRadians(90));
        System.out.println("toDegrees(Math.PI/2):"+ Math.toDegrees(Math.PI/2));

        //logs
        System.out.println("log(10):"+ Math.log(10));
        System.out.println("log(-10):"+ Math.log(-10));
        System.out.println("log(0.0):"+ Math.log(0.0));
        System.out.println("exp(5):"+ Math.exp(5));

        //pow()
        System.out.println("pow(2,2):"+ Math.pow(2,2));
    }
}

package learn.classextends;

public class Animal {
    public Animal(){
        this(new String("2"));
    }
    public Animal(String name){
        this.name = name;
    }

    public String name = "animal";


    public int eat(){
        System.out.println("动物都能吃");
        return 1;
    }
    public long eat(int a){
        return 1;
    }
}
class cat extends Animal{
    public String name = "cat";
    @Override
    public int eat() {
        System.out.println("猫也能吃");
        return 1;
    }

    public void catchMouse () {
        System.out.println("抓耗子");
    }
}
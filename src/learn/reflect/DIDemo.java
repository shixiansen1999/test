package learn.reflect;

public class DIDemo {
    Service service;//对象默认值为null，必须实例化，不然不能执行callService方法

    public void callService(){
        service.command();//java.lang.NullPointerException
    }

    public static void main(String[] args) {
        DIDemo di = new DIDemo();
        di.callService();
    }
}

class Service{
    public void command(){
        System.out.println("run service : command");
    }
}

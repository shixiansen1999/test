package learn.reflect;

import java.lang.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationDemo {

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface QueryParam{
        String value();
    }

    String name;

    AnnotationDemo(String name){
        this.name = name;
    }

    public void hello(@QueryParam("action") String action){

    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        AnnotationDemo a = new AnnotationDemo("red");
        Class<AnnotationDemo> cls = AnnotationDemo.class;
        Method hello = cls.getDeclaredMethod("hello", String.class);
        Annotation[][] at = hello.getParameterAnnotations();
        System.out.println(at.toString());
        System.out.println(at[0][0].annotationType().getSimpleName());
        Field fName = cls.getDeclaredField("name");
        Object o = fName.get(a);
        System.out.println(o);
    }
}

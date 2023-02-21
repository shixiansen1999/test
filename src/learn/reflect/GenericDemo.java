package learn.reflect;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class GenericDemo {
    static class GenericTest<U extends Comparable<U>, V>{
        U u;
        V v;
        List<String> list;
        public U test(List<? extends Number> numbers, U u){
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<GenericTest> cls = GenericTest.class;
        //类的类型参数
        for (TypeVariable t : cls.getTypeParameters()){
            System.out.println(t.getName()+":extends:"+ Arrays.toString(t.getBounds()));
        }
        //字段：泛型类型
        Field u = cls.getDeclaredField("u");
        System.out.println("field type:"+u.getGenericType());
        //字段：参数化的类型
        Field list = cls.getDeclaredField("list");
        Type listType = list.getGenericType();
        System.out.println("list type:"+listType);
        if (listType instanceof ParameterizedType){
            ParameterizedType pType = (ParameterizedType) listType;
            System.out.println("raw type:"+pType.getRawType()+",type arguments:"+Arrays.toString(pType.getActualTypeArguments()));
        }
        //方法的泛型参数
        Method method = cls.getDeclaredMethod("test", List.class, Comparable.class);
        System.out.println("method return type:"+method.getGenericReturnType());
        for (Type t : method.getGenericParameterTypes()){
            System.out.println("method parameter type:"+t.getTypeName());
        }

    }
}

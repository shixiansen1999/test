package learn.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticSyncThread extends Thread {
    private Class aClass;

    public StaticSyncThread(Class aClass){
        this.aClass = aClass;
    }

    @Override
    public void run() {
        try {
            aClass.getMethod("demo").invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

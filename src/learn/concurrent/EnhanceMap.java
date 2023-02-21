package learn.concurrent;

import java.util.Collections;
import java.util.Map;

public class EnhanceMap<K,V> {
    Map<K,V> map;

    public EnhanceMap(Map<K,V> map){
        this.map = Collections.synchronizedMap(map);
    }

    public synchronized V putIfAbsent(K key, V value){
        V old = map.get(key);
        if (old != null){
            return old;
        }
        return map.put(key,value);
    }

    //测试同时执行同步方法与非同步方法的情况。
    public V put(K key, V value){
        return map.put(key,value);
    }

    public synchronized Map<K, V> getMap() {
        return map;
    }

    public synchronized void setMap(Map<K, V> map) {
        this.map = map;
    }
}

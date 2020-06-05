package com.zss.api.limit.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

public class Bloom {

    @Autowired
    private RedisTemplate redisTemplate;

    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,0.01);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++){
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<>();
        for(int i = size + 10000; i < size + 20000; i++){
            if(bloomFilter.mightContain(i)){
                list.add(i);
            }
        }

        System.out.println(String.format("误判的个数 %d，误判率 %s",list.size(),(double)(list.size()/10000)));
    }

    /*public static String get(String key) {
        String value = redisTemplate.get(key);
        if (value  == null) {
            if(!bloomFilter.mightContain(key)){
                return null;
            }else{
                value = db.get(key);
                redis.set(key, value);
            }
        }
        return value；
    }*/
}

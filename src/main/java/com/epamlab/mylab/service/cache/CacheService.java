package com.epamlab.mylab.service.cache;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.epamlab.mylab.entity.YearEntity;

@Component
public class CacheService {
    private final HashMap<Integer, YearEntity> cache = new HashMap<>();

    public YearEntity getCache(Integer value) {
        return cache.get(value);
    }

    public void putCache(YearEntity object) {
        this.cache.put(object.getYear(), object);
    }
    
    public Boolean contains(Integer value) {
        return this.cache.containsKey(value);
    }

}

package com.violin.recyclerviewdemo.demo;

import com.violin.recyclerviewdemo.refresh.IDragDistanceConverter;

/**
 * Created by whl on 2017/8/16.
 */

public class RefreshDiatanceConverter implements IDragDistanceConverter {
    @Override
    public float convert(float scrollDistance, float refreshDistance) {
        return scrollDistance*0.3f;
    }
}

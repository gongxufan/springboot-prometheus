package com.gxf.app.monitor.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DemoCtrl {
    private Random random = new Random();

    // 一个用于演示的http接口
    @GetMapping(path = "random")
    public String random(String name) {
        int sleep = random.nextInt(200);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("sleep:%s millis,for %s", sleep ,name);
    }
}

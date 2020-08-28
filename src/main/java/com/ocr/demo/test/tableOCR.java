package com.ocr.demo.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class tableOCR {

    @RequestMapping("test")
    public String test() throws InterruptedException, IOException {
        Sample sample = new Sample();
        return sample.test();
    }
}

package com.ocr.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testSample {

    @Test
    public void testMatch(){


        String test = "{\"result\":[{\"request_id\":\"22265037_2077490\"}],\"log_id\":1598431509996896}";
        String match1 = ":\"[\\s\\S]+(?<=\"}])";
        String match = "(?<=:\")[\\s\\S]+(?=\"}])";
        Pattern p =Pattern.compile(match);

        Matcher m=p.matcher(test);
        System.out.println(m.toString());

        m.find();
        String trigger=m.group(0); //全局是0，第一个括号是1，第二个是2，以此类推
        System.out.println(trigger);
    }
}

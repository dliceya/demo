package com.ocr.demo.test;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "22265037";
    public static final String API_KEY = "u3R9rsnnhjOIC6hHKAtmkAj6";
    public static final String SECRET_KEY = "KSFiMXu39nvcOadMvBayvQfE0YuwyPoq";

    public String test() throws InterruptedException, IOException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

/*        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理*/

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "D:\\Project\\test.png";
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("result_type", "json");
        //JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        JSONObject res = client.tableRecognitionAsync(path, options);

//        com.alibaba.fastjson.JSONObject;
        String requestId = getRequestId(res.toString());
        Thread.sleep(4000);
        JSONObject ocrResult = client.tableResultGet(requestId, options);

        System.out.println(ocrResult.getJSONObject("result"));


        JSONObject jsonObject = new JSONObject(
                ocrResult.getJSONObject("result")
                        .getString("result_data"));

        String forms = ((JSONObject)jsonObject.getJSONArray("forms").get(0))
                .getJSONArray("body").toString();

        System.out.println(jsonObject);
        String tableJson = ocrResult.getJSONObject("result").getString("result_data");

        FileOutputStream fos = new FileOutputStream("C:/Users/HuaMeiTraining/Desktop/test.json");
        fos.write(forms.getBytes());
        return forms;

    }

    private String getRequestId(String result) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:/Users/HuaMeiTraining/Desktop/test.json");
        String match = "(?<=:\")[\\s\\S]+(?=\"}])";
        Pattern p =Pattern.compile(match);

        Matcher m=p.matcher(result);
        System.out.println(m.toString());
        String trigger;

        while(m.find()) {
            trigger=m.group(0); //全局是0，第一个括号是1，第二个是2，以此类推

            //fos.write(trigger.getBytes());
            return trigger;
        }

        return "0";
    }
}

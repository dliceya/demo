package com.ocr.demo.test;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class Json2Excel {
    public Json2Excel() {
    }

    public static void build(OutputStream output, String json, String[] properties) {
        build(output, json, properties, properties);
    }

    public static void build(OutputStream output, String json, String[] properties, String[] columnsNames) {
        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet();
        HSSFRow header = hoja.createRow(0);
        JSONArray array = new JSONArray(json);

        //test PR

        int e;
        for(e = 0; e < columnsNames.length; ++e) {
            String object = columnsNames[e];
            HSSFCell row = header.createCell(e);
            HSSFRichTextString j = new HSSFRichTextString(object);
            row.setCellValue(j);
        }

        for(e = 0; e < array.length(); ++e) {
            JSONObject var16 = array.getJSONObject(e);
            HSSFRow var17 = hoja.createRow(e + 1);

            for(int var18 = 0; var18 < properties.length; ++var18) {
                String string = properties[var18];
                HSSFCell cell = var17.createCell(var18);
                try {
                    HSSFRichTextString text = new HSSFRichTextString(var16.get(string).toString());
                    cell.setCellValue(text);
                } catch (Exception var15) {
                    var15.printStackTrace();
                    HSSFRichTextString text = new HSSFRichTextString(var16.getDouble(string) + "");
                    cell.setCellValue(text);
                }
            }
        }
        try {
            libro.write(output);
        } catch (Exception var15) {
            var15.printStackTrace();
        }
    }
    public static void main(String [] args){
        try {
            FileReader reader = new FileReader(new File("C:/Users/HuaMeiTraining/Desktop/test.json"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line= bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            FileOutputStream xlsStream = new FileOutputStream("C:/Users/HuaMeiTraining/Desktop/document.xls");
            Json2Excel.build(xlsStream, stringBuffer.toString(), new String[]{"column","row","word"});
            xlsStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

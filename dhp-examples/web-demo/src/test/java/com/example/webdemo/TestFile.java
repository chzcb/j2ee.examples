package com.example.webdemo;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestFile {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhangcb/Downloads/kline1m.sql");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
        BufferedReader input = new BufferedReader(inputStreamReader, 65535);
    
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
        Map<String, OutputStream> symbolOS = new HashMap<>();
        Map<String, BlockingQueue<String>> symbolCache = new ConcurrentHashMap<>();
        
        String content = input.readLine();
        while ( content != null) {
            if(content.length()>1000)
                log.info("content: {}", content.substring(0, 1000));
            else
                log.info("content:{}", content);
            if(content.startsWith("INSERT")){
                content = content.substring("INSERT INTO `klines_1m_tmp` VALUES ".length());
                int splitIndex = content.indexOf("),");
                while(splitIndex>0){
                    String line = content.substring(1, splitIndex);
                    String[] arr = line.split(",");
                    String symbol = arr[3].substring(1, arr[3].length()-1);
        
                    //判断写入文件
                    if(!symbolOS.containsKey(symbol)){
                        File wfile = new File("/Users/zhangcb/Downloads/output2/"+symbol+".csv");
                        final FileOutputStream os = new FileOutputStream(wfile);
                        os.write("时间(毫秒),开,高,低,收,交易笔数,k线类型,成交额,成交量,涨幅\n".getBytes());
                        symbolOS.put(symbol, os);
                        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
                        symbolCache.put(symbol, queue);
                        
                        Thread t = new Thread(()->{
                            String cache = "";
                            while(true){
                                try {
                                    String item = queue.poll(100, TimeUnit.MILLISECONDS);
                                    if(item != null){
                                        cache += item;
                                    }
                                    if(cache.length()>65535 || (cache.length()>0 && item == null)){
                                        os.write(cache.getBytes());
                                        cache = "";
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                    }
        
        
                    Date date = format.parse(arr[1].replace(".000", "").replace("'",""));
                    line = date.getTime()+","+arr[4]+","+arr[6]+","+arr[7]+","+arr[5]+",0,1m,"+arr[9]+","+arr[8]+",0\n";
    
                    symbolCache.get(symbol).add(line);
//                    symbolOS.get(symbol).write(line.getBytes());


//                    log.info("line: {}", line);
                    content = content.substring(splitIndex+2);
                    splitIndex = content.indexOf("),");
                }
            }
            content = input.readLine();
        }
    }
}

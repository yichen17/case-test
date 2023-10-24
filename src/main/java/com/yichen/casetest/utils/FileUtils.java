package com.yichen.casetest.utils;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/10/9 07:11
 * @describe 文件操作工具类
 */
@Slf4j
public class FileUtils {

    /**
     * 默认所有文件的编码格式都是utf-8<br/>
     * 同类功能文件合并，例如两个账户文件合并<br/>
     * 1、文件编码格式转换<br/>
     * 2、空行剔除跳过<br/>
     * 3、去除重复的，有差异的放到最后，特殊表示出来<br/>
     * @param filePath1 第一个文件路径
     * @param filePath2 第二个文件路径
     * @return 是否合并成功
     */
    public static boolean mergeFile(String filePath1, String filePath2, String mergeFilePath){
        try {
            doMergeFileBase(filePath1, filePath2, mergeFilePath);
        }
        catch (Exception e){
            log.error("文件合并异常{}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    private static void doMergeFileBase(String filePath1, String filePath2, String mergeFilePath) throws Exception {
        Map<String, String> map1 = readFileItems(checkFileAndGetStream(filePath1));
        Map<String, String> map2 = readFileItems(checkFileAndGetStream(filePath2));
        // 合并结果 一致的放在前面  不一样的放在后面  某一方有的认为一致
        List<Map.Entry<String, String>> sameList = new ArrayList<>(32);
        List<Pair<Map.Entry<String, String>, String>> diffList = new ArrayList<>(32);
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            String val = map2.get(entry.getKey());
            // map1独有
            if (StringUtils.isEmpty(val) || val.equals(entry.getValue())){
                sameList.add(entry);
                map2.remove(entry.getKey());
                continue;
            }
            diffList.add(new Pair<>(entry, val));
            map2.remove(entry.getKey());
        }
        sameList.addAll(map2.entrySet());

        try(FileOutputStream fileOutputStream = new FileOutputStream(mergeFilePath)) {
            sameList.forEach(p -> {
                try {
                    fileOutputStream.write(p.getKey().getBytes("utf8"));
                    fileOutputStream.write(new byte[]{'\n'});
                    fileOutputStream.write(p.getValue().getBytes("utf8"));
                    fileOutputStream.write(new byte[]{'\n', '\n'});
                    fileOutputStream.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            if (!diffList.isEmpty()){
                fileOutputStream.write("================================= diff =================================\n".getBytes());
                diffList.forEach(p -> {
                    try {
                        fileOutputStream.write(p.getKey().getKey().getBytes("utf8"));
                        fileOutputStream.write("\n===> type1\n".getBytes());
                        fileOutputStream.write(p.getKey().getValue().getBytes("utf8"));
                        fileOutputStream.write("===> type2\n".getBytes());
                        fileOutputStream.write(p.getValue().getBytes());
                        fileOutputStream.write(new byte[]{'\n', '\n'});
                        fileOutputStream.flush();
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    private static Map<String, String> readFileItems(FileInputStream fileInputStream) throws Exception {
        Map<String, String> mapItems = new HashMap<>(32);
        if (Objects.isNull(fileInputStream)){
            return mapItems;
        }
        StringBuilder builder = new StringBuilder();
        StringBuilder items = new StringBuilder();
        int val;
        boolean title = true;
        String titleStr = null;
        while ((val = fileInputStream.read()) != -1) {
            if (val == '\n'){
                // 直接起头空行或者数据间隔多个空行
                if (title && builder.length() == 0){
                    continue;
                }
                // title上有数据，保存
                if (title){
                    title = false;
                    builder.append("\n");
                    titleStr = convertEncoding(builder.toString(), "latin1", "utf8").trim();
                    builder = new StringBuilder();
                    continue;
                }
                // 数据间隔空行 保存数据
                if (builder.length() == 0){
                    mapItems.put(titleStr, convertEncoding(items.toString(), "latin1", "utf8"));
                    titleStr = null;
                    items = new StringBuilder();
                    title = true;
                    continue;
                }
                // 正常结果数据
                builder.append("\n");
                items.append(builder);
                builder = new StringBuilder();
                continue;
            }
            builder.append((char) val);
        }
        // 兜底，如果最后一个之后没有换行
        if (StringUtils.isNotEmpty(titleStr)  && (items.length() != 0 || builder.length() != 0)){
            items.append(builder);
            mapItems.put(titleStr, convertEncoding(items.toString(), "latin1", "utf8"));
        }
        fileInputStream.close();
        return mapItems;
    }

    private static FileInputStream checkFileAndGetStream(String filePath) throws FileNotFoundException{
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()){
            return null;
        }
        return new FileInputStream(file);
    }

    /**
     * 行数据比对，空行忽略
     * 一致数据放前面，不一致的数据放后面
     * 怎么确定是变更数据呢，也就是一行中部分数据改变了。。。     =>   数据规范化处理
     *
     * 标准格式的定义：
     *      第一行功能用途项，不可变更
     *      第二行及之后，为具体的数据项，可以变更
     *      强约束，要么不存在，要么行数大于等于2，不应该存在行数为1的游离项
     * 标准合并规则：
     *      以第一项去配置，如果新旧都有且不一致，则进行标记并放到最后；如果都有且一致，则保留其中一项；
     *                          如果只有某一方存在，则放到上边；如果都没有，那不可能触发
     * @param filePath1 第一个文件
     * @param filePath2 第二个文件
     * @param mergeFilePath 合并后的文件
     * @throws Exception 处理中产生的异常
     */
    private static void doMergeFile(String filePath1, String filePath2, String mergeFilePath) throws Exception{

        Set<String> data = new HashSet<>();

        File file = new File(filePath1);
        try(
            FileReader fileReader = new FileReader(file);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, fileReader.getEncoding());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ){
            String line;
            StringBuilder item = new StringBuilder();
            // 循环读取文件数据
            while ((line = bufferedReader.readLine()) != null){
                if (StringUtils.isEmpty(line)){
                    if (item.length() > 0){
                        data.add(item.toString());
                        item = new StringBuilder();
                    }
                    continue;
                }
                item.append(line).append("\r\n");
            }
        }
        catch (Exception e){
            log.error("合并文件异常{}", e.getMessage(), e);
        }

        file = new File(filePath2);
        try(
                FileReader fileReader = new FileReader(file);
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, fileReader.getEncoding());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ){
            String line;
            StringBuilder item = new StringBuilder();
            // 循环读取文件数据
            while ((line = bufferedReader.readLine()) != null){
                if (StringUtils.isEmpty(line)){
                    if (item.length() > 0){
                        data.add(item.toString());
                        item = new StringBuilder();
                    }
                    continue;
                }
                item.append(line).append("\r\n");
            }
        }
        catch (Exception e){
            log.error("合并文件异常{}", e.getMessage(), e);
        }

    }




    /**
     * 编码格式转换
     * @param originStr 原始字符串
     * @param fromEncoding 原始编码格式
     * @param toEncoding 目标编码格式
     * @return 转换后的字符串
     * @throws Exception
     */
    public static String convertEncoding(String originStr, String fromEncoding, String toEncoding) throws Exception{
        return new String(originStr.getBytes(fromEncoding), toEncoding);
    }


    public static void main(String[] args) throws Exception {
        String s = "/opt/homebrew/var/db/redis/a.txt";

//        readFileLine(s);

//        fileWriteByBinary(s);
//        fileWriteByEncoding(s);

//        System.out.println(convertEncoding("狂杀一条街", "gbk", "utf8"));
//        System.out.println(convertEncoding("¿ñÉ±Ò»Ìõ½Ö", "utf8", "gbk"));

//        arraySort();

//        manualReadFile(s);

        doMergeFileBase(s, "/opt/homebrew/var/db/redis/b.txt", "/opt/homebrew/var/db/redis/a.txt");

    }

    private static void manualReadFile(String filePath) throws Exception {
        Map<String, String> map = readFileItems(checkFileAndGetStream(filePath));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.printf("%s%n%s============================================%n", entry.getKey(), entry.getValue());
        }
    }

    private static void arraySort(){
        int[] array = new int[]{2,1,3,4,9,8,7};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void readFileLine(String s) throws Exception{
        File file1 = new File(s);
        FileReader fileReader = new FileReader(file1);
        FileInputStream fileInputStream = new FileInputStream(file1);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, fileReader.getEncoding()));
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        br.close();
        fileInputStream.close();
        fileReader.close();
    }

    private static void fileWriteByBinary(String s) throws Exception{
        File file = new File(s);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] data = "狂杀一条街".getBytes();
        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static void fileWriteByEncoding(String s)throws Exception{
        File file = new File(s);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "gbk");
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
        String content = "狂杀一条街";
        bw.write(content);
        bw.flush();
        bw.close();
        outputStreamWriter.close();
        fos.close();
    }

    private static void fileEncoding(String s) throws Exception{
        FileReader fr = new FileReader(s);
        System.out.println(fr.getEncoding());
        fr.close();
    }



}

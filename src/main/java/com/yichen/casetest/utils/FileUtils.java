package com.yichen.casetest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/10/9 07:11
 * @describe 文件操作工具类
 */
@Slf4j
public class FileUtils {





    /**
     * 替换 {@code searchDir}中所有remark信息，以及空行   用于解决源码反编译处理费代码
     * @param searchDir
     */
    public static void removeRemarkLine(String searchDir){
        File directory = new File(searchDir);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        removeRemarkLine(file.getAbsolutePath());
                    } else if (file.getName().endsWith(".java")) {
                        removeCommentsAndEmptyLines(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public static void removeCommentsAndEmptyLines(String filePath) {
        try {
            List<String> modifiedLines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder sb = new StringBuilder();
            boolean commentStart = false;

            while ((line = reader.readLine()) != null) {
                sb.setLength(0);
                // 每一行逐个字符匹配， 碰到  /* 标记注释开头    碰到  */ 丢弃范围内的东西
                for (int i=0; i<line.length(); i++){
                    if (line.charAt(i) == '/' && i != line.length() - 1 && line.charAt(i+1) == '*'){
                        commentStart = true;
                        i++;
                        continue;
                    }
                    if (commentStart && line.charAt(i) == '/' && i != 0 && line.charAt(i-1) == '*'){
                        commentStart = false;
                        continue;
                    }
                    if (!commentStart){
                        sb.append(line.charAt(i));
                    }
                }
                line = sb.toString();
                if (line.trim().isEmpty()){
                    continue;
                }
//                modifiedLines.add(line.trim());
                modifiedLines.add(line);
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String modifiedLine : modifiedLines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




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

//        doMergeFileBase(s, "/opt/homebrew/var/db/redis/b.txt", "/opt/homebrew/var/db/redis/a.txt");

//        base64EncoderCompare();

//        removeRemarkLine("/Users/banyu/personal/agent-demo/agent/src/main/java");

//        formatCode("/Users/banyu/personal/case-test/test", 4);
//        formatCode("/Users/banyu/personal/agent-demo/agent/src/main/java", 4);

//        fileConvert("~/tmp/origin.txt", null, "~/tmp/target.txt");
        fileConvert("/Users/banyu/tmp/origin.txt", (p) -> {
            if (StringUtils.isBlank(p)) {
                return p;
            }
            String[] items = p.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < items.length; i++) {
                if (i != 0) {
                    sb.append("\n");
                }
                sb.append(items[i]);
            }
            return sb.toString();
        }, "/Users/banyu/tmp/target.txt");
    }

    /**
     * jackson打印byte[]逻辑。  是base64字节流
     * @throws Exception
     */
    private static void jacksonByteArray() throws Exception{
        byte[] bytes = new byte[]{'a', 'b', 'c', 11, 12};
        System.out.println(new String(bytes));
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(bytes));
//        System.out.println(new String(objectMapper.readValue("YWJjCww=", byte[].class)));
        System.out.println(new String(Base64.getDecoder().decode("YWJjCww=")));

//        System.out.println(objectMapper.readValue("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", byte[].class));
//        System.out.println(new String(Base64.getDecoder().decode("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")));
//        System.out.println(new String(Base64.getDecoder().decode("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")));

    }

    /**
     * 直接读取文件字节流进行base64加密
     * 读取文件的字节流后转成string，在转成字节数组后进行base64加密
     */
    private static void base64EncoderCompare(){
        byte[] bytes = readByteArrayFromFile("/tmp/20231129173538_21660C8C0E1149D904EC93C19D61A532_419383");
        System.out.println(new String(bytes));
        com.yichen.casetest.utils.StringUtils.divisionLine();
        String rr  = new String(Base64.getEncoder().encode(bytes));
        System.out.println(rr);
        com.yichen.casetest.utils.StringUtils.divisionLine("直接用字节流加密");
        rr = new String(bytes);
        rr = new String(Base64.getEncoder().encode(rr.getBytes()));
        System.out.println(rr);
        com.yichen.casetest.utils.StringUtils.divisionLine("byte和string转换一次后字节流加密");
    }

    public static String readFile(String filePath) throws Exception{
        byte[] bytes = readByteArrayFromFile(filePath);
        if (Objects.isNull(bytes)){
            return null;
        }
        return new String(bytes);
    }

    public static byte[] readByteArrayFromFile(String filePath){
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            return bytes;
        } catch (IOException e) {
            log.error("readByteArrayFromFile异常{}", e.getMessage(), e);
            return null;
        }
    }

    public static void readFileAndConvert(String filePath, String fromEncoding, String toEncoding) throws Exception{
        String fileData = readFile(filePath);
        String result = convertEncoding(fileData, fromEncoding, toEncoding);
        log.info("\n转换前{}\n\n转换后{}", fileData, result);
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

    public static void writeFile(String filePath, String data) throws Exception {
//        File file = new File(filePath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.flush();
        fileOutputStream.close();
    }


    /**
     * 疑惑，为啥不能用~指定用户目录？？？
     * @param originPath
     * @param convertFunction
     * @param targetPath
     * @throws Exception
     */
    public static void fileConvert(String originPath, Function<String, String> convertFunction, String targetPath) throws Exception{
        String originData = readFile(originPath);
        String targetData = convertFunction.apply(originData);
        writeFile(targetPath, targetData);

//        String[] items = originData.split(",");
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < items.length; i++) {
//            if (i != 0) {
//                sb.append("\n");
//            }
//            sb.append(items[i]);
//        }
//        writeFile("/Users/banyu/tmp/20240514.txt", sb.toString());

    }



}

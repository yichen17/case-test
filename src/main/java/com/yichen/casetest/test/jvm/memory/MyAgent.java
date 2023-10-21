

import java.lang.instrument.Instrumentation;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/21 11:57
 * @describe  参考文档：https://www.javamex.com/tutorials/memory/instrumentation.shtml
 *          直接在当前目录 memory执行 run.sh脚本
 */
public class MyAgent {

    public static void premain(String args, Instrumentation inst){
//        Person person = new Person();
//        long size = inst.getObjectSize(person);

        String s = "";
        long size = inst.getObjectSize(s);
        System.out.printf("Bytes used by object: %s%n", size);
    }

}

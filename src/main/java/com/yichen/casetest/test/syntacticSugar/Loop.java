package com.yichen.casetest.test.syntacticSugar;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/6/5 9:54
 * @describe
 */
@Slf4j
public class Loop {

    public static void main(String[] args) {
//        testLoopContinue();
        StringUtils.divisionLine();
        testLoopBreak();
    }


    /**
     * continue xxx 表示回到xxx，重新执行
     * 参考链接  =>  https://www.cnblogs.com/RunningSnails/p/13301549.html
     */
    private static void testLoopContinue(){
        retry:
        for(;;){
            for(int i=0; i<10; i++){
                if (i == 3){
                    log.info("retry");
                    continue retry;
                }
                log.info("==> {}", i);
            }
            log.info("==> inner end");
            break;
        }
        log.info("==> end");
    }

    /**
     * break xxx 表示 xxx层循环跳出
     */
    private static void testLoopBreak(){
        retry:
        for(;;){
            for(int i=0; i<10; i++){
                if (i == 3){
                    log.info("retry");
                    break retry;
                }
                log.info("==> {}", i);
            }
            log.info("==> inner end");
            break;
        }
        log.info("==> end");
    }

}

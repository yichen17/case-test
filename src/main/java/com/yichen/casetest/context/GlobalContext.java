package com.yichen.casetest.context;

import lombok.Data;

/**
 * @author Qiuxinchao
 * @date 2023/6/20 13:31
 * @describe
 */
@Data
public class GlobalContext {

    private static final ThreadLocal<GlobalContext> GLOBAL = ThreadLocal.withInitial(GlobalContext::new);

    public static GlobalContext current(){
        return GLOBAL.get();
    }

    public static void release(){
        GLOBAL.remove();
    }


}

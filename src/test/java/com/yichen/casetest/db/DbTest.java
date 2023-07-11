package com.yichen.casetest.db;

import com.yichen.casetest.service.db.DbTestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

/**
 * @author Qiuxinchao
 * @date 2023/7/11 18:06
 * @describe
 */
@SpringBootTest
public class DbTest {

    @SpyBean
    private DbTestService dbTestService;

    @Test
    public void transactionTest(){
        dbTestService.consistentFail("11");
    }

    @Test
    public void transactionTestTrans(){
        dbTestService.consistentFailTrans("11");
    }


}

package com.yichen.casetest.config.hasor;

import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Qiuxinchao
 * @date 2023/4/10 9:49
 * @describe  学习文章 => https://mp.weixin.qq.com/s/rO-jP724oNpoWpK_r4m2wA
 *    后台配置地址  http://localhost:8088/interface-ui/#/
 *    两个表配置  interface_info.sql  interface_release.sql     hasor-dataway-4.1.3-fix20200414
 */
@DimModule
@Component
public class BindModule implements SpringModule {

    @Autowired
    private DataSource dataSource = null;

    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        // .DataSource form Spring boot into Hasor
        apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));
    }
}

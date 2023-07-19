package com.yichen.casetest.test.pointcuts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @date 2023/7/19 15:07
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CA
class TT {

    private String ntr;

    TT fork(){
        return new TT(getNtr());
    }

}

package com.yichen.casetest.problem.bot;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/26 9:48
 * @describe
 */
public class RequestData<T> extends RequestCommon<T> {
    private static final long serialVersionUID = 3833911584288897252L;

    private Long customerId;

    private Long mobile;

    private Long certId;

    public RequestData() {
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMobile() {
        return this.mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getCertId() {
        return this.certId;
    }

    public void setCertId(Long certId) {
        this.certId = certId;
    }
}

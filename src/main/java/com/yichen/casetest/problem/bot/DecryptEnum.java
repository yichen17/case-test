package com.yichen.casetest.problem.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/14 11:40
 * @describe 数据解密枚举
 */
@AllArgsConstructor
@Getter
public enum DecryptEnum {
    /**
     * h5  rsa 加密  encryptedData
     */
    H5("h5","encryptedInfoForH5,encryptedData", "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJaW+tvFU00Ww/MHTVnponesDTqEOLGTLB21TF/EBWmYnHz4l3gcr+mA4Ul0JQ9C0V+SH6tWmlA2ZbM2+fthRDdxIgzeX+Pbr2KLrunyIH4sJ5uQZYhXZ5+7ntBAtJbrd4jTdXwBUyZO5Id5vDsfdMytRFtThEMMydncNX3d4I4hAgMBAAECgYACL/9E4gDrxcDi7uA4xiQOCPnIN9N7AOBLLi8F1RsfBZCAYDwTCpejlLOxFXbUWewgr0a2fnOPZrrjd5D6Dden0NAWiVlBLJ4byw5zWGEpQabOFg3ddMVSVFX+3fDmk4RObnNrH9nScwHd4dibbfmoT5qQRk65WLxRoCDF3aiCYQJBAMmN9PF/b1Y9muEabLXBEP3V/4z/bd9/5WPpK7JUL7IQie+MM10fu35kcK6YOnPgo0c9OUkN8WBaUFxlt1MooasCQQC/RKv51yvzWgFv5IQBE7FD0Jq05BV1Acj0qgZ0n7nve/v/YVSrecpSkQ9XYPhyRy1cplvkAj51IXTxLvhxVxtjAkB+1wotrG/Jiw1b2gBNxUlHJRQkjF59x4P5gzSPjjFR0tyrVsTANwcMPHM5PO2UHOtEGsBhPBgJ9ewaqZxcBfbvAkBTpMBgfgymW1INkK15mxcGRQ+i06veg21SMZipH8C8TkghonrYkmY8PVusJqf/scjQn5/H0oNlzb/KSXQ0fJdpAkB2HJZ4/H8RxCyW2fuY7Jp89sEFSCZbL/qzPWxImC0mnuVPD3E3KfhABCOLvFjC5gkbeNaAit3+EmNoJMjFN4fI"),
    /**
     * android  aes加密
     */
    ANDROID("android", "encryptedInfo", "9b6f011102e72b8a420b9246a6a96bee"),
    /**
     * ios  aes加密
     */
    IOS("ios", "encryptedInfo", "9b6f011102e72b8a420b9246a6a96bee");

    /**
     * 设备类型
     */
    private final String deviceType;
    /**
     * 加密字段
     */
    private final String encryptField;
    /**
     * 解密密钥
     */
    private final String key;


    public static String getEncryptField(String deviceType){
        for (DecryptEnum item : DecryptEnum.values()){
            if (item.getDeviceType().equals(deviceType)){
                return item.getEncryptField();
            }
        }
        return null;
    }

    public static String getDecryptKey(String deviceType){
        for (DecryptEnum item : DecryptEnum.values()){
            if (item.getDeviceType().equals(deviceType)){
                return item.getKey();
            }
        }
        return null;
    }





}

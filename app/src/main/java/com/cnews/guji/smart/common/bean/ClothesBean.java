package com.cnews.guji.smart.common.bean;

import java.util.List;

/**
 * @package: GoodsBean
 * @author： JSYL-DCL
 * @describe： 测试实体
 * @email： 11442865
 * https://suggest.taobao.com/sug?code=utf-8&q=裤子
 * https://ditu.amap.com/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712
 */
public class ClothesBean {
    private List<List<String>> result;
    public void setResult(List<List<String>> result) {
        this.result = result;
    }
    public List<List<String>> getResult() {
        return result;
    }
}

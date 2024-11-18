package org.example.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @Title: IPBelongPlaceResult.java
 * @Package com.itzixi.utils
 * @Description: IP归属地自定义响应数据结构
 * 				200：表示成功
 * 				400：参数错误
 * 				500：系统维护，请稍候再试
 * 				501：官方数据源维护，请稍候再试
 * 				604：接口停用
 * 				701: IP地址信息不存在
 * 			    702: {ip}为内网IP地址
 * 			    999: 其他，以实际返回为准
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IPBelongPlaceResult {

    private Integer code;               // 响应业务状态
    private String msg;                 // 响应消息
    private boolean charge;             // 响应成功失败标记
    private String taskNo;              // 本次唯一请求号
    private PlaceData data;             // 归属地对象映射实体类

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class PlaceData {
        private String country;         // 国家
        private String country_id;      // 国家编号
        private String area;            // 地域
        private String region;          // 省份
        private String region_id;       // 省份编号
        private String city;            // 城市
        private String city_id;         // 城市编号
        private String ip;
        private String long_ip;
        private String isp;             // 运营商
    }
}

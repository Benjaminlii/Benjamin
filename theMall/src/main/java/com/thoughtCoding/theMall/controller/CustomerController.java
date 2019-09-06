package com.thoughtCoding.theMall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtCoding.theMall.model.Customer;
import com.thoughtCoding.theMall.service.CustomerService;
import com.thoughtCoding.theMall.service.RecordService;
import com.thoughtCoding.theMall.utils.ImageUtil;
import com.thoughtCoding.theMall.utils.MQTTUtil;
import com.thoughtCoding.theMall.vo.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private MQTTUtil mqttUtil;

    private Logger logger = LogManager.getLogger(CustomerController.class);

    @RequestMapping(value = "/addCustomer")
    @ResponseBody
    public Map<String, String> addCustomer(HttpServletRequest request,
                                           @RequestParam(value = "customerName", required = true) String customerName,
                                           @RequestParam(value = "customerSex", required = true) Byte customerSex,
                                           @RequestParam(value = "customerAge", required = true) Byte customerAge,
                                           @RequestParam(value = "phone", required = true) String phone,
                                           @RequestParam(value = "image", required = true) String imageString) {
        Map<String, String> retMap = new HashMap<>();

        ImageUtil.stringToImage(imageString, phone + ".jpg");
        File image = new File(Constants.IMAGE_PATH + phone + ".jpg");

        logger.info("创建文件:" + image.getAbsolutePath() + ", 大小:" + image.length() + "bytes.");

        Boolean isOk = customerService.AddCustomer(customerName, customerSex, customerAge, phone, image);
        if (isOk) {
            retMap.put(Constants.ERROR_MESSAGE, Constants.OK);
        } else {
            retMap.put(Constants.ERROR_MESSAGE, "服务器繁忙,请稍后再试.");
        }

//        image.delete();

        return retMap;
    }

    @RequestMapping(value = "/captureFace")
    @ResponseBody
    public Map<String, Object> captureFace(HttpServletRequest request,
                                           @RequestParam(value = "msg", required = true) String msg//参数
    ) {
        Map<String, String[]> map = request.getParameterMap();

        JSONObject jsonObject = JSONObject.parseObject(msg);

        String action = jsonObject.getString("action");//固定值,visitor
        String customerName = jsonObject.getString("vip_name");//捕捉到的顾客的姓名
        Integer customerId = jsonObject.getInteger("vip_num");//顾客id
        String face_url = jsonObject.getString("face_url");//人脸图片url
        String frame_url = jsonObject.getString("frame_url");//帧图url,可能为空串
        Long timestamp = jsonObject.getLong("timestamp");//捕捉到脸的时间戳

        logger.info(">>>>>>>>>>>>(captureFace) customerName = "
                + customerName
                + ", customerId = "
                + customerId
                + "face_url = "
                + face_url
                + "frame_url = "
                + frame_url
                + "timestamp = "
                + timestamp
                + "msg = "
                + msg);

        Map<String, Object> retMap = new HashMap<>();
        Map<String, Object> goToAndroid = new HashMap<>();

        //接收到摄像头信息,在数据库中查询用户信息,姓名一致则安卓推送
        //捕捉到customer,service中对其进行判断,并更新时间
        Customer customer = customerService.captureCustomer(customerId, customerName);


        if (customer != null) {
            //反馈的顾客信息与数据库中一致,推送至安卓端

            //查询该顾客历史购买记录中最喜欢的商品
            String favorite = recordService.queryFavoriteByCustomerId(customerId);

            //构造发送的信息,JSON格式
            goToAndroid.put("customer", customer);
            goToAndroid.put("face_url", face_url);
            goToAndroid.put("frame_url", frame_url);
            goToAndroid.put("timestamp", timestamp);
            goToAndroid.put("favorite", favorite);
            String json = JSON.toJSONString(goToAndroid);

            //推送至所有订阅了theMall/clerk消息的安卓端(店员端全部都要推送)
            mqttUtil.send("theMall/clerk", json);

            //如果是黑名单,也推送至店长端
            if (1 == customer.getCustomerIsBlack()) {
                mqttUtil.send("theMall/manager", json);
            }
        }

        retMap.put("code", 1000);

        return retMap;
    }
}

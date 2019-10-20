package com.thoughtCoding.alarm.service;

import com.alibaba.fastjson.JSONObject;
import com.thoughtCoding.alarm.mapper.ContactMapper;
import com.thoughtCoding.alarm.model.Constant;
import com.thoughtCoding.alarm.model.Contact;
import com.thoughtCoding.alarm.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ContactServiceImpl
 * Package:com.thoughtCoding.alarm.service
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-17 下午5:28
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private SMSUtil smsUtil;

    @Override
    public boolean addContact(Short uid, String callPhone) {
        Contact contact = new Contact();
        contact.setUserId(uid);
        contact.setCallPhone(callPhone);
        return contactMapper.insertSelective(contact) > 0;
    }

    @Override
    public List<Contact> findAllContactsByUid(Short uid) {

        return contactMapper.selectByUid(uid);
    }

    @Override
    public boolean deleteContact(Short uid, String callPhone) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("uid", uid);
        paraMap.put("callPhone", callPhone);

        return contactMapper.deleteByUidAndCallPhone(paraMap) > 0;
    }

    @Override
    public int alarmAllCallPhone(String phoneOfUser, String address, List<Contact> contactList) {
        int numOfSMS = 0;
        String callSMSMobile = null;
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("content", String.format(Constant.ALARM_SSM, phoneOfUser, address));
        String result = null;
        JSONObject resultJson = null;

        for (Contact contact : contactList) {
            // 每循环一次,发送一个短信
            callSMSMobile = contact.getCallPhone();
            paraMap.put("mobile", callSMSMobile);
            try {
                result = smsUtil.sms(Constant.SMS_URL, paraMap);
            } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            resultJson = JSONObject.parseObject(result);
            if (resultJson.getInteger("status") == 1) {
                // 调用成功
                numOfSMS++;
            }
        }

        return numOfSMS;
    }
}

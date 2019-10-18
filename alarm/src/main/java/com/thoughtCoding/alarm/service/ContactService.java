package com.thoughtCoding.alarm.service;

import com.thoughtCoding.alarm.model.Contact;

import java.util.List;

/**
 * ClassName:ContactService
 * Package:com.thoughtCoding.alarm.service
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-17 下午5:27
 */
public interface ContactService {

    /**
     * 添加一条联系人关系
     * @param uid 添加的用户
     * @param callPhone 该用户添加的联系人手机号
     * @return 添加是否成功
     */
    boolean addContact(Short uid, String callPhone);

    /**
     * 找到对应用户所添加的所有紧急联系人
     * @param uid 用户对应的uid
     * @return 紧急联系人列表
     */
    List<Contact> findAllContactsByUid(Short uid);

    /**
     * 删除一条紧急联系人
     * @param uid 当前用户
     * @param callPhone 要删除的紧急联系人手机号
     * @return 是否删除成功
     */
    boolean deleteContact(Short uid, String callPhone);

    /**
     * 向列表中的紧急联系人发信息
     * @param phoneOfUser 报警端用户手机号码
     * @param address
     * @param contactList 该用户的紧急联系人列表
     * @return 发送的信息条数
     */
    int alarmAllCallPhone(String phoneOfUser, String address, List<Contact> contactList);
}

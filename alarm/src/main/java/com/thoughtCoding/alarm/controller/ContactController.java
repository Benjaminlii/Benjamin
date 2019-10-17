package com.thoughtCoding.alarm.controller;

import com.thoughtCoding.alarm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ClassName:ContactController
 * Package:com.thoughtCoding.alarm.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-17 下午8:10
 */
@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
}

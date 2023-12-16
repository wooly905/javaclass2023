package com.evproject.evproject.controllers;

import com.evproject.evproject.models.CustomerSessionInfo;
import com.evproject.evproject.services.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChargeController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/charge1")
    public String charge1(HttpSession session, Model model) {
        CustomerSessionInfo info = (CustomerSessionInfo) session.getAttribute("info");

        Integer point = memberService.getPoint(info.getCustomerId());

        if (point >= 0) {
            model.addAttribute("point", point);
        } else {
            model.addAttribute("chargingId", info.getChargingId());
            model.addAttribute("error", "未找到客戶");
            return "error"; // 返回 error.html 页面并显示错误信息
        }

        model.addAttribute("customerName", info.getChargingId());
        model.addAttribute("chargingId", info.getChargingId());

        return "charge1";
    }

    @GetMapping("/charge2")
    public String charge2(HttpSession session, Model model) {
        CustomerSessionInfo info = (CustomerSessionInfo) session.getAttribute("info");

        Integer point = memberService.getPoint(info.getCustomerId());

        if (point >= 0) {
            model.addAttribute("point", point);
        } else {
            model.addAttribute("chargingId", info.getChargingId());
            model.addAttribute("error", "未找到客戶");
            return "error"; // 返回 error.html 页面并显示错误信息
        }

        model.addAttribute("customerName", info.getCustomerName());
        model.addAttribute("chargingId", info.getChargingId());

        return "charge2"; // 返回 charge2.html 页面
    }
}

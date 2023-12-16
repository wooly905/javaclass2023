package com.evproject.evproject.controllers;

import com.evproject.evproject.entities.Member;
import com.evproject.evproject.models.CustomerSessionInfo;
import com.evproject.evproject.repositories.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/fake-login")
    public String yourPage(Model model, HttpSession session) {
        session.invalidate();

        // 生成充電椿編號清單（假設從1到10）
        List<Integer> chargingIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Member> members = memberRepository.findAll();

        // 添加到Model中
        model.addAttribute("chargingIds", chargingIds);
        model.addAttribute("members", members);

        return "fakelogin.html";  // 返回Thymeleaf模板的名稱
    }

    @PostMapping("/processLogin")
    public String processLogin(@RequestParam("chargingId") String chargingId,
                               @RequestParam("customerId") Long customerId,
                               Model model,
                               HttpSession session) {

        Optional<Member> member = memberRepository.findById(customerId);

        if (member.isPresent()) {
            model.addAttribute("customerName", member.get().getCustomerName());
        } else {
            model.addAttribute("chargingId", chargingId);
            model.addAttribute("error", "未找到客戶");
            return "error"; // 返回 error.html 页面并显示错误信息
        }

        // 將選擇的充電椿編號和客戶名字添加到Model中
        model.addAttribute("chargingId", chargingId);
        model.addAttribute("customerId", customerId);

        CustomerSessionInfo info = new CustomerSessionInfo(member.get().getCustomerName(),
                customerId,
                chargingId);

        session.setAttribute("info", info);

        // 導向到功能主頁
        return "mainPage";
    }
}

package com.evproject.evproject.controllers;

import com.evproject.evproject.entities.Member;
import com.evproject.evproject.models.CustomerSessionInfo;
import com.evproject.evproject.repositories.CreditCardDiscountRepository;
import com.evproject.evproject.repositories.MemberRepository;
import com.evproject.evproject.services.MemberService;
import com.evproject.evproject.services.PointService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private com.evproject.evproject.services.PaymentService paymentService;

    @Autowired
    private CreditCardDiscountRepository creditCardDiscountRepository;

    @Autowired
    private PointService pointService;

    @GetMapping("/payment1")
    public String payment1(HttpSession session, Model model) {
        CustomerSessionInfo info = (CustomerSessionInfo) session.getAttribute("info");

        model.addAttribute("customerName", info.getCustomerName());
        model.addAttribute("chargingId", info.getChargingId());

        Integer point = memberService.getPoint(info.getCustomerId());

        if (point >= 0) {
            model.addAttribute("point", point);
        } else {
            model.addAttribute("chargingId", info.getChargingId());
            model.addAttribute("error", "未找到客戶");
            return "error"; // 返回 error.html 页面并显示错误信息
        }

        // 假設充電充了 100W.
        session.setAttribute("chargeWatt", 100);

        return "payment1";
    }

    @GetMapping("/payment2")
    public String payment2(HttpSession session, Model model) {
        CustomerSessionInfo info = (CustomerSessionInfo) session.getAttribute("info");

        model.addAttribute("customerName", info.getCustomerName());
        model.addAttribute("chargingId", info.getChargingId());

        Integer point = memberService.getPoint(info.getCustomerId());

        if (point >= 0) {
            model.addAttribute("point", point);
        } else {
            model.addAttribute("chargingId", info.getChargingId());
            model.addAttribute("error", "未找到客戶");
            return "error"; // 返回 error.html 页面并显示错误信息
        }

        // 假設充電充了 1000W.
        session.setAttribute("chargeWatt", 1000);

        return "payment2";
    }

    @GetMapping("/payment3") // 路由為 /payment3，使用 GET 方法路由到 payment3.html 页面。
    public String payment3(HttpSession session, Model model) {
        CustomerSessionInfo info = (CustomerSessionInfo) session.getAttribute("info");
        Integer chargeWatt = (Integer) session.getAttribute("chargeWatt");

        model.addAttribute("customerName", info.getCustomerName());
        model.addAttribute("chargingId", info.getChargingId());
        model.addAttribute("chargeWatt", chargeWatt); // 显示充電容量

        Integer point = memberService.getPoint(info.getCustomerId());

        if (point >= 0) {
            model.addAttribute("point", point);
        } else {
            model.addAttribute("chargingId", info.getChargingId());
            model.addAttribute("error", "未找到客戶");
            return "error"; // 返回 error.html 页面并显示错误信息
        }

        // get amount
        int amount = paymentService.getAmount(chargeWatt);
        model.addAttribute("amount", amount);
        session.setAttribute("amount", amount);

        List<com.evproject.evproject.entities.CreditCardDiscount> creditCardDiscounts = creditCardDiscountRepository.findAll();
        model.addAttribute("creditCardDiscounts", creditCardDiscounts); // 显示信用卡折扣

        return "payment3"; // 返回 payment3.html 页面并显示充電容量和积分信息。
    }

    @GetMapping("/payment4") // 路由為 /payment4，使用 GET 方法路由到 payment4.html 页面。
    public String payment4(HttpSession session, Model model) {
        CustomerSessionInfo info = (CustomerSessionInfo) session.getAttribute("info");

        model.addAttribute("customerName", info.getCustomerName());
        model.addAttribute("chargingId", info.getChargingId());

        Integer point = memberService.getPoint(info.getCustomerId());

        if (point >= 0) {
            model.addAttribute("point", point);
        } else {
            model.addAttribute("chargingId", info.getChargingId());
            model.addAttribute("error", "未找到客戶");
            return "error"; // 返回 error.html 页面并显示错误信息
        }

        Integer amount = (Integer) session.getAttribute("amount");
        model.addAttribute("paidAmount", amount);

        int newlyAddedPoint = pointService.convertPoint(amount);
        model.addAttribute("newPoint", newlyAddedPoint);

        int totalPoint = point + newlyAddedPoint;
        model.addAttribute("latestPoint", totalPoint);

        memberService.updatePoint(info.getCustomerId(), totalPoint);

        return "payment4"; // 返回 payment4.html 页面并显示充電容量和积分信息。
    }

    @GetMapping("/payment5")
    public String payment5(HttpSession session, Model model) {
        CustomerSessionInfo info = (CustomerSessionInfo) session.getAttribute("info");

        model.addAttribute("customerName", info.getCustomerName());
        model.addAttribute("chargingId", info.getChargingId());

        return "payment5"; // 返回 payment5.html 页面并显示充電容量和积分信息。
    }
}

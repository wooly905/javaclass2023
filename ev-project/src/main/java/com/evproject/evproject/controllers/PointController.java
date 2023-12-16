package com.evproject.evproject.controllers;

import com.evproject.evproject.entities.Member;
import com.evproject.evproject.models.CustomerSessionInfo;
import com.evproject.evproject.repositories.MemberRepository;
import com.evproject.evproject.services.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class PointController {

    @Autowired
    private MemberService  memberService;

    @GetMapping("/checkpoint")
    public String checkPoint(HttpSession session, Model model)
    {
        CustomerSessionInfo info =  (CustomerSessionInfo) session.getAttribute("info");

        model.addAttribute("customerName", info.getCustomerName());
        model.addAttribute("customerId", info.getCustomerId());
        model.addAttribute("chargingId", info.getChargingId());

        Integer point = memberService.getPoint(info.getCustomerId());

        if (point >= 0) {
            model.addAttribute("point", point);
        }

        return "checkPoint";
    }
}

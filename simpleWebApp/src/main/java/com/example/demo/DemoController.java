package com.example.demo;

import com.example.demo.config.CurrencyServiceConfiguration;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private CurrencyServiceConfiguration currencyConfig;

    @RequestMapping("currencyurl")
    @ResponseBody
    public String GetCurrencyServiceUrl() {
        return currencyConfig.getUrl();
    }

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "This is a hello string";
    }

    @RequestMapping("gotohello")
    public RedirectView goToHello() {
        return new RedirectView("hello");
    }

    @RequestMapping(value = "gotohello2", method = RequestMethod.GET)
    public ModelAndView goToHello2() {
        return new ModelAndView("hello");
    }

    @RequestMapping(value = "gotohello3", method = RequestMethod.GET)
    public String goToHello3() {
        return "redirect:hello";
    }

    @GetMapping("showcustomer")
    public String showCustomer(Model model) {
        Customer customer = new Customer(1, "John", "john@test.com");
        model.addAttribute("customer", customer);
        return "showCustomer";
    }

    @GetMapping("ifelseswitch")
    public String ifelseswitch(Model model) {

        model.addAttribute("value", "2");
        model.addAttribute("role", "admin");

        return "ifelseswitch";
    }

    @GetMapping("showcustomers")
    public String showCustomers(Model model) {
        Customer customer = new Customer(1, "John", "john@test.com");
        Customer customer2 = new Customer(2, "Jack", "jack@test.com");
        Customer customer3 = new Customer(3, "Jake", "jake@test.com");

        List<Customer> customers = Arrays.asList(customer, customer2, customer3);
        model.addAttribute("customers", customers);
        return "showCustomers";
    }

    // http://localhost:8080/showquery?name=bruce
    @GetMapping("showquery")
    public String showQuery(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "showQuery";
    }

    // http://localhost:8080/showquery2?name=bruce&name2=bruce2
    @GetMapping("showquery2")
    public String showQuery(@RequestParam String name2, @RequestParam String name, Model model) {
        model.addAttribute("value", name);
        model.addAttribute("value2", name2);
        logger.info("name = " + name);
        logger.info("name2 = " + name2);
        return "showQuery";
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("showlogger")
    public String showLogger(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        logger.info("name = " + name);
        return "showLogger";
    }

}

package com.example.demo.controller;

import com.example.demo.config.CurrencyServiceConfiguration;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
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
@SessionAttributes("name")
public class DemoController {

    @Autowired
    private CurrencyServiceConfiguration currencyConfig;

    // http://localhost:8080/currencyurl
    @RequestMapping("currencyurl")
    @ResponseBody
    public String GetCurrencyServiceUrl() {
        return currencyConfig.getUrl();
    }

    // http://localhost:8080/hello
    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "This is a hello string";
    }

    // http://localhost:8080/hello2
    @RequestMapping("hello2")
    @ResponseBody
    public String hello2() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<h1>This is a hello string</h1>");
        sb.append("<br>");
        sb.append("<h2>Hello 2</h2>");
        sb.append("<br>");
        sb.append("<h3>Hello 3</h3>");
        sb.append("<br>");
        sb.append("<h4>Hello 4</h4>");
        sb.append("<br>");

        sb.append("<table border=1>");
        sb.append("<tr><td>1</td><td>2</td></tr>");
        sb.append("<tr><td>3</td><td>4</td></tr>");
        sb.append("</table>");

        sb.append("</html>");

        return sb.toString();
    }

    // http://localhost:8080/gotohello
    @RequestMapping("gotohello")
    public RedirectView goToHello() {
        return new RedirectView("hello2");
    }

    // http://localhost:8080/gotohello2
    @RequestMapping(value = "gotohello2", method = RequestMethod.GET)
    public ModelAndView goToHello2() {
        return new ModelAndView("hello");
    }

    @RequestMapping(value = "gotohello2", method = RequestMethod.POST)
    public ModelAndView goToHello21() {
        return new ModelAndView("hello");
    }

    // http://localhost:8080/gotohello3
    @RequestMapping(value = "gotohello3", method = RequestMethod.GET)
    public String goToHello3() {
        return "redirect:hello";
    }

    // http://localhost:8080/showcustomer
    @GetMapping("showcustomer")
    public String showCustomer(Model model) {
        Customer customer = new Customer(1, "John 2", "john2@test.com");
        model.addAttribute("customer", customer);
        return "showCustomer";
    }

    // http://localhost:8080/ifelseswitch
    @GetMapping("ifelseswitch")
    public String ifelseswitch(Model model) {

        model.addAttribute("value", "2");
        model.addAttribute("role", "admin");

        return "ifelseswitch";
    }

    // http://localhost:8080/showcustomers
    @GetMapping("showcustomers")
    public String showCustomers(Model model) {
        Customer customer = new Customer(1, "John", "john@test.com");
        Customer customer2 = new Customer(2, "Jack", "jack@test.com");
        Customer customer3 = new Customer(3, "Jake", "jake@test.com");
        Customer customer4 = new Customer(4, "Jill", "jill@test.com");

        List<Customer> customers = Arrays.asList(customer, customer2, customer3, customer4);
        model.addAttribute("customers", customers);
        return "showCustomers";
    }

    @GetMapping("mathadd")
    public String MathAdd(@RequestParam Integer a, @RequestParam Integer bTest, Model model) {
//        int a1 = Integer.parseInt(a);
//        int b1 = Integer.parseInt(bTest);
        int c = a + bTest;
        model.addAttribute("value", c);
        return "showQuery";
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

    // http://localhost:8080/showlogger?name=name1
    @GetMapping("showlogger")
    public String showLogger(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        logger.info("name = " + name);
        logger.debug("name = " + name);
        logger.error("name = " + name);
        logger.warn("name = " + name);
        logger.trace("name = " + name);
        return "showLogger";
    }

    // form post example
    @GetMapping("showpost")
    public String showPostGet()
    {
        return "showPost";
    }

    @PostMapping("showpost")
    public String showPost(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "showPost";
    }

    // http://localhost:8080/orderform
    @GetMapping("orderform")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping("orderform")
    public String orderForm2(@ModelAttribute Order order, Model model) {
        model.addAttribute("order", order);
        return "showOrder";
    }

}

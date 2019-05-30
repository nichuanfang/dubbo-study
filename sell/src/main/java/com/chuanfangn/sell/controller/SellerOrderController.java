package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 卖家controller
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-23 15:02
 * @version:
 **/
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public String findList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {
        Page<OrderDTO> orderDTOPage = orderService.findAll(page-1, size);
        System.out.println(orderDTOPage.getTotalPages());
        model.addAttribute("orderDtoPage",orderDTOPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        return "seller";
    }
}

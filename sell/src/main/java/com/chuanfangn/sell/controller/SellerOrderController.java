package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.dto.PageDto;
import com.chuanfangn.sell.service.OrderService;
import com.chuanfangn.sell.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    PageService pageService;

    @RequestMapping("/list")
    public String findList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "5") Integer size, Model model) {
        Page<OrderDTO> orderDTOPage = orderService.findAll(page-1, size);
        PageDto pageDto = pageService.getPageDto(page, orderDTOPage.getTotalPages());
        System.out.println(orderDTOPage.getTotalPages());
        model.addAttribute("orderDtoPage",orderDTOPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        model.addAttribute("pageDto",pageDto);
        return "sell/seller";
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String findAllOrders(@RequestParam("orderId")String orderId,Model model,@RequestParam("currentPage")Integer currentPage) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("orderDto",orderDTO);
        return "order/order";
    }

    /**
     * 方法功能: 完结订单
     * @param orderid
     * @author f18326186224@gmail.com
     * @creatDate  2019/6/4 18:48
     * @return java.lang.String
     */
    @RequestMapping("/finish")
    public String finish(@RequestParam("orderId")String orderid,Model model,@RequestParam("currentPage")Integer currentPage) {
        model.addAttribute("url","http://127.0.0.1/sell/seller/order/list?page="+currentPage);
        try {
            orderService.finish(orderService.findOne(orderid));
        } catch (Exception e) {
            model.addAttribute("message","[完结订单]失败");
            return "commons/fail";
        }
        model.addAttribute("message","[完结订单]成功");
        return "commons/success";
    }

    /**
     * 方法功能:  取消订单
     * @param
     * @author f18326186224@gmail.com
     * @creatDate  2019/6/4 18:49
     * @return java.lang.String
     */
    @RequestMapping("/cancel")
    public String cancel(@RequestParam("orderId")String orderId,Model model,@RequestParam("currentPage")Integer currentPage) {
        model.addAttribute("url","http://127.0.0.1/sell/seller/order/list?page="+currentPage);
        try {
            orderService.cancel(orderId);
        } catch (Exception e) {
            model.addAttribute("message","[取消订单]失败");
            e.printStackTrace();
            return "commons/fail";
        }
        model.addAttribute("message","[取消订单]成功");
        return "commons/success";
    }
}

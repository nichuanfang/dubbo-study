package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.converter.CartDtoList2OrderDetailListConverter;
import com.chuanfangn.sell.dto.CartDTO;
import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.entity.OrderDetail;
import com.chuanfangn.sell.enums.ResultEnums;
import com.chuanfangn.sell.exception.ProductException;
import com.chuanfangn.sell.form.OrderForm;
import com.chuanfangn.sell.service.OrderService;
import com.chuanfangn.sell.utils.VoUtil;
import com.chuanfangn.sell.vo.OrderVo;
import com.chuanfangn.sell.vo.ResultVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-30 14:43
 * @version:
 **/
@Controller
@Slf4j
@RequestMapping("/buyer/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @ResponseBody
    public OrderDTO createOrder(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ProductException(ResultEnums.ORDER_UPDATE_FAIL.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        Gson gson = new Gson();
        List<CartDTO> cartDTOList = gson.fromJson(orderForm.getItems(),new TypeToken<List<CartDTO>>(){}.getType());
        List<OrderDetail> orderDetails = CartDtoList2OrderDetailListConverter.converter(cartDTOList);
        if(CollectionUtils.isEmpty(orderDetails)){
            log.error("购物车不能为空,String={}",orderForm.getItems());
            throw new ProductException(ResultEnums.CART_EMPTY);
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerOpenid("oTgZpwTmoIcVzc0LlB3gsvU5sqFc");
        orderDTO.setOrderDetails(orderDetails);
        OrderDTO dto = orderService.create(orderDTO);
        OrderVo orderVo = new OrderVo();
        orderVo.setCode("200");
        orderVo.setMessage("true");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("orderId",orderDTO.getOrderId());
        orderVo.setData(stringStringHashMap);
        return dto;
    }


    /**
     * 方法功能:@requestParam标记的变量,会自动与相应请求的参数进行绑定,可以给请求参数设定默认值,此时可以不用传递请求参数
     * @param openid
     * @param page
     * @param size
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/30 15:07
     * @return com.chuanfangn.sell.vo.ResultVo<java.util.List<com.chuanfangn.sell.dto.OrderDTO>>
     */
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> getOrderList(@RequestParam(value = "openid") String openid,@RequestParam(value = "page",defaultValue = "0") Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单]openid不存在");
            throw new ProductException(ResultEnums.PARAM_ERROR);
        }
        Page<OrderDTO> orderDtoList = orderService.getOrderDtoList(openid, page, size);
        ResultVo success = VoUtil.success(orderDtoList.getContent());
        return success;
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public RefundResponse cancel(@RequestParam("orderId") String orderId){
        RefundResponse response = orderService.cancel(orderId);
        if(null != response) {
            log.info("[取消订单]成功,response={}",response);
        }
        JsonUtil.toJson(response);
        return response;
    }

}

package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.converter.CartDtoList2OrderDetailListConverter;
import com.chuanfangn.sell.dto.CartDTO;
import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.entity.OrderDetail;
import com.chuanfangn.sell.entity.ProductCategory;
import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.enums.ProductStatusEnums;
import com.chuanfangn.sell.enums.ResultEnums;
import com.chuanfangn.sell.exception.ProductException;
import com.chuanfangn.sell.form.OrderForm;
import com.chuanfangn.sell.service.CategoryService;
import com.chuanfangn.sell.service.OrderService;
import com.chuanfangn.sell.service.ProductInfoService;
import com.chuanfangn.sell.utils.VoUtil;
import com.chuanfangn.sell.vo.OrderVo;
import com.chuanfangn.sell.vo.ProductInfoVo;
import com.chuanfangn.sell.vo.ProductVo;
import com.chuanfangn.sell.vo.ResultVo;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @description: shop description~
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 22:15
 * @version: 0.01
 **/
@RestController
public class SellController {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public ResultVo test(){
        List<ProductInfo> productInfoList = productInfoService.findByStatus(ProductStatusEnums.UP.getCode());
        ArrayList<Integer> arrayList = new ArrayList();
        productInfoList.forEach(productInfo -> arrayList.add(productInfo.getCategoryType()));
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(arrayList);
        List<ProductVo> productVos = new ArrayList<>();
        for (ProductCategory productCategory : byCategoryTypeIn) {
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if((productInfo.getCategoryType()).equals(productCategory.getCategoryType())){
                    ProductInfoVo ProductInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, ProductInfoVo);
                    productInfoVos.add(ProductInfoVo);
                }
            }
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setProductInfoVos(productInfoVos);
            productVos.add(productVo);
        }
        ResultVo success = VoUtil.success(productVos);
        return success;
    }

    @PostMapping("/buyer/order/create")
    public OrderVo createOrder(@Valid OrderForm orderForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ProductException(ResultEnums.ORDER_UPDATE_FAIL.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        Gson gson = new Gson();
        List<CartDTO> cartDTOList = gson.fromJson(orderForm.getItems(),List.class);
        List<OrderDetail> orderDetails = CartDtoList2OrderDetailListConverter.converter(cartDTOList);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setOrderDetails(orderDetails);
        OrderDTO dto = orderService.create(orderDTO);
        OrderVo orderVo = new OrderVo();
        orderVo.setCode("200");
        orderVo.setMessage("true");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("orderId",orderDTO.getOrderId());
        orderVo.setData(stringStringHashMap);
        return orderVo;
    }
}

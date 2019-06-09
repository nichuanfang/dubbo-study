package com.chuanfangn.sell.service.serviceimpl;

import com.chuanfangn.sell.dto.CartDTO;
import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.enums.ProductStatusEnums;
import com.chuanfangn.sell.enums.ResultEnums;
import com.chuanfangn.sell.exception.ProductException;
import com.chuanfangn.sell.form.ProductForm;
import com.chuanfangn.sell.mapper.ProductInfoMapper;
import com.chuanfangn.sell.repository.ProductInfoRepository;
import com.chuanfangn.sell.service.CategoryService;
import com.chuanfangn.sell.service.ProductInfoService;
import com.chuanfangn.sell.utils.IdGenerateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 15:07
 * @version:
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductInfoMapper productInfoMapper;
    @Override
    public ProductInfo findOne(String id) {
        ProductInfo byProductId = productInfoRepository.findByProductId(id);
        return byProductId;
    }

    @Override
    public List<ProductInfo> findByStatus(Integer integer) {
        List<ProductInfo> allByProductStatus = productInfoRepository.findAllByProductStatus(integer);
        return allByProductStatus;
    }

    @Override
    public List<ProductInfo> findAll() {
        List<ProductInfo> all = productInfoRepository.findAll();
        return all;
    }

    @Override
    public Page<ProductInfo> findAllViaPage(Pageable pageable) {
        Page<ProductInfo> all = productInfoRepository.findAll(pageable);
        return all;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        ProductInfo save = productInfoRepository.save(productInfo);
        return save;
    }

    @Override
    public void delete(String id) {
        productInfoRepository.deleteById(id);
    }

    @Override
    public void deCreaseStock(List<CartDTO> collect) {
        collect.forEach(e -> {
            String productId = e.getProductId();
            Integer productQuantity = e.getProductQuantity();
            //从数据库查询库存
            ProductInfo one = findOne(productId);
            Integer productStock = one.getProductStock();
            if (productQuantity > productStock) {
                throw new ProductException(ResultEnums.PRODUCT_STOCK_ERROR);
            }
            //减库存
            one.setProductStock(productStock - productQuantity);
            save(one);
        });
    }

    @Override
    public void increaseStock(List<CartDTO> list) {
        list.forEach(e -> {
            Integer productQuantity = e.getProductQuantity();
            ProductInfo one = findOne(e.getProductId());
            Integer productStock = one.getProductStock();
            one.setProductStock(productStock + productQuantity);
            save(one);
        });
    }

    @Override
    public PageInfo<ProductInfo> findBypagehelper(int page, int size) {
        PageHelper.startPage(page, size);
        List<ProductInfo> productInfoList = productInfoMapper.findAll();
        PageInfo<ProductInfo> productInfoPageInfo = new PageInfo<>(productInfoList,8);
        return productInfoPageInfo;
    }

    @Override
    public Boolean up(String productId) {
        ProductInfo byProductId = productInfoMapper.findByProductId(productId);
        if(byProductId==null) {
            throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        if(byProductId.getProductStatus().equals(ProductStatusEnums.UP. getCode())){
            throw new ProductException(ResultEnums.PRODUCT_STATUS_ERROR);
        }
        byProductId.setProductStatus(ProductStatusEnums.UP.getCode());
        int update = productInfoMapper.update(byProductId);
        if(update!=0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean down(String productId) {
        ProductInfo byProductId = productInfoMapper.findByProductId(productId);
        if(byProductId==null) {
            throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        if(byProductId.getProductStatus().equals(ProductStatusEnums.DOWN. getCode())){
            throw new ProductException(ResultEnums.PRODUCT_STATUS_ERROR);
        }
        byProductId.setProductStatus(ProductStatusEnums.DOWN.getCode());
        int update = productInfoMapper.update(byProductId);
        if(update!=0){
            return true;
        }
        return false;
    }

    @Override
    public void update(ProductForm productForm) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductIcon("/sell/images/wallhaven-744071.jpg");
        productInfo.setProductStatus(ProductStatusEnums.UP.getCode());
        productInfo.setProductName(productForm.getName());
        productInfo.setProductPrice(BigDecimal.valueOf(productForm.getPrice()));
        productInfo.setProductStock(productForm.getStock());
        productInfo.setProductDescription(productForm.getDescription());
        productInfo.setCategoryType(productForm.getCategory());
        if(StringUtils.isEmpty(productForm.getProductId())) {
            try {
                 productInfo.setProductId(IdGenerateUtil.getId());
                productInfoMapper.add(productInfo);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("新增商品失败");
            }
        }
        productInfo.setProductId(productForm.getProductId());
        try {
            productInfoMapper.updateAll(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改商品失败");
        }
    }


}

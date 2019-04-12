package com.chuanfangn.sell.utils;

import com.chuanfangn.sell.vo.ProductVo;
import com.chuanfangn.sell.vo.ResultVo;

import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-12 15:03
 * @version:
 **/
public class VoUtil {
    public static ResultVo success(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData((List<ProductVo>) data);
        return resultVo;
    }

    /**
     * 方法功能: reloadMethod
     * @param
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/12 15:07
     * @return void
     */
    public static ResultVo success(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        return resultVo;
    }

    public static ResultVo failed(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMsg("failed");
        return resultVo;
    }
}

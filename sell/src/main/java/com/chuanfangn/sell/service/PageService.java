package com.chuanfangn.sell.service;

import com.chuanfangn.sell.dto.PageDto;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-03 20:55
 * @version:
 **/
public interface PageService {
    /**
     * 方法功能: 获得首尾页
     * @param currentPage
 * @param totalPages
     * @author f18326186224@gmail.com
     * @creatDate  2019/6/3 21:00
     * @return com.chuanfangn.sell.dto.PageDto
     */
    PageDto getPageDto(Integer currentPage,Integer totalPages);
}

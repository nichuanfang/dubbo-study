package com.chuanfangn.sell.service.serviceimpl;

import com.chuanfangn.sell.constant.PageConstant;
import com.chuanfangn.sell.dto.PageDto;
import com.chuanfangn.sell.service.PageService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-03 21:00
 * @version:
 **/
@Service
public class PageServiceImpl implements PageService {

    @Override
    public PageDto getPageDto(Integer currentPage, Integer totalPages) {

        PageDto pageDto = new PageDto();
        if (totalPages <= PageConstant.PAGE_CELL) {
            pageDto.setFirstPage(1);
            pageDto.setEndPage(totalPages);
        }else if (currentPage%PageConstant.PAGE_CELL==0){
            pageDto.setFirstPage((currentPage/PageConstant.PAGE_CELL-1)*PageConstant.PAGE_CELL+1);
            pageDto.setEndPage(currentPage);
        }else{
            if(currentPage/PageConstant.PAGE_CELL==totalPages/PageConstant.PAGE_CELL){
                pageDto.setFirstPage((currentPage/PageConstant.PAGE_CELL)*PageConstant.PAGE_CELL+1);
                pageDto.setEndPage(totalPages);
            }else{
                pageDto.setFirstPage((currentPage/PageConstant.PAGE_CELL)*PageConstant.PAGE_CELL+1);
                pageDto.setEndPage((currentPage/PageConstant.PAGE_CELL)*PageConstant.PAGE_CELL+PageConstant.PAGE_CELL);
            }
        }
        return pageDto;
    }
}

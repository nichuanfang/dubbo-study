package com.chuanfangn.sell.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-03 20:57
 * @version:
 **/
@Data
public class PageDto implements Serializable {

    private static final long serialVersionUID = -744633010900344036L;

    private Integer firstPage;

    private Integer endPage;
}

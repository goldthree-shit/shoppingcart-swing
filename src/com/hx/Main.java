package com.hx;

import com.hx.common.DataInIt;
import com.hx.window.IndexWindow;


/**
 * @ClassName Main
 * @Author goldthree-shit
 * @Date 2021/12/24 20:15
 * @Description 主启动类
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        DataInIt.init();
        new IndexWindow("购物车", 400, 100, 1000, 750);
    }
}

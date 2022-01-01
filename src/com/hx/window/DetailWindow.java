package com.hx.window;

import com.hx.page.QueryPage;
import com.hx.page.ShoppingCardDetailPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @ClassName DetailWindow
 * @Author goldthree-shit
 * @Date 2021/12/24 23:50
 * @Description 详情窗口
 * @Version 1.0
 */
public class DetailWindow extends JFrame {
    JTabbedPane jTabbedPane;

    public DetailWindow(String title, int x, int y, int width, int height) {
        this.initContainer(title, x, y, width, height);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    void initContainer(String title, int x, int y, int width, int height) {
        this.setTitle(title);
        this.setBounds(x, y, width, height);
        this.setLayout(new CardLayout());

        jTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        jTabbedPane.add("购物车详情", new ShoppingCardDetailPage());
        jTabbedPane.add("商品查询", new QueryPage());

        jTabbedPane.validate();
        this.add(jTabbedPane, BorderLayout.NORTH);
        this.validate();

        // 添加点击关闭窗口事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setVisible(false);
                new IndexWindow("购物车", 400, 100, 1000, 750);
            }
        });
    }
}

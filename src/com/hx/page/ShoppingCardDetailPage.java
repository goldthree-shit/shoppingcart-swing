package com.hx.page;

import com.hx.common.DataInIt;
import com.hx.entity.Commodity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @ClassName ShoppingCardDetail
 * @Author goldthree-shit
 * @Date 2021/12/25 0:30
 * @Description 购物车详情页面
 * @Version 1.0
 */
public class ShoppingCardDetailPage extends JPanel {

    JTable jTable;
    Object[] tableHeader = {"序号", "商品名称", "单价", "数量", "总价格"};
    Box boxV1;
    JPanel centerContainer;

    public ShoppingCardDetailPage() {

        this.setLayout(new BorderLayout());
        // 上方表格区域
        List<Commodity> commodities = DataInIt.getCommodities();
        Object[][] content = new Object[commodities.size()][tableHeader.length];
        Integer totalPrice = 0;
        for (int i = 0; i < commodities.size(); i++) {
            Commodity commodity = commodities.get(i);
            content[i][0] = commodity.getOrder();
            content[i][1] = commodity.getName();
            content[i][2] = commodity.getPrice();
            content[i][3] = commodity.getCounts();
            totalPrice += commodity.getCounts() * commodity.getPrice();
            content[i][4] = commodity.getCounts() * commodity.getPrice();
        }
        jTable = new JTable(content, tableHeader);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);

        // 中间部分，包括显示商品总金额标签和清空购物车按钮
        boxV1 = Box.createVerticalBox();
        JLabel totalPricelabel = new JLabel("商品总金额:  " + totalPrice + " ￥");
        totalPricelabel.setForeground(Color.red);
        totalPricelabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));

        JLabel clearLabel = new JLabel("清空购物车");
        clearLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JButton clearButton = new JButton();
        clearButton.add(clearLabel);
        // 清空购物车按钮绑定事件
        clearButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataInIt.clear();
                TableModel tableModel = new DefaultTableModel(new Object[0][0], tableHeader);
                jTable.setModel(tableModel);
                totalPricelabel.setText("商品总金额:  " + 0 + " ￥");
            }
        });

        // 刷新购物车
        JLabel refreshLabel = new JLabel("刷新购物车");
        refreshLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JButton refreshButton = new JButton();
        refreshButton.add(refreshLabel);
        // 刷新购物车按钮绑定事件
        refreshButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            List<Commodity> commodities = DataInIt.getCommodities();
            System.out.println("--------------");
            System.out.println(commodities);
            Object[][] content = new Object[commodities.size()][tableHeader.length];
            Integer totalPrice = 0;
            for (int i = 0; i < commodities.size(); i++) {
                Commodity commodity = commodities.get(i);
                content[i][0] = commodity.getOrder();
                content[i][1] = commodity.getName();
                content[i][2] = commodity.getPrice();
                content[i][3] = commodity.getCounts();
                totalPrice += commodity.getCounts() * commodity.getPrice();
                content[i][4] = commodity.getCounts() * commodity.getPrice();
            }
            TableModel tableModel = new DefaultTableModel(content, tableHeader);
            totalPricelabel.setText("商品总金额:  " + totalPrice + " ￥");
            jTable.setModel(tableModel);
            }
        });


        boxV1.add(totalPricelabel);
        boxV1.add(Box.createVerticalStrut(20));
        boxV1.add(clearButton);
        boxV1.add(Box.createVerticalStrut(20));
        boxV1.add(refreshButton);
        // 把boxV1加入容器中，这个容器默认浮动布局，所以这些box会自动居中
        // 否则因为更上一层的pane是BorderLayout布局，这些标签会显示在左边
        centerContainer = new JPanel();
        centerContainer.add(boxV1);


        this.add(jScrollPane, BorderLayout.NORTH);
        this.add(centerContainer, BorderLayout.CENTER);
    }
}

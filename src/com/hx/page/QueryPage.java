package com.hx.page;

import com.hx.common.DataInIt;
import com.hx.entity.Commodity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @ClassName QueryPage
 * @Author goldthree-shit
 * @Date 2021/12/25 16:28
 * @Description 商品查询页面
 * @Version 1.0
 */
public class QueryPage extends JPanel {

    Box boxV1, boxV2, boxV3, boxV4, base;

    public QueryPage() {

        this.setLayout(new FlowLayout());
        boxV1 = Box.createVerticalBox();
        boxV2 = Box.createVerticalBox();
        boxV3 = Box.createHorizontalBox();
        boxV4 = Box.createHorizontalBox();
        base = Box.createVerticalBox();

        // 标签部分，全部放入 boxV1中，boxV1竖向
        JLabel orderLabel = new JLabel("商品序号");
        orderLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JLabel nameLabel = new JLabel("商品名称");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JLabel priceLabel = new JLabel("商品价格");
        priceLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JLabel countLabel = new JLabel("购买数量");
        countLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JLabel totalPriceLabel = new JLabel("总金额");
        totalPriceLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));

        boxV1.add(orderLabel);
        boxV1.add(Box.createVerticalStrut(20));
        boxV1.add(nameLabel);
        boxV1.add(Box.createVerticalStrut(20));
        boxV1.add(priceLabel);
        boxV1.add(Box.createVerticalStrut(20));
        boxV1.add(countLabel);
        boxV1.add(Box.createVerticalStrut(20));
        boxV1.add(totalPriceLabel);

        // 输入框，全部放入 boxV2中，boxV2竖向
        JTextField orderField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField countField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JTextField totalPriceField = new JTextField(20);
        boxV2.add(orderField);
        boxV2.add(Box.createVerticalStrut(20));
        boxV2.add(nameField);
        boxV2.add(Box.createVerticalStrut(20));
        boxV2.add(priceField);
        boxV2.add(Box.createVerticalStrut(20));
        boxV2.add(countField);
        boxV2.add(Box.createVerticalStrut(20));
        boxV2.add(totalPriceField);

        // boxV1 和 boxV2 加入 boxV3， boxV3 横向
        boxV3.add(boxV1);
        boxV3.add(Box.createHorizontalStrut(20));
        boxV3.add(boxV2);

        // 各种操作的按钮
        JLabel queryLabel = new JLabel("查询商品");
        queryLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JButton queryButton = new JButton();
        queryButton.add(queryLabel);
        queryButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer order = Integer.parseInt(orderField.getText());
                List<Commodity> commodities = DataInIt.getCommodities();
                Commodity ansCommodity = null;
                for ( Commodity commodity : commodities) {
                    if (commodity.getOrder().equals(order)) {
                        ansCommodity = commodity;
                        break;
                    }
                }
                if (ansCommodity != null) {
                    nameField.setText(ansCommodity.getName());
                    priceField.setText(ansCommodity.getPrice() + "");
                    countField.setText(ansCommodity.getCounts() + "");
                    totalPriceField.setText(ansCommodity.getPrice() * ansCommodity.getCounts() + "");
                } else {
                    nameField.setText("未查到相关记录");
                    priceField.setText("未查到相关记录");
                    countField.setText("未查到相关记录");
                    totalPriceField.setText("未查到相关记录");
                }
            }
        });
        JLabel queryFirstLabel = new JLabel("第一件商品");
        queryFirstLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JButton queryFirstButton = new JButton();
        queryFirstButton.add(queryFirstLabel);
        queryFirstButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Commodity firstCommodity = DataInIt.getFirstCommodity();
                if (firstCommodity != null) {
                    orderField.setText(firstCommodity.getOrder() + "");
                    nameField.setText(firstCommodity.getName());
                    priceField.setText(firstCommodity.getPrice() + "");
                    countField.setText(firstCommodity.getCounts() + "");
                    totalPriceField.setText(firstCommodity.getPrice() * firstCommodity.getCounts() + "");
                } else {
                    orderField.setText("未查到相关记录");
                    nameField.setText("未查到相关记录");
                    priceField.setText("未查到相关记录");
                    countField.setText("未查到相关记录");
                    totalPriceField.setText("未查到相关记录");
                }
            }
        });

        // 最后一件商品
        JLabel queryLastLabel = new JLabel("最后一件商品");
        queryLastLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        JButton queryLastButton = new JButton();
        queryLastButton.add(queryLastLabel);
        queryLastButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Commodity lastCommodity = DataInIt.getLastCommodity();
                if (lastCommodity != null) {
                    orderField.setText(lastCommodity.getOrder() + "");
                    nameField.setText(lastCommodity.getName());
                    priceField.setText(lastCommodity.getPrice() + "");
                    countField.setText(lastCommodity.getCounts() + "");
                    totalPriceField.setText(lastCommodity.getPrice() * lastCommodity.getCounts() + "");
                } else {
                    orderField.setText("未查到相关记录");
                    nameField.setText("未查到相关记录");
                    priceField.setText("未查到相关记录");
                    countField.setText("未查到相关记录");
                    totalPriceField.setText("未查到相关记录");
                }
            }
        });

        // 商品删除
        JLabel deleteLabel = new JLabel("删除商品");
        deleteLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        deleteLabel.setForeground(Color.red);
        JButton deleteButton = new JButton();
        deleteButton.add(deleteLabel);
        deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer order = Integer.parseInt(orderField.getText());
                List<Commodity> commodities = DataInIt.getCommodities();
                int pos = 0;
                for ( Commodity commodity : commodities) {
                    if (commodity.getOrder().equals(order)) {
                        break;
                    }
                    pos++;
                }
                // 如果存在
                if (pos != commodities.size()) {
                    commodities.remove(pos);
                    nameField.setText("删除成功！！！！");
                    priceField.setText("删除成功！！！！");
                    countField.setText("删除成功！！！！");
                    totalPriceField.setText("删除成功！！！！");
                } else {
                    nameField.setText("未查到相关记录, 无法删除");
                    priceField.setText("未查到相关记录, 无法删除");
                    countField.setText("未查到相关记录, 无法删除");
                    totalPriceField.setText("未查到相关记录, 无法删除");
                }

            }
        });

        // 以上几个按钮全部加入 boxV4 中， boxV4 横向
        boxV4.add(queryFirstButton);
        boxV4.add(Box.createHorizontalStrut(20));
        boxV4.add(queryButton);
        boxV4.add(Box.createHorizontalStrut(20));
        boxV4.add(deleteButton);
        boxV4.add(Box.createHorizontalStrut(20));
        boxV4.add(queryLastButton);

        // 把 boxV3 和 box V4 全部加入 base中， base 纵向
        base.add(Box.createVerticalStrut(160));
        base.add(boxV3);
        base.add(Box.createVerticalStrut(20));
        base.add(boxV4);
        this.add(base);
    }
}

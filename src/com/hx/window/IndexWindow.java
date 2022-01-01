package com.hx.window;

import com.hx.common.DataInIt;
import com.hx.entity.Commodity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @ClassName IndexWindow
 * @Author goldthree-shit
 * @Date 2021/12/24 20:38
 * @Description 主页
 * @Version 1.0
 */
public class IndexWindow extends JFrame {

    JButton shoppingCartButton, addButton;
    JPanel shoppingCardContainer;
    ImageIcon imageIcon;
    Box base, baseBox, boxV1, boxV2, boxV3;

    public IndexWindow(String title, int x, int y, int width, int height) {
        this.initContainer(title, x, y, width, height);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initContainer(String title, int x, int y, int width, int height) {
        this.setTitle(title);
        this.setBounds(x, y, width, height);
        this.setLayout(new FlowLayout());

        // 设置图片按钮
        imageIcon = new ImageIcon("./shoppingcart.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(200, 160, Image.SCALE_DEFAULT ));
        shoppingCartButton = new JButton(imageIcon);
        shoppingCartButton.setPreferredSize(new Dimension(200,160));
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DetailWindow("详情", x, y, width, height);
            }
        });
        shoppingCardContainer = new JPanel();
        shoppingCardContainer.add(shoppingCartButton);
        boxV3 = Box.createVerticalBox();
        boxV3.add(shoppingCardContainer);

        // 设置输入框的标签
        boxV1 = Box.createVerticalBox();
        JLabel nameLabel = new JLabel("商品名称");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        boxV1.add(nameLabel);
        boxV1.add(Box.createVerticalStrut(20));
        JLabel priceLabel = new JLabel("单价");
        priceLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        boxV1.add(priceLabel);
        boxV1.add(Box.createVerticalStrut(20));
        JLabel countLabel = new JLabel("数量");
        countLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        boxV1.add(countLabel);

        // 设置输入框
        boxV2 = Box.createVerticalBox();
        JTextField nameField = new JTextField(10);
        boxV2.add(nameField);
        boxV2.add(Box.createVerticalStrut(20));
        JTextField priceField = new JTextField(10);
        boxV2.add(priceField);
        boxV2.add(Box.createVerticalStrut(20));
        JTextField countField = new JTextField(10);
        boxV2.add(countField);

        // 装入V1 和 V2 进入 baseBox表示 标签和输入框
        baseBox = Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(20));
        baseBox.add(boxV2);


        // 添加商品按钮
        JLabel label4 = new JLabel("添加到购物车");
        label4.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        addButton = new JButton();
        addButton.add(label4);
        addButton.setPreferredSize(new Dimension(200, 50));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                System.out.println(nameField.getText());
                System.out.println(countField.getText());
                System.out.println(priceField.getText());
                */
                DataInIt.addData(new Commodity(nameField.getText(), Integer.parseInt(priceField.getText()),
                        Integer.parseInt(countField.getText())));
                System.out.println(DataInIt.getCommodities());
            }
        });

        // 把baseBox 和 V3 装入 base，即图片按钮, 输入框部分, 添加商品按钮
        base = Box.createVerticalBox();
        base.add(Box.createVerticalStrut(20));
        base.add(boxV3);
        base.add(Box.createVerticalStrut(80));
        base.add(baseBox);
        base.add(Box.createVerticalStrut(80));
        base.add(addButton);

        this.add(base);

        // 添加关闭窗口事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                DataInIt.logout();
            }
        });
    }
}

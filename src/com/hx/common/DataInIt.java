package com.hx.common;

import com.hx.entity.Commodity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DataInIt
 * @Author goldthree-shit
 * @Date 2021/12/24 20:33
 * @Description 完成数据操作以及 I/O 处理的类。其中写出操作实在主窗口点击关闭后，直接清空文件，然后一次性把所有的数据写入
 *              动态写入文件还要维护次序有点麻烦。
 * @Version 1.0
 */
public class DataInIt {
    private static List<Commodity> commodities;
    private static int order;
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;

    /***
     * 初始化，包括：
     *      1. 从文件读入，如文件为空或列表长度为0则新建对象
     *      2. 完成 order（商品记录） 的初始化操作
     */
    public static void init() {
        File file = new File("./data.txt");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            // 防止是空文件，否则有读入异常
            if (file.length() > 4) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                try {
                    commodities = (List<Commodity>) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    fis.close();
                    ois.close();
                }
                // 防止读入空的
                if (commodities.size() != 0) {
                    order = commodities.get(commodities.size() - 1).getOrder() + 1;
                } else {
                    commodities = new ArrayList<>();
                    order = 1;
                }
            } else {
                commodities = new ArrayList<>();
                order = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 在退出时一次性把 ArrayList 中的数据写入
    public static void logout() {
        File file = new File("./data.txt");
        FileWriter writer = null;
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file);
            writer.write("");
            writer.flush();
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(commodities);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 添加数据
    public static void addData(Commodity commodity) {
        commodity.setOrder(order++);
        commodities.add(commodity);
    }

    // 获取全部数据
    public static List<Commodity> getCommodities() {
        return commodities;
    }

    // 清空数据, 此时先不处理IO
    public static void clear() {
        commodities.clear();
        order = 0;
    }

    public static Commodity getFirstCommodity() {
        return commodities.size() == 0 ? null : commodities.get(0);
    }

    public static Commodity getLastCommodity() {
        return commodities.size() == 0 ? null : commodities.get(commodities.size() - 1);
    }


}

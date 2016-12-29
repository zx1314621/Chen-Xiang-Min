package com.lzw;

import java.awt.*;
import java.net.URL;

import javax.swing.JPanel;

/**
 * 
 */
public class ImagePanel extends JPanel implements Runnable {
    private static Image image;
    private static Image rotateIcon;
    private double angle = 0;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    
    public ImagePanel() {
        URL bgUrl = getClass().getResource("bg.jpg"); // 获取背景图片URL
        URL rotateUrl = getClass().getResource("rotate.png");// 获取转盘图片URL
        image = tk.createImage(bgUrl);// 加载背景图片
        rotateIcon = tk.createImage(rotateUrl);// 加载转盘图片
        setOpaque(false);// 使面板透明
        setLayout(null);
    }
    
    protected void paintComponent(Graphics g) {
        int width = getWidth();// 获取面板宽度
        int height = getHeight();// 获取高度
        if (image != null) {// 如果背景图片对象不为空
            g.drawImage(image, 0, 0, width, height, this);// 根据面板大小绘制图片
        }
        Graphics2D g1 = (Graphics2D) g.create();// 获取绘图上下文的副本
        // 设置绘图上下文以质量为主，绘制图片，这样可以避免图片旋转时的锯齿
        g1.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        if (rotateIcon != null) {// 如果转盘图片对象不为null
            int iconWidth = rotateIcon.getWidth(this);// 获取转盘图片宽度
            int iconHeight = rotateIcon.getHeight(this);// 获取转盘图片高度
            g1.rotate(Math.toRadians(angle), width / 2,
                    height / 2);// 设置旋转角度
            g1.drawImage(rotateIcon, width / 2 - iconWidth / 2,
                    height / 2 - iconHeight / 2, this);// 在面板中间绘制转盘图片
        }
    }
    
    int count = 0;
    int temp = 0;
    int randNum = 0;
    
    @Override
    public void run() {
        double stAngle = Math.random() * 360;
        angle = stAngle;
        while (angle < stAngle + 1200) {// 角度小于1200
            angle += 6;// 累加角度值
            repaint();// 重写绘制界面
            try {
                Thread.sleep(10);// 线程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double sleepTime = 10;// 定义休眠变量
        while (sleepTime < 90) {// 角度小于120
            angle += 6;// 累加角度值
            repaint();// 重写绘制界面
            try {
                Thread.sleep((int) (sleepTime += 0.5));// 休眠并累加休眠变量值
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

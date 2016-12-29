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
        URL bgUrl = getClass().getResource("bg.jpg"); // ��ȡ����ͼƬURL
        URL rotateUrl = getClass().getResource("rotate.png");// ��ȡת��ͼƬURL
        image = tk.createImage(bgUrl);// ���ر���ͼƬ
        rotateIcon = tk.createImage(rotateUrl);// ����ת��ͼƬ
        setOpaque(false);// ʹ���͸��
        setLayout(null);
    }
    
    protected void paintComponent(Graphics g) {
        int width = getWidth();// ��ȡ�����
        int height = getHeight();// ��ȡ�߶�
        if (image != null) {// �������ͼƬ����Ϊ��
            g.drawImage(image, 0, 0, width, height, this);// ��������С����ͼƬ
        }
        Graphics2D g1 = (Graphics2D) g.create();// ��ȡ��ͼ�����ĵĸ���
        // ���û�ͼ������������Ϊ��������ͼƬ���������Ա���ͼƬ��תʱ�ľ��
        g1.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        if (rotateIcon != null) {// ���ת��ͼƬ����Ϊnull
            int iconWidth = rotateIcon.getWidth(this);// ��ȡת��ͼƬ���
            int iconHeight = rotateIcon.getHeight(this);// ��ȡת��ͼƬ�߶�
            g1.rotate(Math.toRadians(angle), width / 2,
                    height / 2);// ������ת�Ƕ�
            g1.drawImage(rotateIcon, width / 2 - iconWidth / 2,
                    height / 2 - iconHeight / 2, this);// ������м����ת��ͼƬ
        }
    }
    
    int count = 0;
    int temp = 0;
    int randNum = 0;
    
    @Override
    public void run() {
        double stAngle = Math.random() * 360;
        angle = stAngle;
        while (angle < stAngle + 1200) {// �Ƕ�С��1200
            angle += 6;// �ۼӽǶ�ֵ
            repaint();// ��д���ƽ���
            try {
                Thread.sleep(10);// �߳�����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double sleepTime = 10;// �������߱���
        while (sleepTime < 90) {// �Ƕ�С��120
            angle += 6;// �ۼӽǶ�ֵ
            repaint();// ��д���ƽ���
            try {
                Thread.sleep((int) (sleepTime += 0.5));// ���߲��ۼ����߱���ֵ
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

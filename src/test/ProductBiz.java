package test;

import javax.swing.JOptionPane;

public class ProductBiz {
	public static void main(String[] args){
        //��������ÿ����Ʒ������
        int[] counts = new int[3];
        double total = 0;//��Ʒ�ܼ�
        for (int i = 0; i < counts.length; i++) {
            String name = JOptionPane.showInputDialog("��������Ʒ���ƣ�");
            String strPrice = JOptionPane.showInputDialog("��������Ʒ���ۣ�");
            String  strCount = JOptionPane.showInputDialog("�����빺��������");
            counts[i] = Integer.parseInt(strCount);
        }
        System.out.println("��Ʒ�ܽ��Ϊ��" + total);
    }
}

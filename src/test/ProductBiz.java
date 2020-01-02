package test;

import javax.swing.JOptionPane;

public class ProductBiz {
	public static void main(String[] args){
        //用来保存每种商品的总量
        int[] counts = new int[3];
        double total = 0;//商品总价
        for (int i = 0; i < counts.length; i++) {
            String name = JOptionPane.showInputDialog("请输入商品名称：");
            String strPrice = JOptionPane.showInputDialog("请输入商品单价：");
            String  strCount = JOptionPane.showInputDialog("请输入购买数量：");
            counts[i] = Integer.parseInt(strCount);
        }
        System.out.println("商品总金额为：" + total);
    }
}

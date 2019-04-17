package com.linxf.main;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 数字-->大写汉字转换工具界面
 *
 * @author lintao
 */
public class MainFram extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField num_txt;

    private JTextField word_txt;

    private JLabel text_copy;

    /**
     * 启动入口
     * <p>
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFram frame = new MainFram();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 程序主界面 FRAM
     */
    public MainFram() {
        setResizable(false);
        setBackground(Color.GRAY);
        setTitle("\u5927\u5199\u6C49\u5B57\u8F6C\u6362\u5DE5\u5177");
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(MainFram.class.getResource("/images/change_ico.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 380, 210);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel label = new JLabel("\u8BF7\u8F93\u5165\u6570\u5B57\uFF1A");

        num_txt = new JTextField();
        num_txt.setColumns(10);

        word_txt = new JTextField();
        word_txt.setColumns(10);

        JButton bt_change = new JButton("\u8F6C\u6362");
        bt_change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeToActionPerformed(e);
            }
        });

        JButton bt_reset = new JButton("\u6E05\u7A7A");
        bt_reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });

        JButton bt_copy = new JButton("\u590D\u5236");
        bt_copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                copyTextActionPerformed(word_txt.getText());
            }
        });

        text_copy = new JLabel("\u6587\u5B57\u5DF2\u590D\u5236\uFF01");
        text_copy.setVisible(false);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(43)
                                                .addGroup(gl_contentPane.
                                                        createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(word_txt)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(label)
                                                                .addGap(18)
                                                                .addComponent(num_txt,
                                                                        GroupLayout.PREFERRED_SIZE, 185,
                                                                        GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(78)
                                                .addComponent(bt_copy)
                                                .addGap(18)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(text_copy)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(bt_reset)
                                                                .addGap(18)
                                                                .addComponent(bt_change)))))
                                .addContainerGap(46, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(31)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(num_txt, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addComponent(word_txt, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(bt_copy)
                                        .addComponent(bt_reset)
                                        .addComponent(bt_change))
                                .addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(text_copy)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);

        // 设置JFrame居中显示
        this.setLocationRelativeTo(null);

    }

    /**
     * 转换事件处理
     *
     * @param e
     */
    protected void changeToActionPerformed(ActionEvent e) {
        String s = num_txt.getText();//取出用户输入的数字
        if (s.length() == 0) {
            JOptionPane.showMessageDialog(null, "请输入12位以内的数字！");
            num_txt.requestFocus();
            return;
        }
        if (s.length() > 12) {
            JOptionPane.showMessageDialog(null, "输入数字过长！\n请输入12位以内的数字！");
            num_txt.requestFocus();
            return;
        }
        try {
            Long.parseLong(s);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "输入格式不正确！\n请输入12位以内的数字！");
            num_txt.requestFocus();
            return;
        }

        //将数字转换为大写汉字输出到文本框
        this.word_txt.setText(new NumToWord().wordFinal(num_txt.getText()));
    }

    /**
     * 重置事件处理
     *
     * @param evt
     */
    private void resetValueActionPerformed(ActionEvent evt) {
        this.num_txt.setText("");
        this.word_txt.setText("");
    }

    /**
     * 将制定内容复制到剪切板
     *
     * @param text
     */
    public void copyTextActionPerformed(String text) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(text);
        clip.setContents(tText, null);
        this.showWord();//显示“文字已复制”2秒
    }

    /**
     * 显示text_copy文字2秒
     */
    public void showWord() {
        //新建一个线程来显示text_copy文字2秒
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                text_copy.setVisible(true);
                try {
                    Thread.sleep(2000);// 该线程睡眠2秒
                } catch (InterruptedException ex) {
                }
                text_copy.setVisible(false);
            }
        });
        t.start();// 启动线程
    }

}

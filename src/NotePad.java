import java.io.*;
import javax.swing.*;
// Grafiksel Kullanıcı Arayüzü (GUI )için kullanılır

import java.awt.*;
// Java'nın özgün AWT (Abstract Window Toolkit) kütüphanesini içerir.

import java.awt.event.*;
// AWT olayları işlemek için çeşitli sınıflar ve arabirimler içerir ve bu sınıf ve arabirimler java.awt.event paketi altında bulunur.

import javax.swing.filechooser.FileNameExtensionFilter;

public class NotePad extends  JFrame implements ActionListener, WindowListener {

    JTextArea jta = new JTextArea();
    File fnameContainer;
    public NotePad(){
        Font fnt =  new Font("Arial",Font.PLAIN,15);
        Container con = getContentPane();
        JMenuBar jab = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmeedit = new JMenu("Edit");
        JMenu jmhelp = new JMenu("Help");
    }

}

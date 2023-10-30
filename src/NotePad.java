

import java.io.*;
import javax.swing.*;
// Grafiksel Kullanıcı Arayüzü (GUI )için kullanılır

import java.awt.*;
// Java'nın özgün AWT (Abstract Window Toolkit) kütüphanesini içerir.

import java.awt.event.*;
// AWT olayları işlemek için çeşitli sınıflar ve arabirimler içerir ve bu sınıf ve arabirimler java.awt.event paketi altında bulunur.


public class NotePad extends  JFrame implements ActionListener, WindowListener {

    JTextArea jta = new JTextArea();
    File fnameContainer;


    public NotePad(){
        Font fnt =  new Font("Arial",Font.PLAIN,15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();



        JMenu jmfile = new JMenu("File");
        JMenu jmeedit = new JMenu("Edit");
        JMenu jmhelp = new JMenu("Help");

        con.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta); // Kaydırma işlemleri için.
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrText.setVisible(true); // görünürlük

        jta.setFont(fnt);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

        con.add(sbrText);

        createMenuItem(jmfile,"New");
        createMenuItem(jmfile,"Open");
        createMenuItem(jmfile,"Save");

        jmfile.addSeparator();

        createMenuItem(jmeedit,"Cut");
        createMenuItem(jmeedit,"Copy");
        createMenuItem(jmeedit,"Paste");

        createMenuItem(jmhelp,"About NodePad");

        jmb.add(jmfile);
        jmb.add(jmeedit);
        jmb.add(jmhelp);

        setJMenuBar(jmb);
        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(500,500);
        setTitle("Untitle.txt");
        setVisible(true);


    }

    //yeni bir menü öğesi eklemek için.
    public void createMenuItem(JMenu jm, String txt) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        jm.add(jmi);
    }


    public void actionPerformed(ActionEvent e ){

        // dosyaları veya dizinleri seçme.
        JFileChooser  jfc = new JFileChooser();

        if(e.getActionCommand().equals("New")){
            this.setTitle("Untitled.txt-Nodepad");
            jta.setText("");
            fnameContainer = null;
        }
        else if (e.getActionCommand().equals("Open")){
            int ret = jfc.showDialog(null,"Open");
            if(ret == JFileChooser.APPROVE_OPTION){
                try{
                    File fyl = jfc.getSelectedFile();
                    OpenFile(fyl.getAbsolutePath());
                    this.setTitle(fyl.getName()+ " - NotePad"  );
                    fnameContainer = fyl;

                }catch (IOException ers){  }
            }

        }
    else if(e.getActionCommand().equals("Save")){
        if(fnameContainer != null){
                jfc.setCurrentDirectory(fnameContainer);
                jfc.setSelectedFile(fnameContainer);
        } else {
            jfc.setSelectedFile(new File("Untitled.txt"));
        }
        int ret = jfc.showSaveDialog(null);
        if (ret ==  JFileChooser.APPROVE_OPTION){
            try{
                File fyl = jfc.getSelectedFile();
                SaveFile(fyl.getAbsolutePath());
                this.setTitle(fyl.getName()+" - NodePad" );
                fnameContainer =fyl;
            }catch (Exception ers){  }

        }

        }
    else if(e.getActionCommand().equals("Exit")){
        Exiting();
    }
    else if(e.getActionCommand().equals("Copy")){
        jta.copy();
        }
    else if(e.getActionCommand().equals("Paste")){
        jta.paste();
        }
    else if(e.getActionCommand().equals("NotePad")){
        JOptionPane.showMessageDialog(this,"Created by : KaganY.","Nodepad",JOptionPane.INFORMATION_MESSAGE);

        }
    else if(e.getActionCommand().equals("Cut")){
        jta.cut();

        }
    }

    public void OpenFile(String fname) throws  IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String l;
        jta.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while( (l=d.readLine()) !=null  ){
            jta.setText(jta.getText() + l +"\r\n" );
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }

    public void SaveFile(String fname) throws  IOException{

        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream o =  new DataOutputStream(new FileOutputStream(fname));
        o.writeBytes(jta.getText());
        o.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }

    public void windowDeactivated(WindowEvent e){ }

    public void windowActivated(WindowEvent e){ }

    public void windowDeiconified(WindowEvent e){ }

    public void windowIconified(WindowEvent e){ }

    public void windowClosed(WindowEvent e){ }

    public  void windowClosing(WindowEvent e){
        Exiting();
    }
    public void windowOpened(WindowEvent e){ }

    public void Exiting(){
        System.exit(0);
    }





}

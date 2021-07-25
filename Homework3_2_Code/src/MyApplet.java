import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyApplet extends JApplet {
    Panel bottom_p;
    TextField text;
    Choice shapeChooser;
    ButtonGroup colorChooser;
    JLabel lab;

    public void init(){
        this.setLayout(new BorderLayout());
        super.setSize(600, 400);
        //super.setLocation(0,0);

        addPanel();
        /*添加菜单*/
        addMenu();
        /*添加文本框*/
        addText();
        /*添加下拉选单*/
        addShapeChooser();
        /*添加单选框*/
        addColorChooser();
        /*添加标签以显示图片*/
        addLabel();

    }
    public void addPanel() {
        bottom_p = new Panel();
        bottom_p.setBackground(Color.white);
        bottom_p.setSize(400, 100);
        getContentPane().add(bottom_p, BorderLayout.SOUTH);
    }

    public void addMenu() {
        JMenuBar m_menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem exit = new JMenuItem("Exit");
        JMenu menuEdit = new JMenu("Edit");
        JMenuItem paint = new JMenuItem("Paint");
        JMenuItem clear = new JMenuItem("Clear");
        /*将菜单File加入菜单条，将相应的菜单项加入菜单*/
        m_menuBar.add(menuFile);
        menuFile.add(open);
        menuFile.add(exit);
        /*将菜单Edit加入菜单条，将相应的菜单项加入菜单*/
        m_menuBar.add(menuEdit);
        menuEdit.add(paint);
        menuEdit.add(clear);
        /*把菜单条加入frame*/
        //setMenuBar(m_menuBar);
        getContentPane().add(m_menuBar,BorderLayout.NORTH);
        /*为file菜单的每个菜单项注册监听者*/
        ActionListener f_ml_open = new ML_open();
        ActionListener f_ml_exit = new ML_exit();
        open.addActionListener(f_ml_open);
        exit.addActionListener(f_ml_exit);
        /*为edit菜单的每个菜单项注册监听者*/
        ActionListener e_ml_paint = new ML_paint();
        ActionListener e_ml_clear = new ML_clear();
        paint.addActionListener(e_ml_paint);
        clear.addActionListener(e_ml_clear);
    }

    public void addText() {
        text = new TextField(10);
        bottom_p.add(text,BorderLayout.WEST);
    }

    public void addColorChooser(){
        colorChooser = new ButtonGroup();


        JRadioButton red_but = new JRadioButton("red");
        red_but.setActionCommand("red");
        colorChooser.add(red_but);
        red_but.setSelected(true);

        JRadioButton yellow_but = new JRadioButton("yellow");
        yellow_but.setActionCommand("yellow");
        colorChooser.add(yellow_but);

        JRadioButton green_but = new JRadioButton("green");
        green_but.setActionCommand("green");
        colorChooser.add(green_but);

        bottom_p.add(red_but, BorderLayout.WEST);
        bottom_p.add(yellow_but, BorderLayout.WEST);
        bottom_p.add(green_but, BorderLayout.WEST);
    }

    public void addShapeChooser() {
        shapeChooser = new Choice();
        shapeChooser.add("oval");
        shapeChooser.add("rectangle");
        shapeChooser.add("triangle");
        bottom_p.add(shapeChooser, BorderLayout.CENTER);
    }

    public void addLabel() {
        lab = new JLabel();
        add(lab, BorderLayout.CENTER);
    }

    public class ML_open implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String path;
            ImageIcon pic;
            File temp;
            String fileName;

            JFileChooser file = new JFileChooser();
            file.setFileFilter(new FileNameExtensionFilter("image(*gif)", "gif"));
            file.showOpenDialog(MyApplet.this);
            path = file.getSelectedFile().getPath();
            pic = new ImageIcon(path);
            lab.setIcon(pic);

            temp = new File(path.trim());
            fileName = temp.getName();
            text.setText(fileName);

            lab.setHorizontalAlignment(SwingConstants.CENTER);

        }
    }
    public class ML_exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public class ML_paint implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String shape = shapeChooser.getSelectedItem();
            String color = colorChooser.getSelection().getActionCommand();
            lab.setIcon(new MyShape(shape, color));
            //text.setText(color);
            lab.setHorizontalAlignment(SwingConstants.CENTER);

        }
    }

    public class ML_clear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            lab.setIcon(null);
            text.setText(null);


        }
    }

}

package db.mframe;

import db.mysql.GeneratorProcess;
import db.mysql.env.RuntimeEnv;
import db.mysql.process.CallBackFunction;
import db.mysql.process.MysqlCommon;
import db.mysql.process.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.SQLException;

/**
 * db.frame
 *
 * @author ASUS
 * @date 2017/10/21 10:15
 */
public class MainFrame extends JFrame{

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public String selectFilePath ;

    public CallBackFunction generateCallBack;

    JComboBox  dbSelect = new JComboBox();

    JButton cancelConnectBtn = new JButton("取消连接");

    JTextField search = new JTextField();

    JTextField modelWorkOut=new JTextField();

    JTextField mapperWorkOut=new JTextField();

    JTextField xmlWorkOut=new JTextField();


    JTextField modelField=new JTextField();

    JTextField modelOut=new JTextField();

    JTextField mapperField=new JTextField();

    JTextField mapperOut=new JTextField();

    JTextField xmlField=new JTextField();

    JTextField xmlOut=new JTextField();

    JTextField author = new JTextField();

    DefaultListModel<String> defaultListModel= new DefaultListModel();

    JList<String> jList = new JList<>(defaultListModel);

    JScrollPane  jScrollPane = new JScrollPane (jList);

    JCheckBox packgeFiler=new JCheckBox("生成包文件夹");

    JCheckBox sperate=new JCheckBox("是否读写分离");

    JCheckBox underLineToCamel=new JCheckBox("下划线转驼峰");

    {
        dbSelect.addItem("mysql");
        dbSelect.addItem("postgres");
//        dbSelect.addItem("oracle");
        dbSelect.setSelectedItem(RuntimeEnv.pp.getDataBaseType());
        jScrollPane.setPreferredSize(new Dimension(150,252));
        modelField.setPreferredSize(new Dimension(150,26));
        search.setPreferredSize(new Dimension(150,26));
        modelOut.setPreferredSize(new Dimension(150,26));
        mapperField.setPreferredSize(new Dimension(150,26));
        mapperOut.setPreferredSize(new Dimension(150,26));
        xmlField.setPreferredSize(new Dimension(150,26));
        xmlOut.setPreferredSize(new Dimension(150,26));
        modelWorkOut.setPreferredSize(new Dimension(150,26));
        mapperWorkOut.setPreferredSize(new Dimension(150,26));
        xmlWorkOut.setPreferredSize(new Dimension(150,26));
        author.setPreferredSize(new Dimension(150,26));

    }


    private static Dimension titleSize ;

    private static Dimension inputSize;

    static {

        titleSize=new Dimension();
        titleSize.setSize(100,20);
        inputSize = new Dimension();
        inputSize.setSize(250,20);
    }

    public MainFrame (){
        this.setTitle("mybatis生成器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 985, 450);
        JPanel contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pane1=new JPanel();
        contentPane.add(pane1);
        JPanel pane2=new JPanel();
        contentPane.add(pane2);
        JPanel pane3=new JPanel();
        contentPane.add(pane3);


        JLabel label1=new JLabel("url：");
        JTextField textField1=new JTextField();
        textField1.setColumns(10);
        textField1.setText(RuntimeEnv.pp.getUrl());
        pane1.add(dbSelect);
        pane1.add(label1);
        pane1.add(textField1);

        JLabel schemaLabel=new JLabel("schema：");
        JTextField schemaField=new JTextField();
        schemaField.setColumns(10);
        schemaField.setText(RuntimeEnv.pp.getSchema());
        pane1.add(schemaLabel);
        pane1.add(schemaField);

        JLabel label2=new JLabel("user：");
        JTextField textField2=new JTextField();
        textField2.setColumns(10);
        textField2.setText(RuntimeEnv.pp.getUser());
        pane1.add(label2);
        pane1.add(textField2);
        JLabel label3=new JLabel("password：");
        JTextField textField3=new JTextField();
        textField3.setColumns(10);
        textField3.setText(RuntimeEnv.pp.getPassword());
        pane1.add(label3);
        pane1.add(textField3);
        JButton connectBtn = new JButton("连接");
        connectBtn.addActionListener(actionEvent->{
            RuntimeEnv.pp.setDataBaseType(dbSelect.getSelectedItem().toString());
            RuntimeEnv.pp.setUrl(textField1.getText());
            RuntimeEnv.pp.setUser(textField2.getText());
            RuntimeEnv.pp.setSchema(schemaField.getText());
            RuntimeEnv.pp.setPassword(textField3.getText());
            RuntimeEnv.storage();
            try {
                RuntimeEnv.mc = new MysqlCommon();
                connectBtn.setEnabled(false);
                connectBtn.setText("连接成功");
                cancelConnectBtn.setEnabled(true);
                textField1.setEnabled(false);
                schemaField.setEnabled(false);
                textField2.setEnabled(false);
                textField3.setEnabled(false);
                pane2.setVisible(true);
                pane3.setVisible(true);
                System.out.println("connect");
                final Integer[] selected = {null};
                int index = 0;
                defaultListModel.removeAllElements();
                for (String table : RuntimeEnv.mc.getTableList()) {
                    defaultListModel.addElement(table);
                    if (table.equals(RuntimeEnv.pp.getTableName())) {
                        selected[0] = index;

                    }
                    index++;
                }
                if (selected[0]!=null) {
                    jList.setSelectedIndex(selected[0]);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"连接数据库失败:"+e.getMessage());
                e.printStackTrace();
            }
        });
        pane1.add(connectBtn);

        cancelConnectBtn.setEnabled(false);
        cancelConnectBtn.addActionListener(actionEvent->{
            connectBtn.setEnabled(true);
            connectBtn.setText("连接");
            cancelConnectBtn.setEnabled(false);
            textField1.setEnabled(true);
            schemaField.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            pane2.setVisible(false);
            pane3.setVisible(false);
        });
        pane1.add(cancelConnectBtn);
        this.setVisible(true);
        pane2.setVisible(false);

        JPanel innerJp = new JPanel();
        innerJp.setLayout(new FlowLayout(0));
        search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String tableName=search.getText();
                defaultListModel.removeAllElements();
                try {
                    RuntimeEnv.mc.getTableList().stream().filter(m->m.contains(tableName))
                        .forEach(m->defaultListModel.addElement(m));
                } catch (SQLException | ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(null,"生成失败："+e1.getMessage());
                    e1.printStackTrace();
                }
                jList.setSelectedIndex(0);
            }
        });

        jList.addListSelectionListener(accessibleContext->{
            if (jList.getSelectedValue()!=null) {
                String content = StringUtils.underLineToCamel(jList.getSelectedValue(),false);
                modelField.setText(content);
                mapperField.setText(content);
                xmlField.setText(content);
            }
        });
        search.setText(RuntimeEnv.pp.getTableName());
        innerJp.add(search);
        innerJp.add(jScrollPane);
        pane2.add(innerJp);



        JPanel innerJp1 = new JPanel();
        innerJp1.setLayout(new GridLayout(4,1,5,5));
        pane2.add(innerJp1);
        modelWorkOut.setText(RuntimeEnv.pp.getModelWorkSpace());
        innerJp1.add(modelWorkOut);
        JButton modelWorkBtn = new JButton("选择model工作目录");
        modelWorkBtn.addActionListener(actionEvent->{
            JFileChooser jfc = selectFilePath ==null?new JFileChooser():new JFileChooser(new File(selectFilePath));
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION ){
                //解释下这里,弹出个对话框,可以选择要上传的文件,如果选择了,就把选择的文件的绝对路径打印出来,有了绝对路径,通过JTextField的settext就能设置进去了,那个我没写
                RuntimeEnv.pp.setModelWorkSpace(jfc.getSelectedFile().getAbsolutePath().replaceAll("\\\\","/"));
                modelWorkOut.setText(RuntimeEnv.pp.getModelWorkSpace());
            }
        });
        innerJp1.add(modelWorkBtn);


        modelField.setText(RuntimeEnv.pp.getClassName());
        modelOut.setText(RuntimeEnv.pp.getPackageModel());
        innerJp1.add(modelField);
        innerJp1.add(modelOut);

        JPanel innerJp2 = new JPanel();
        innerJp2.setLayout(new GridLayout(4,1,5,5));
        pane2.add(innerJp2);
        mapperWorkOut.setText(RuntimeEnv.pp.getMapperWorkSpace());
        innerJp2.add(mapperWorkOut);
        JButton mapperWorkBtn = new JButton("选择mapper工作目录");
        mapperWorkBtn.addActionListener(actionEvent->{
            JFileChooser jfc = selectFilePath ==null?new JFileChooser():new JFileChooser(new File(selectFilePath));
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION ){
                //解释下这里,弹出个对话框,可以选择要上传的文件,如果选择了,就把选择的文件的绝对路径打印出来,有了绝对路径,通过JTextField的settext就能设置进去了,那个我没写
                RuntimeEnv.pp.setMapperWorkSpace(jfc.getSelectedFile().getAbsolutePath().replaceAll("\\\\","/"));
                mapperWorkOut.setText(RuntimeEnv.pp.getMapperWorkSpace());
            }
        });
        innerJp2.add(mapperWorkBtn);

        mapperField.setText(RuntimeEnv.pp.getMapperName());
        mapperOut.setText(RuntimeEnv.pp.getPackageMapper());
        innerJp2.add(mapperField);
        innerJp2.add(mapperOut);

        JPanel innerJp3 = new JPanel();
        innerJp3.setLayout(new GridLayout(4,1,5,5));
        pane2.add(innerJp3);

        xmlWorkOut.setText(RuntimeEnv.pp.getXmlWorkSpace());
        innerJp3.add(xmlWorkOut);
        JButton xmlWorkBtn = new JButton("选择xml工作目录");
        xmlWorkBtn.addActionListener(actionEvent->{
            JFileChooser jfc = selectFilePath ==null?new JFileChooser():new JFileChooser(new File(selectFilePath));
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION ){
                //解释下这里,弹出个对话框,可以选择要上传的文件,如果选择了,就把选择的文件的绝对路径打印出来,有了绝对路径,通过JTextField的settext就能设置进去了,那个我没写
                RuntimeEnv.pp.setXmlWorkSpace(jfc.getSelectedFile().getAbsolutePath().replaceAll("\\\\","/"));
                xmlWorkOut.setText(RuntimeEnv.pp.getXmlWorkSpace());
            }
        });
        innerJp3.add(xmlWorkBtn);

        xmlField.setText(RuntimeEnv.pp.getMapperXmlName());
        xmlOut.setText(RuntimeEnv.pp.getPackageXmlMapper());
        innerJp3.add(xmlField);
        innerJp3.add(xmlOut);

        modelField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                mapperField.setText(modelField.getText());
                xmlField.setText(modelField.getText());
            }
        });
        mapperField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                xmlField.setText(mapperField.getText());
            }
        });
        pane3.setVisible(false);

        packgeFiler.setSelected(RuntimeEnv.pp.isProducePackFile());
        packgeFiler.addActionListener(actionEvent->{
            RuntimeEnv.pp.setProducePackFile(packgeFiler.getSelectedObjects()!=null);
        });

        pane3.add(packgeFiler);

        underLineToCamel.setSelected(RuntimeEnv.pp.isUnderlineToCamel());
        underLineToCamel.addActionListener(actionEvent->{
            RuntimeEnv.pp.setUnderlineToCamel(underLineToCamel.getSelectedObjects()!=null);
        });

        pane3.add(underLineToCamel);

        sperate.setSelected(RuntimeEnv.pp.isSperateRead());
        sperate.addActionListener(actionEvent->{
            RuntimeEnv.pp.setSperateRead(sperate.getSelectedObjects()!=null);
        });

        pane3.add(sperate);

        author.setText(RuntimeEnv.pp.getAuthor());
        pane3.add(author);

        JButton generate = new JButton("生成模板");
        generate.addActionListener(actionEvent->{
            boolean pack=packgeFiler.getSelectedObjects()!=null;
            RuntimeEnv.pp.setProducePackFile(pack);
            RuntimeEnv.pp.setModelWorkSpace(modelWorkOut.getText());
            RuntimeEnv.pp.setMapperWorkSpace(mapperWorkOut.getText());
            RuntimeEnv.pp.setXmlWorkSpace(xmlWorkOut.getText());
            RuntimeEnv.pp.setTableName(jList.getSelectedValue());
            RuntimeEnv.pp.setClassName(modelField.getText());
            RuntimeEnv.pp.setPackageModel(modelOut.getText());
            RuntimeEnv.pp.setModelOutPath(modelWorkOut.getText()+(pack?"/"+ RuntimeEnv.pp.getPackageModel().replaceAll("\\.","/"):""));
            RuntimeEnv.pp.setMapperName(mapperField.getText());
            RuntimeEnv.pp.setPackageMapper(mapperOut.getText());
            RuntimeEnv.pp.setMapperOutPath(mapperWorkOut.getText()+(pack?"/"+ RuntimeEnv.pp.getPackageMapper().replaceAll("\\.","/"):""));
            RuntimeEnv.pp.setMapperXmlName(xmlField.getText());
            RuntimeEnv.pp.setPackageXmlMapper(xmlOut.getText());
            RuntimeEnv.pp.setMapperXmlOutPath(xmlWorkOut.getText()+(pack?"/"+ RuntimeEnv.pp.getPackageXmlMapper().replaceAll("\\.","/"):""));
            RuntimeEnv.pp.setAuthor(author.getText());
            try {
                GeneratorProcess.generate();
                RuntimeEnv.storage();
                if (generateCallBack!=null){
                    generateCallBack.call();
                }
                JOptionPane.showMessageDialog(null,"生成成功");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"生成失败："+e.getMessage());
                e.printStackTrace();
            }

        });
        pane3.add(generate);

        AutoComplate.setupAutoComplete(mapperOut,mapperWorkOut);
        AutoComplate.setupAutoComplete(xmlOut,xmlWorkOut);
        AutoComplate.setupAutoComplete(modelOut,modelWorkOut);
    }

}

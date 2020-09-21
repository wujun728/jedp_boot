package com.fly.code;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fly.code.process.RefreshDataProgress;
import com.fly.code.process.RunIbatorProgress;

/**
 * 图形化界面交互方式代码生成器
 * 
 * @author 00fly
 * @version [版本号, 2017-5-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CodeMaker
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeMaker.class);
    
    Display display;
    
    Shell shell;
    
    private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    
    // 表名列表
    private Set<String> tableNameSet = new TreeSet<>();
    
    // 界面组件
    private Combo dbTypeName;
    
    private Text dataBaseURL;
    
    private Text userName;
    
    private Text passWord;
    
    private Text filtertext;
    
    private List list;
    
    private Button overwrite;
    
    private Text packtext;
    
    private Text prefixtext;
    
    private Text projParentPath;
    
    // beanID , DAO
    Map<String, String> beanIdDAOmap = new TreeMap<>();
    
    public CodeMaker()
    {
        super();
        display = new Display();
        shell = new Shell(display, SWT.MIN | SWT.CLOSE);
        InputStream is = this.getClass().getResourceAsStream("/img/icon.gif");
        if (is != null)
        {
            shell.setImage(new Image(display, is));
        }
        shell.setText("springmvc_ibatis_plus代码创建工具 V2.0");
        shell.setSize(540, 680);
        Rectangle screeRec = display.getBounds();
        Rectangle shellRec = shell.getBounds();
        if (shellRec.height > screeRec.height)
        {
            shellRec.height = screeRec.height;
        }
        if (shellRec.width > screeRec.width)
        {
            shellRec.width = screeRec.width;
        }
        shell.setLocation((screeRec.width - shellRec.width) / 2, (screeRec.height - shellRec.height) / 2);
        addMenu();
        setContent();
        shell.open();
        try
        {
            File file = new File("default.ini");
            InputStream fis;
            if (file.exists())
            {
                fis = new FileInputStream(file);
            }
            else
            {
                // 定位jar内资源
                fis = this.getClass().getResourceAsStream("/default.ini");
            }
            init(fis);
            MessageDialog.openInformation(shell, "确认", "加载默认配置文件成功！");
        }
        catch (IOException e)
        {
            MessageDialog.openError(shell, "错误", e.getMessage());
        }
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }
        display.dispose();
    }
    
    private void init(InputStream is)
        throws IOException
    {
        Properties config = new Properties();
        config.load(is);
        String driver = config.getProperty("driver");
        if (MYSQL_DRIVER.equals(driver))
        {
            dbTypeName.select(1);
        }
        else
        {
            dbTypeName.select(0);
        }
        dataBaseURL.setText(config.getProperty("url"));
        userName.setText(config.getProperty("username"));
        passWord.setText(config.getProperty("password"));
        packtext.setText(config.getProperty("packtext"));
        String protext = config.getProperty("protext", "");
        if (StringUtils.isEmpty(protext) || !new File(protext).exists())
        {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            projParentPath.setText(fsv.getHomeDirectory().getPath() + File.separatorChar);
        }
        else
        {
            projParentPath.setText(protext);
        }
        prefixtext.setText(config.getProperty("prefixtext", ""));
        overwrite.setSelection("true".equals(config.getProperty("overwrite", "false")));
        filtertext.setText("");
        tableNameSet.clear();
        list.removeAll();
        IOUtils.closeQuietly(is);
    }
    
    private void addMenu()
    {
        Menu m = new Menu(shell, SWT.BAR);
        // create a file menu and add an exit item
        MenuItem file = new MenuItem(m, SWT.CASCADE);
        file.setText("配置文件(&F)");
        file.setAccelerator(SWT.CTRL + 'F');
        Menu filemenu = new Menu(shell, SWT.DROP_DOWN);
        file.setMenu(filemenu);
        MenuItem openMenuItem = new MenuItem(filemenu, SWT.CASCADE);
        openMenuItem.setText("加载(&O)");
        openMenuItem.setAccelerator(SWT.CTRL + 'O');
        openMenuItem.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
                fileDialog.setText("请选择配置文件");
                fileDialog.setFilterExtensions(new String[] {"*.ini"});
                String filePath = fileDialog.open();
                if (filePath == null)
                {
                    return;
                }
                try
                {
                    InputStream is = new FileInputStream(filePath);
                    init(is);
                }
                catch (IOException e1)
                {
                    MessageDialog.openError(shell, "警告", "加载配置文件失败！");
                }
            }
        });
        MenuItem saveMenuItem = new MenuItem(filemenu, SWT.CASCADE);
        saveMenuItem.setText("保存(&S)");
        saveMenuItem.setAccelerator(SWT.CTRL + 'S');
        saveMenuItem.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
                fileDialog.setText("选择文件");
                fileDialog.setFilterPath(new File(" ").getAbsolutePath().trim());
                fileDialog.setFileName("default.ini");
                fileDialog.setFilterExtensions(new String[] {"*.ini"});
                String fileName = fileDialog.open();
                if (fileName == null)
                {
                    return;
                }
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)))))
                {
                    String driver = (dbTypeName.getSelectionIndex() == 0 ? ORACLE_DRIVER : MYSQL_DRIVER);
                    String dburl = dataBaseURL.getText().trim();
                    String username = userName.getText().trim();
                    String password = passWord.getText().trim();
                    String packText = packtext.getText().trim();
                    String prefixText = prefixtext.getText().trim();
                    String proText = projParentPath.getText().trim();
                    boolean overWrite = overwrite.getSelection();
                    writer.newLine();
                    writer.write("driver=" + driver);
                    writer.newLine();
                    writer.write("url=" + dburl);
                    writer.newLine();
                    writer.write("username=" + username);
                    writer.newLine();
                    writer.write("password=" + password);
                    writer.newLine();
                    writer.write("packtext=" + packText);
                    writer.newLine();
                    writer.write("prefixtext=" + prefixText);
                    writer.newLine();
                    writer.write("overwrite=" + overWrite);
                    writer.newLine();
                    writer.write("protext=" + proText.replace("\\", "\\\\"));
                    writer.newLine();
                    writer.flush();
                    MessageDialog.openInformation(shell, "确认", "保存配置文件成功！ \n文件位置：" + fileName);
                }
                catch (IOException e)
                {
                    MessageDialog.openError(shell, "警告", "保存配置文件失败！");
                }
            }
        });
        new MenuItem(filemenu, SWT.SEPARATOR);
        MenuItem exitMenuItem = new MenuItem(filemenu, SWT.PUSH);
        exitMenuItem.setText("退出(&X)");
        exitMenuItem.setAccelerator(SWT.CTRL + 'X');
        exitMenuItem.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                if (MessageDialog.openConfirm(shell, "确认", "您真的要退出吗?"))
                {
                    shell.dispose();
                }
            }
        });
        MenuItem help = new MenuItem(m, SWT.CASCADE);
        help.setText("帮助(&H)");
        help.setAccelerator(SWT.CTRL + 'H');
        Menu helpmenu = new Menu(shell, SWT.DROP_DOWN);
        help.setMenu(helpmenu);
        MenuItem useMenuItem = new MenuItem(helpmenu, SWT.PUSH);
        useMenuItem.setText("使用指南(&U)");
        useMenuItem.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                MessageDialog.openInformation(shell,
                    "使用指南",
                    new StringBuilder("请按以下顺序操作：").append("\n 1. 配置数据库参数。")
                        .append("\n 2. 选择需要生成java源码的数据库表。")
                        .append("\n 3. 填写源码包名，选择java文件输出目录。")
                        .append("\n 4. 生成相关源代码、配置文件以及运行所需jar包。")
                        .append("\n 5. 新建Java工程，拷贝全部生成的文件，将lib设为运行jar。")
                        .append("\n 6. 编译运行junit测试用例。")
                        .toString());
            }
        });
        MenuItem infoItem = new MenuItem(helpmenu, SWT.PUSH);
        infoItem.setText("重要说明(&O)");
        infoItem.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                MessageDialog.openWarning(shell,
                    "重要问题说明",
                    new StringBuilder("\n  1. 当表主键为序列时，需要在SqlMap.xml配置文件 insert、insertSelective 处添加类似如下代码").append("\n     Oracle修改方式：")
                        .append("\n     <selectKey resultClass = \"int\" keyProperty=\"id\" >")
                        .append("\n        select seq_xxxx.nextval from dual")
                        .append("\n     </selectKey>\n")
                        .append("\n     Mysql修改方式：")
                        .append("\n     <selectKey resultClass = \"int\" keyProperty=\"id\" >")
                        .append("\n        select @@identity as id")
                        .append("\n     </selectKey>")
                        .append("\n\n  2. junit测试用例有些无法作成通用代码，一部分运行结果需看具体情况而定，\n     此部分代码需手工修改。")
                        .toString());
            }
        });
        new MenuItem(helpmenu, SWT.SEPARATOR);
        MenuItem aboutMenuItem = new MenuItem(helpmenu, SWT.PUSH);
        aboutMenuItem.setText("关于本工具(&A)");
        aboutMenuItem.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                MessageDialog.openInformation(shell, "关于本工具", "在 ibator1.2.1 源码基础修改的代码创建工具。 \n\n支持DAO、model、Ibatis sqlMap XML 自动创建。\n\n00fly 于 2017年5月。");
            }
        });
        shell.setMenuBar(m);
    }
    
    private void setContent()
    {
        Group group1 = new Group(shell, SWT.NONE);
        group1.setText("数据库设置");
        group1.setBounds(10, 10, 510, 170);
        Label dataBaseLabel = new Label(group1, SWT.NONE);
        dataBaseLabel.setText("数据库:");
        dataBaseLabel.setBounds(20, 30, 60, 20);
        dbTypeName = new Combo(group1, SWT.DROP_DOWN | SWT.READ_ONLY);
        dbTypeName.setBounds(80, 30, 100, 65);
        String[] items = {" Oracle", " MySql"};
        dbTypeName.setItems(items);
        dbTypeName.select(0);
        dbTypeName.addModifyListener((ModifyEvent event) -> {
            int index = dbTypeName.getSelectionIndex();
            switch (index)
            {
                case 0:
                    dataBaseURL.setText("jdbc:oracle:thin:@hostname:1521:SID");
                    break;
                case 1:
                default:
                    dataBaseURL.setText("jdbc:mysql://127.0.0.1:3306/dbname");
                    break;
            }
            tableNameSet.clear();
            list.removeAll();
        });
        Label sourceLabel = new Label(group1, SWT.NONE);
        sourceLabel.setText("   URL:");
        sourceLabel.setBounds(20, 70, 60, 20);
        dataBaseURL = new Text(group1, SWT.BORDER);
        dataBaseURL.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        dataBaseURL.setBounds(80, 70, 350, 20);
        dataBaseURL.setText("jdbc:oracle:thin:@hostname:1521:SID");
        Label user = new Label(group1, SWT.NONE);
        user.setText("用户名:");
        user.setBounds(20, 100, 60, 20);
        userName = new Text(group1, SWT.BORDER);
        userName.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        userName.setBounds(80, 100, 100, 20);
        Label pass = new Label(group1, SWT.NONE);
        pass.setText("密  码:");
        pass.setBounds(20, 130, 50, 20);
        passWord = new Text(group1, SWT.BORDER);
        passWord.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        passWord.setEchoChar('●');
        passWord.setBounds(80, 130, 100, 20);
        Button test = new Button(group1, SWT.PUSH);
        test.setText(" 测 试 ");
        test.setBounds(190, 130, 60, 20);
        test.addSelectionListener(new DataListener());
        Group group2 = new Group(shell, SWT.NONE);
        group2.setText("数据表选择");
        group2.setBounds(10, 190, 510, 260);
        Label filter = new Label(group2, SWT.NONE);
        filter.setText("选择器:");
        filter.setBounds(20, 30, 60, 20);
        filtertext = new Text(group2, SWT.BORDER);
        filtertext.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        filtertext.setBounds(80, 30, 260, 20);
        filtertext.setToolTipText("输入空格表示条件 或 ");
        filtertext.addModifyListener(event -> {
            String nameParam = filtertext.getText().toLowerCase().trim();
            if (StringUtils.isEmpty(nameParam))
            {
                return;
            }
            while (nameParam.contains("  "))
            {
                nameParam = nameParam.replace("  ", " ");
            }
            list.removeAll();
            String[] params = nameParam.split(" ");
            // Arr->List->Set
            Set<String> set = new HashSet<>(Arrays.asList(params));
            for (String tableName : tableNameSet)
            {
                for (String param : set)
                {
                    if (tableName.contains(param) || tableName.contains(param.toUpperCase()))
                    {
                        list.add(tableName);
                        break;
                    }
                }
            }
        });
        Button clear = new Button(group2, SWT.PUSH);
        clear.setText(" 清  除 ");
        clear.setBounds(350, 30, 60, 20);
        clear.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent selectionevent)
            {
                filtertext.setText("");
                java.util.List<String> l = new ArrayList<>(tableNameSet);
                list.setItems(l.toArray(new String[0]));
            }
        });
        Label tab = new Label(group2, SWT.NONE);
        tab.setText("表  名:");
        tab.setBounds(20, 130, 60, 20);
        list = new List(group2, SWT.BORDER | SWT.V_SCROLL | SWT.SIMPLE | SWT.MULTI);
        list.setBounds(80, 60, 330, 190);
        list.setToolTipText("选择需要生成代码的数据库表,支持多选!");
        Button refresh = new Button(group2, SWT.PUSH);
        refresh.setText("刷  新");
        refresh.setBounds(430, 80, 60, 30);
        refresh.addSelectionListener(new DataListener());
        Button selectAll = new Button(group2, SWT.PUSH);
        selectAll.setText("全  选");
        selectAll.setBounds(430, 120, 60, 30);
        selectAll.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                list.selectAll();
            }
        });
        Button selectNone = new Button(group2, SWT.PUSH);
        selectNone.setText("不  选");
        selectNone.setBounds(430, 160, 60, 30);
        selectNone.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                list.deselectAll();
            }
        });
        Button selectReverse = new Button(group2, SWT.PUSH);
        selectReverse.setText("反  选");
        selectReverse.setBounds(430, 200, 60, 30);
        selectReverse.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                int count = list.getItemCount();
                for (int i = 0; i < count; i++)
                {
                    if (list.isSelected(i))
                    {
                        list.deselect(i);
                    }
                    else
                    {
                        list.select(i);
                    }
                }
            }
        });
        Group group3 = new Group(shell, SWT.NONE);
        group3.setBounds(10, 460, 510, 160);
        group3.setText("工程设置");
        Label pack = new Label(group3, SWT.NONE);
        pack.setText("源码包名:");
        pack.setBounds(20, 30, 60, 20);
        packtext = new Text(group3, SWT.BORDER);
        packtext.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        packtext.setBounds(80, 30, 160, 20);
        packtext.setText("com.fly.demo");
        packtext.setToolTipText("dao、map、model 文件存放的源码路径， 类似于 com.fly.demo");
        packtext.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent focusevent)
            {
                String text = packtext.getText().toLowerCase().trim();
                packtext.setText(text);
            }
        });
        Label prefix = new Label(group3, SWT.NONE);
        prefix.setText("去除表名前缀:");
        prefix.setBounds(250, 30, 75, 20);
        prefixtext = new Text(group3, SWT.BORDER);
        prefixtext.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        prefixtext.setBounds(340, 30, 70, 20);
        overwrite = new Button(group3, SWT.CHECK);
        overwrite.setText("覆盖代码");
        overwrite.setSelection(true);
        overwrite.setBounds(430, 30, 67, 20);
        overwrite.setToolTipText("选中后，若原工程目录存在代码则覆盖，不会对原来的代码做备份操作");
        Label pro = new Label(group3, SWT.NONE);
        pro.setText("工程位置:");
        pro.setBounds(20, 60, 60, 20);
        projParentPath = new Text(group3, SWT.BORDER);
        projParentPath.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        projParentPath.setBounds(80, 60, 330, 20);
        projParentPath.setText(new File(" ").getAbsolutePath().trim());
        projParentPath.setEditable(false);
        projParentPath.setToolTipText("工程位置文件存放的路径，一般选为Java工程目录，默认值为当前目录");
        Button bakBrowse = new Button(group3, SWT.PUSH);
        bakBrowse.setText("选  择");
        bakBrowse.setBounds(430, 60, 60, 20);
        bakBrowse.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                DirectoryDialog dialog = new DirectoryDialog(shell);
                dialog.setFilterPath(projParentPath.getText());
                String fileName = dialog.open();
                if (fileName != null)
                {
                    if (fileName.endsWith("\\"))
                    {
                        projParentPath.setText(fileName);
                    }
                    else
                    {
                        projParentPath.setText(fileName + "\\");
                    }
                }
            }
        });
        Button run = new Button(group3, SWT.PUSH);
        run.setText("生 成 代 码");
        run.setBounds(200, 100, 140, 40);
        run.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                String dburl = dataBaseURL.getText().trim();
                if (dburl.length() <= 0)
                {
                    MessageDialog.openError(shell, "警告", "数据库地址不可为空!");
                    dataBaseURL.setFocus();
                    return;
                }
                String username = userName.getText().trim();
                if (username.length() <= 0)
                {
                    MessageDialog.openError(shell, "警告", "用户名不可为空!");
                    userName.setFocus();
                    return;
                }
                String password = passWord.getText().trim();
                if (password.length() <= 0)
                {
                    MessageDialog.openError(shell, "警告", "密码不可为空!");
                    passWord.setFocus();
                    return;
                }
                int count = list.getSelectionCount();
                if (count < 1)
                {
                    MessageDialog.openError(shell, "警告", "请选择数据库表!");
                    list.setFocus();
                    return;
                }
                String packName = packtext.getText().toLowerCase().trim();
                if (packName.length() <= 0)
                {
                    MessageDialog.openError(shell, "警告", "源码包名不可为空!");
                    packtext.setFocus();
                    return;
                }
                if (!Pattern.matches("\\w+(\\.\\w+)+", packName))
                {
                    MessageDialog.openError(shell, "警告", "源码包名不符合规范,请重新填写!");
                    packtext.setFocus();
                    return;
                }
                String projectPath = projParentPath.getText().trim();
                if (projectPath.length() <= 0)
                {
                    MessageDialog.openError(shell, "警告", "工程位置不可为空!");
                    projParentPath.setFocus();
                    return;
                }
                String driver;
                if (0 == dbTypeName.getSelectionIndex())
                {
                    driver = ORACLE_DRIVER;
                }
                else
                {
                    driver = MYSQL_DRIVER;
                }
                String projDir = projectPath + "project\\";
                File projDirFile = new File(projDir);
                if (!overwrite.getSelection())
                {
                    int i = 1;
                    while (projDirFile.exists())
                    {
                        projDir = projectPath + "project_" + (i++) + "\\";
                        projDirFile = new File(projDir);
                    }
                }
                else
                {
                    try
                    {
                        // 清空目录下的文件
                        if (projDirFile.exists())
                        {
                            FileUtils.cleanDirectory(projDirFile);
                        }
                    }
                    catch (IOException e2)
                    {
                    }
                }
                String prefix = prefixtext.getText().trim();
                try
                {
                    // 在当前目录，创建并运行脚本
                    IRunnableWithProgress runProgress =
                        new RunIbatorProgress(driver, dburl, username, password, packName, projDir, list.getSelection(), prefix);
                    new ProgressMonitorDialog(shell).run(true, false, runProgress);
                }
                catch (InvocationTargetException e)
                {
                    LOGGER.error(e.getMessage(), e);
                    MessageDialog.openError(shell, "警告", "生成代码失败!" + e.getMessage());
                    return;
                }
                catch (InterruptedException e)
                {
                    MessageDialog.openInformation(shell, "确认", "生成代码被用户取消!");
                    return;
                }
                catch (Exception e)
                {
                    LOGGER.error(e.getMessage(), e);
                    MessageDialog.openError(shell, "警告", "生成代码出错!" + e.getCause());
                    return;
                }
                StringBuilder desc = new StringBuilder("\n生成java源码的数据库表共").append(list.getSelection().length).append(" 张！");
                MessageDialog.openInformation(shell, "确认", "生成代码成功！ \n文件位置：" + projDir + desc);
                if (MessageDialog.openConfirm(shell, "查看项目代码", "处理完成，是否直接查看生成的代码?"))
                {
                    try
                    {
                        Desktop.getDesktop().open(new File(projDir));
                    }
                    catch (IOException e)
                    {
                    }
                }
            }
        });
        
        Label link = new Label(group3, SWT.NONE);
        link.setBounds(20, 130, 160, 20);
        link.setText("Java 爱码少年");
        Color color = new Color(display, 0, 0, 255);
        link.setForeground(color);
        Cursor cursor = new Cursor(display, SWT.CURSOR_HAND);
        link.setCursor(cursor);
        link.setToolTipText("双击直达技术讨论群!");
        link.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseDoubleClick(MouseEvent e)
            {
                try
                {
                    Runtime.getRuntime().exec("cmd.exe /c start https://jq.qq.com/?_wv=1027^&k=4AuWuZu");
                }
                catch (Exception ex)
                {
                }
            }
        });
    }
    
    class DataListener extends SelectionAdapter
    {
        @Override
        public void widgetSelected(SelectionEvent event)
        {
            String dburl = dataBaseURL.getText().trim();
            if (dburl.length() <= 0)
            {
                MessageDialog.openError(shell, "警告", "数据库地址不可为空!");
                dataBaseURL.setFocus();
                return;
            }
            String username = userName.getText().trim();
            if (username.length() <= 0)
            {
                MessageDialog.openError(shell, "警告", "用户名不可为空!");
                userName.setFocus();
                return;
            }
            String password = passWord.getText().trim();
            if (password.length() <= 0)
            {
                MessageDialog.openError(shell, "警告", "密码不可为空!");
                passWord.setFocus();
                return;
            }
            String driver;
            if (0 == dbTypeName.getSelectionIndex())
            {
                driver = ORACLE_DRIVER;
            }
            else
            {
                driver = MYSQL_DRIVER;
            }
            try
            {
                IRunnableWithProgress runnable = new RefreshDataProgress(driver, dburl, username, password, tableNameSet);
                new ProgressMonitorDialog(shell).run(true, false, runnable);
                filtertext.setText("");
                list.removeAll();
                // set->List->Arr
                java.util.List<String> l = new ArrayList<>(tableNameSet);
                list.setItems(l.toArray(new String[0]));
            }
            catch (InvocationTargetException e)
            {
                MessageDialog.openError(shell, "警告", e.getMessage());
            }
            catch (InterruptedException e)
            {
                MessageDialog.openInformation(shell, "Cancelled", "刷新操作被用户取消！");
            }
        }
    }
    
    /**
     * main
     * 
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        new CodeMaker();
    }
}

package db.mframe;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * db.mframe
 *
 * @author mymx.jlh
 * @date 2017/11/30 19:26
 */
public class AutoComplate {
    public static void setupAutoComplete(final JTextField txtInput,JTextField workPath) {
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<String> items = new ArrayList<>();
        updateTip(items,workPath.getText(),txtInput,null);
        final JComboBox cbInput = new JComboBox(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(cbInput, false);
        for (String item : items) {
            model.addElement(item);
        }
        cbInput.setSelectedItem(null);
        cbInput.addActionListener(e -> {
            if (!isAdjusting(cbInput)) {
                if (cbInput.getSelectedItem() != null) {
                    txtInput.setText(cbInput.getSelectedItem().toString());
                }
            }
        });

        txtInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (txtInput.getText().equals("")||e.getKeyChar()=='.' || e.getKeyCode()== KeyEvent.VK_BACK_SPACE){
                    updateTip(items,workPath.getText(),txtInput,e.getKeyChar());
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtInput.setText(txtInput.getText().substring(0,txtInput.getText().lastIndexOf(".")+1)+cbInput.getSelectedItem().toString());
                        cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
            }
        });
        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(cbInput, true);
                model.removeAllElements();
                String input = txtInput.getText();
                if (!input.isEmpty()) {
                    String inputs[]=input.split("\\.");
                    String lastInput = inputs[inputs.length-1];
                    if (input.lastIndexOf(".")==input.length()-1){
                        lastInput="";
                    }
                    for (String item : items) {
                        if (item.toLowerCase().startsWith(lastInput)) {
                            model.addElement(item);
                        }
                    }
                }
                cbInput.setPopupVisible(model.getSize() > 0);
                setAdjusting(cbInput, false);
            }
        });
        txtInput.setLayout(new BorderLayout());
        txtInput.add(cbInput, BorderLayout.SOUTH);
    }

    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }


    private static void updateTip(ArrayList<String> items,String workPath,JTextField txtInput,Character keycode){



        String packagePath=txtInput.getText();
        File file  = new File(workPath+"/"+ packagePath.replaceAll("\\.","/"));
        if (keycode!=null&&keycode=='.'&&!file.isDirectory()){
            while (items.size() > 0) {
                items.remove(items.size() - 1);
            }
            return;
        }

        while (file.exists()&&!file.isDirectory()){
            int index=packagePath.lastIndexOf(".");
            if (index==-1){
                index=0;
            }
            packagePath=packagePath.substring(0,index);
            file = new File(workPath+"/"+packagePath.replaceAll("\\.","/"));
        }
        if (file.isDirectory()) {
            while (items.size() > 0) {
                items.remove(items.size() - 1);
            }
            List<String> fileList=Stream.of(file.listFiles())
                    .filter(File::isDirectory)
                    .map(File::getName).collect(Collectors.toList());
            items.addAll(fileList);
        }
    }
}

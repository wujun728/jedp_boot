package com.BBS.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/12 10:05
 * @info Excel导入工具类
 */
public class ExcelImportUtil {

    private final static String excel12003L = ".xls"; //2003-版本的excel
    private final static String excel12007U = ".xlsx"; //2007+版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param is,fileName
     * @return
     * @throws IOException
     */
        public List<List<Object>>  getBankListByExcel(InputStream is,String filename) throws Exception {
            List<List<Object>> list = null;
            //创建工作薄
            Workbook work = this.getWorkbook(is, filename);

            if (work == null) {
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet = null; //工作区对象
            Row row = null;     //行对象
            Cell cell = null;   //列对象

            list  = new ArrayList<List<Object>>();

            //遍历Excel中所有的Sheet
            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                sheet = work.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                //遍历所有行
                //从sheet的第一行开始遍历，这里不能定义为0或1，
                // 因为不确定excel第一行就一定有数据，才用sheet本身的方法去寻找首行有数据的Row
                for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row==null) continue;

                    //遍历所有的列
                    List<Object> li = new ArrayList<Object>();
                    for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                        cell = row.getCell(k);
                        li.add(this.getCellValue(cell));
                    }
                    list.add(li);
                }
            }
            is.close();
            return list;
        }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param is,fileName
     * @return
     * @throws Exception
     */

    public Workbook getWorkbook(InputStream is, String filename) throws Exception {
        Workbook wb = null;
        String fileType = filename.substring(filename.lastIndexOf("."));
        if (excel12003L.equals(fileType)) {  //2003-版本一下的excel 文件
            wb = new HSSFWorkbook(is);
        } else if (excel12007U.equals(fileType)) {  //2007+版本一下的excel 文件
            wb = new XSSFWorkbook(is);
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:  //如果为字符类型的数据
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:  //如果为数值类型的数据
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;

    }

}

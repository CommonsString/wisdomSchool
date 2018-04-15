package com.cj.witcommon.utils.excle;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class exportExcelUtil {


    public static void main(String[] args) throws FileNotFoundException {

        exportExcelUtil e = new exportExcelUtil();
        e.testPOI();
    }

    /**
     * 二、生成xlsx格式的表格
     只需要修改
     1、XSSFWorkbook
     2、XSSFFont
     3、XSSFRichTextString
     4、XSSFCellStyle
     5、XSSFRow
     成为：HSSFWorkbook 、HSSFFont、HSSFRichTextString、HSSFCellStyle、HSSFRow即可
     * @param workbook
     * @param sheetNum
     * @param sheetTitle
     * @param headers
     * @param result
     * @param out
     * @throws Exception
     */


    public static void exportExcel(XSSFWorkbook workbook, int sheetNum,String sheetTitle, String[] headers, List<List<String>> result,
                            OutputStream out) throws Exception {
// 第一步，创建一个webbook，对应一个Excel以xsl为扩展名文件
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
//设置列宽度大小
        sheet.setDefaultColumnWidth((short) 20);
//第二步， 生成表格第一行的样式和字体
//        XSSFCellStyle style = workbook.createCellStyle();
// 设置这些样式
//        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
////        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
// 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
//设置字体所在的行高度
        font.setFontHeightInPoints((short) 20);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
// 把字体应用到当前的样式
//        style.setFont(font);
// 指定当单元格内容显示不下时自动换行
//        style.setWrapText(true);
// 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short) i);
//            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
// 第三步：遍历集合数据，产生数据行，开始插入数据
        if (result != null) {
            int index = 1;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    XSSFCell cell = row.createCell((int) cellIndex);
                    cell.setCellValue(str.toString());
                    cellIndex++;
                }
                index++;
            }
        }
    }
    public void testPOI(){
        exportExcelUtil gwu=null;
        String path=null;
        try {
            gwu=new exportExcelUtil();
            path="D:/file/测试Eexle导出.xlsx";
//1、输出的文件地址及名称
            OutputStream out = new FileOutputStream(path);
//2、sheet表中的标题行内容，需要输入excel的汇总数据
            String[] summary = { "系统名称", "活动名称","门店号" ,"日报时间","发券数量","使用数量"};
            List<List<String>> summaryData = new ArrayList<List<String>>();
            List<SummaryInfo> _listSummary=new ArrayList<SummaryInfo>();

            for (SummaryInfo sum:_listSummary) {
                List<String> rowData = new ArrayList<String>();
                rowData.add(sum.getXtmc());
                rowData.add(sum.getHdmc());
                rowData.add(sum.getMdh());
                rowData.add(sum.getCreatTime());
                rowData.add(String.valueOf(sum.getHandoutTotal()));
                rowData.add(String.valueOf(sum.getUseTotal()));
                summaryData.add(rowData);
            }

            String[] water = { "系统名称", "门店号" ,"门店名称","小票号","活动编号"
                    ,"活动名称","发券数量","商品条码","商品名称","购买数量"
                    ,"发券时间","分类代码","是否领赠","数据是否为真"};
            List<List<String>> waterData = new ArrayList<List<String>>();
            List<GenerWater> _listWater=new ArrayList<GenerWater>();
//            for (GenerWater wat:_listWater) {
//                List<String> rowData = new ArrayList<String>();
//                rowData.add(wat.getXtmc());
//                rowData.add(wat.getMdh());
//                rowData.add(wat.getMdmc());
//                rowData.add(wat.getXph());
//                rowData.add(wat.getHdbh());
//                rowData.add(wat.getHdmc());
//                rowData.add(wat.getFqsl());
//                rowData.add(wat.getSptm());
//                rowData.add(wat.getSpmc());
//                rowData.add(wat.getSl());
//                rowData.add(wat.getFqsj());
//                rowData.add(wat.getFldm());
//                rowData.add(wat.getSflq());
//                rowData.add(wat.getReal());
//                waterData.add(rowData);
//            }
//3、生成格式是xlsx可存储103万行数据，如果是xls则只能存不到6万行数据
            XSSFWorkbook workbook = new XSSFWorkbook();
//第一个表格内容
            gwu.exportExcel(workbook, 0, "日报汇总", summary, summaryData, out);
//第二个表格内容
            gwu.exportExcel(workbook, 1, "部分流水数据", water, waterData, out);
//将所有的数据一起写入，然后再关闭输入流。
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
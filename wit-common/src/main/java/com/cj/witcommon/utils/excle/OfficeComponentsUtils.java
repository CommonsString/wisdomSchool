package com.cj.witcommon.utils.excle;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: [cn.mos.framework.common.OfficeComponentsUtils.java]
 * @ClassName: [OfficeComponentsUtils]
 * @Description: [办公组件工具类]
 * @Author: [taolinxi@mosty.com.cn]
 * @CreateDate: [2017-10-12 下午12:04:59]
 * @UpdateUser: [taolinxi(如多次修改保留历史记录，增加修改记录)]
 * @UpdateDate: [2017-10-12 下午12:04:59，(如多次修改保留历史记录，增加修改记录)]
 * @UpdateRemark: [说明本次修改内容,(如多次修改保留历史记录，增加修改记录)]
 * @Version: [v1.0]
 */
public class OfficeComponentsUtils {

	public static final String SEPARATOR = ",";  
    public static final String CONNECTOR = "-";  
	
    /**
     * @Title: createExcel
     * @Description: 生成Excel
     * @param describes	标题行
     * @param fields	标题对应字段
     * @param list		数据集合
     * @return XSSFWorkbook
     */
    public static XSSFWorkbook createExcel(String[] describes, String[] fields, List<?> list){  
		XSSFWorkbook workBook = new XSSFWorkbook();  
		XSSFSheet sheet = workBook.createSheet(); 
		if (describes == null || describes.length == 0) {
			return workBook;
		}
		//格式1
		XSSFDataFormat format = workBook.createDataFormat();
		XSSFCellStyle style = workBook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		//格式2
		XSSFCellStyle normalStyle = workBook.createCellStyle();
		normalStyle.setDataFormat(format.getFormat("@"));
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		//格式3
		XSSFCellStyle dateStyle = workBook.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("yyyy-mm-dd"));
		//字体
		XSSFFont font = workBook.createFont(); 
		font.setFontHeightInPoints((short) 11);
		font.setFontName("微软雅黑");
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		//记录最大列宽
		int[] lengths = new int[describes.length];
		//设置标题行
		XSSFRow row = null;
		XSSFCell cell = null;
		row = sheet.createRow(0);
		row.setHeightInPoints(21);
		for (int i = 0; i < describes.length; i++) {
			String string = describes[i];
			cell = row.createCell(i);
			cell.setCellStyle(normalStyle);
			cell.setCellValue(string);
			lengths[i] = string.length();
		}
		if (fields == null || fields.length == 0 || list == null || list.isEmpty()) {
			return workBook;
		}
		//需要调用的方法
		Method[] methods = new Method[fields.length];
		for (int i = 0; i < fields.length; i++) {
			Object obj = list.get(0);
			try {
				methods[i] = obj.getClass().getMethod("get"+fields[i].substring(0, 1).toUpperCase()+fields[i].substring(1), null);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		//数据行
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i+1);
			for (int j = 0; j < fields.length; j++) {
				cell = row.createCell(j); 
				try {
					Object obj = methods[j].invoke(list.get(i), null);
					if (obj != null) {
						String string = String.valueOf(obj);
						cell.setCellValue(string);
						if (string.length() > lengths[j]) {
							lengths[j] = string.length();
						}
					} else {
						cell.setCellValue("");
					}
				} catch (Exception e) {
					e.printStackTrace();
					cell.setCellValue("");
				}
			}
		}
		//设置列宽
		for (int i = 0; i < lengths.length; i++) {
			//全部作为中文字符算*512
			sheet.setColumnWidth(i, lengths[i] * 512);
		}
		return workBook;
	}  
    
    public static XSSFWorkbook createExcelForArray(String[] describes, List<List<?>> list){  
    	XSSFWorkbook workBook = new XSSFWorkbook();  
    	XSSFSheet sheet = workBook.createSheet(); 
    	if (describes == null || describes.length == 0) {
    		return workBook;
    	}
    	//格式1
    	XSSFDataFormat format = workBook.createDataFormat();
    	XSSFCellStyle style = workBook.createCellStyle();
    	style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
    	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	style.setFillForegroundColor(HSSFColor.BLUE.index);
    	//格式2
    	XSSFCellStyle normalStyle = workBook.createCellStyle();
    	normalStyle.setDataFormat(format.getFormat("@"));
    	normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
    	//格式3
    	XSSFCellStyle dateStyle = workBook.createCellStyle();
    	dateStyle.setDataFormat(format.getFormat("yyyy-mm-dd"));
    	//字体
    	XSSFFont font = workBook.createFont(); 
    	font.setFontHeightInPoints((short) 11);
    	font.setFontName("微软雅黑");
    	font.setColor(HSSFColor.WHITE.index);
    	style.setFont(font);
    	//记录最大列宽
    	int[] lengths = new int[describes.length];
    	//设置标题行
    	XSSFRow row = null;
    	XSSFCell cell = null;
    	row = sheet.createRow(0);
    	row.setHeightInPoints(21);
    	for (int i = 0; i < describes.length; i++) {
    		String string = describes[i];
    		cell = row.createCell(i);
    		cell.setCellStyle(normalStyle);
    		cell.setCellValue(string);
    		lengths[i] = string.length();
    	}
    	if (list == null || list.isEmpty()) {
    		return workBook;
    	}
    	//数据行
    	for (int i = 0; i < list.size(); i++) {
    		List<?> innerList = list.get(i);
    		row = sheet.createRow(i+1);
    		for (int j = 0; j < innerList.size(); j++) {
    			cell = row.createCell(j);
    			Object obj = innerList.get(j);
    			if (obj != null) {
					String string = String.valueOf(obj);
					cell.setCellValue(string);
					if (string.length() > lengths[j]) {
						lengths[j] = string.length();
					}
				} else {
					cell.setCellValue("");
				}
    		}
    	}
    	//设置列宽
    	for (int i = 0; i < lengths.length; i++) {
    		//全部作为中文字符算*512
    		sheet.setColumnWidth(i, lengths[i] * 512);
    	}
    	return workBook;
    }  
    
    /**
     * @Title: readExcel
     * @Description: 读取Excel文件内容
     * @param suffix
     * @param is
     * @return ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> readExcel(String suffix, InputStream is) {  
    	return readExcel(suffix, is, 0, "1-", "A-");  
    }  
    
    /**
     * @Title: readExcel
     * @Description: 读取Excel文件内容
     * @param suffix
     * @param inputStream
     * @param sheetIndex
     * @return ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> readExcel(String suffix, InputStream inputStream, int sheetIndex) {  
        return readExcel(suffix, inputStream, sheetIndex, "1-", "A-");  
    }  
    
    /**
     * @Title: readExcel
     * @Description: 读取Excel文件内容
     * @param suffix
     * @param inputStream
     * @param sheetIndex
     * @param rows
     * @return ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> readExcel(String suffix, InputStream inputStream, int sheetIndex, String rows) {  
        return readExcel(suffix, inputStream, sheetIndex, rows, "A-");  
    } 
    
    /**
     * @Title: readExcel
     * @Description: 读取Excel文件内容
     * @param suffix
     * @param inputStream
     * @param sheetIndex
     * @param rows	1-10,12-
     * @param columns  A-D,AA-
     * @return ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> readExcel(String suffix, InputStream inputStream, int sheetIndex, String rows, String columns) {
    	ArrayList<ArrayList<String>> result = null;
    	Workbook wb = null;
		try {
			if (StringUtils.isBlank(suffix) || suffix.indexOf(".xlsx") != -1) {
				wb = new XSSFWorkbook(inputStream);
			} else {
				wb = new HSSFWorkbook(inputStream);
			}
			Sheet sheet = wb.getSheetAt(sheetIndex);
			result = readExcel(sheet, rows, getColumnNumber(sheet, columns));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
    }
    
    /**
     * @Title: readExcel
     * @Description: 读取Excel文件内容
     * @param sheet
     * @param rows
     * @param cols
     * @return ArrayList<ArrayList<String>>
     */
    private static ArrayList<ArrayList<String>> readExcel(Sheet sheet, String rows, int[] cols) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        // 处理行信息，并逐行列块读取数据  
        String[] rowList = rows.split(SEPARATOR);  
        for (String rowStr : rowList) {  
            if (rowStr.contains(CONNECTOR)) {  
                String[] rowArr = rowStr.trim().split(CONNECTOR);  
                int start = Integer.parseInt(rowArr[0]) - 1;  
                int end;  
                if (rowArr.length == 1) {  
                    end = sheet.getLastRowNum();  
                } else {  
                    end = Integer.parseInt(rowArr[1].trim()) - 1;  
                }  
                dataList.addAll(getRowsValue(sheet, start, end, cols));  
            } else {  
                dataList.add(getRowValue(sheet, Integer.parseInt(rowStr) - 1, cols));  
            }  
        }  
        return dataList;  
    }  
    
    /**
     * @Title: getRowsValue
     * @Description: 获取连续行、不连续列数据
     * @param sheet
     * @param startRow
     * @param endRow
     * @param cols
     * @return ArrayList<ArrayList<String>>
     */
    private static ArrayList<ArrayList<String>> getRowsValue(Sheet sheet, int startRow, int endRow, int[] cols) {  
        if (endRow < startRow) {  
            return null;  
        }  
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();  
        for (int i = startRow; i <= endRow; i++) {  
            data.add(getRowValue(sheet, i, cols));  
        }  
        return data;  
    } 
    
    /**
     * @Title: getRowValue
     * @Description: 获取行不连续列数据
     * @param sheet
     * @param rowIndex
     * @param cols
     * @return ArrayList<String>
     */
    private static ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int[] cols) {  
        Row row = sheet.getRow(rowIndex);  
        ArrayList<String> rowData = new ArrayList<String>();  
        for (int colIndex : cols) {  
            rowData.add(getCellValue(row, colIndex));  
        }  
        return rowData;  
    }  
      
    /**
     * @Title: getCellValue
     * @Description: 获取单元格内容
     * @param row
     * @param col 0 to 65535
     * @return String
     */
    private static String getCellValue(Row row, int col) {  
        if (row == null) {  
            return "";  
        }  
        Cell cell = row.getCell(col);  
        return getCellValue(cell);  
    }  
  
    /**
     * @Title: getCellValue
     * @Description: 获取单元格内容 
     * @param cell
     * @return String
     */
    private static String getCellValue(Cell cell) {  
        if (cell == null) {  
            return "";  
        }  
        String value = cell.toString().trim();  
        try {  
            Float.parseFloat(value);  
            value=value.replaceAll("\\.0$", "");  
            value=value.replaceAll("\\.0+$", "");  
            return value;  
        } catch (NumberFormatException ex) {  
            return value;  
        }  
    } 
    
    /**
     * @Title: getColumnNumber
     * @Description: 获取单列序号
     * @param column 字母
     * @return int
     */
    private static int getColumnNumber(String column) {  
        int length = column.length();  
        short result = 0;  
        for (int i = 0; i < length; i++) {  
            char letter = column.toUpperCase().charAt(i);  
            int value = letter - 'A' + 1;  
            result += value * Math.pow(26, length - i - 1);  
        }  
        return result - 1;  
    }  
  
    /**
     * @Title: getColumnNumber
     * @Description: 获取多列序号
     * @param sheet
     * @param columns 数字
     * @return int[]
     */
    private static int[] getColumnNumber(Sheet sheet, String columns) {  
        // 存为List
        ArrayList<Integer> result = new ArrayList<Integer> ();  
        String[] colList = columns.split(SEPARATOR);  
        for(String colStr : colList){  
            if(colStr.contains(CONNECTOR)){  
                String[] colArr = colStr.trim().split(CONNECTOR);  
                int start = getColumnNumber(colArr[0]);  
                int end;  
                if(colArr.length == 1){  
                    end = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum() - 1;  
                }else{  
                    end = getColumnNumber(colArr[1].trim());  
                }  
                for(int i=start; i<=end; i++) {  
                    result.add(i);  
                }  
            }else{  
                result.add(getColumnNumber(colStr));  
            }  
        }  
        // 将List转换为数组  
        int len = result.size();  
        int[] cols = new int[len];   
        for(int i = 0; i<len; i++) {  
            cols[i] = result.get(i).intValue();  
        }  
        return cols;  
    } 
    
//    @Test
    public void testReadExcel() {
    	InputStream is = null;
    	try {
    		File file = new File("d:/Temp.xlsx");
    		is = new FileInputStream(file);
    		ArrayList<ArrayList<String>> readExcel = readExcel(file.getName(), is, 1);
    		if (readExcel != null) {
    			for (ArrayList<String> arrayList : readExcel) {
    				if (arrayList != null) {
    					for (String string : arrayList) {
    						System.out.println(string);
    					}
    				}
    			}
    		}
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} finally {
    		if (is != null) {
    			try {
    				is.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
			}
    	}
    }
    
}

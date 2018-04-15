package com.cj.witcommon;

import com.cj.witcommon.utils.excle.StudentBaseInfo;
import com.cj.witcommon.utils.excle.exportExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TsetExExcle {


    /**
     *
     * @方法功能备注：导出汇总和流水的数据
     * @修改日期：        2016-2-17 下午02:50:16
     * ----------------------------------------------------------------------------------------------------------------
     * @修改历史
     *                     序号               日期                              修改人              修改原因
     *
     *
     * ----------------------------------------------------------------------------------------------------------------
     *@备注：
     * @测试结果：通过
     */
    /**测试生成excel表格数据*/


//    @Test
//    public void testPOI(){
//        exportExcelUtil gwu=null;
//        String path=null;
//        try {
//            gwu=new exportExcelUtil();
//            path="D:/file/test2.xlsx";
////1、输出的文件地址及名称
//            OutputStream out = new FileOutputStream(path);
////2、sheet表中的标题行内容，需要输入excel的汇总数据
//            String[] summary = { "系统名称", "活动名称","门店号" ,"日报时间","发券数量","使用数量"};
//            List<List<String>> summaryData = new ArrayList<List<String>>();
//            List<StudentBaseInfo> _listSummary=new ArrayList<StudentBaseInfo>();
//            for (StudentBaseInfo sum:_listSummary) {
//                List<String> rowData = new ArrayList<String>();
//                rowData.add(sum.getId());
//                rowData.add(sum.getHdmc());
//                rowData.add(sum.getMdh());
//                rowData.add(sum.getCreatTime());
//                rowData.add(String.valueOf(sum.getHandoutTotal()));
//                rowData.add(String.valueOf(sum.getUseTotal()));
//                summaryData.add(rowData);
//            }
//
//            String[] water = { "系统名称", "门店号" ,"门店名称","小票号","活动编号"
//                    ,"活动名称","发券数量","商品条码","商品名称","购买数量"
//                    ,"发券时间","分类代码","是否领赠","数据是否为真"};
//            List<List<String>> waterData = new ArrayList<List<String>>();
//            List<GenerWater> _listWater=new ArrayList<GenerWater>();
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
////3、生成格式是xlsx可存储103万行数据，如果是xls则只能存不到6万行数据
//            XSSFWorkbook workbook = new XSSFWorkbook();
////第一个表格内容
//            gwu.exportExcel(workbook, 0, "日报汇总", summary, summaryData, out);
////第二个表格内容
//            gwu.exportExcel(workbook, 1, "部分流水数据", water, waterData, out);
////将所有的数据一起写入，然后再关闭输入流。
//            workbook.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

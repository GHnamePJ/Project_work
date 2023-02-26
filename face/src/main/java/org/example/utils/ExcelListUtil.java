package org.example.utils;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.pojo.Affair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ExcelListUtil {
    public static String ExcelListUtil(List<Affair> stuList) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd hhmmss");
        Workbook wb = new XSSFWorkbook();
        //标题行抽出字段
        String[] title = {"序号","姓名", "手机", "签到方式", "签到时间", "签到"};
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "会议签到表";
        Sheet stuSheet = wb.createSheet(sheetName);
        //获取表头行
        Row titleRow = stuSheet.createRow(0);
        //创建单元格，设置style居中，字体，单元格大小等
        CellStyle style = wb.createCellStyle();
        Cell cell = null;
        //把已经写好的标题行写入excel文件中
        for (int i = 0; i < title.length; i++) {
            cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i = 0; i < stuList.size(); i++) {
            //创建list.size()行数据
            row = stuSheet.createRow(i + 1);
            //把值一一写进单元格里
            //设置第一列为自动递增的序号
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(stuList.get(i).getName());
            row.createCell(2).setCellValue(stuList.get(i).getPhone());
            row.createCell(3).setCellValue(stuList.get(i).getSign_in_way());
            //把时间转换为指定格式的字符串再写入excel文件中
            if (stuList.get(i).getSign_in_time() != null) {
                row.createCell(4).setCellValue(sdf.format(stuList.get(i).getSign_in_time()));
                System.out.println(stuList.get(i).getSign_in_time());
            }
                row.createCell(5).setCellValue(stuList.get(i).getSign_in_state());


        }
        //设置单元格宽度自适应，在此基础上把宽度调至1.5倍
        for (int j = 0; j < title.length; j++) {
            stuSheet.autoSizeColumn(j, true);
            stuSheet.setColumnWidth(j, stuSheet.getColumnWidth(j) * 15 / 10);
        }
        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
//        String path = Base64Util.class.getResource("/").getPath().split("/target")[0];
        String path = Base64Util.class.getResource("/").getPath().split("/target")[0];
        String folderPath = path + "\\src\\main\\webapp\\res\\signExcel";

        //创建上传文件目录
        File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String fileName = sheetName + sdf1.format(new Date()) + ".xlsx";
        String savePath = folderPath + File.separator + fileName;
        // System.out.println(savePath);

        OutputStream fileOut = new FileOutputStream(savePath);
        wb.write(fileOut);
        fileOut.close();
        //返回文件保存全路径
        return fileName;
    }
}

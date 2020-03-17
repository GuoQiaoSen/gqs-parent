package com.gqs.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * excel工具类
 *
 * @author 郭乔森
 * @create 2020-03-12 17:02
 */
public class ExcelUtil {

    private static FormulaEvaluator evaluator;

    /**
     * 转换workbook
     *
     * @param file
     * @return workbook
     */
    public static Workbook getWorkBook(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("文件不存在！");
        }
        // 获取excel文件的io流
        InputStream is = file.getInputStream();
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = WorkbookFactory.create(is);
        is.close();
        return workbook;
    }

    /**
     * 获取单元格各类型值，返回字符串类型。cell为null则返回""
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        //判断是否为null或空串
        if (cell == null || "".equals(cell.toString().trim())) {
            return "";
        }
        String cellValue = "";
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_FORMULA) { //表达式类型
            cellType = evaluator.evaluate(cell).getCellType();
        }

        switch (cellType) {
            case Cell.CELL_TYPE_STRING: //字符串类型
                cellValue = cell.getStringCellValue().trim();
                cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: //数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                    cellValue = DateUtils.formatDateByFormat(cell.getDateCellValue(), "yyyy-MM-dd");
                } else {  //否
                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型，取空串吧
                cellValue = "";
                break;
        }
        return cellValue;
    }
}

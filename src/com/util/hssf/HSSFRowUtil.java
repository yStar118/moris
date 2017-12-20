package com.util.hssf;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

/**
 * HSSFRow Utilities
 * Created by Bodil on 2016/7/5.
 */
public class HSSFRowUtil {

    /**
     * 获取单元格中的字符串值（不是字符串类型则进行强制转换）
     *
     * @param hssfRow 行
     * @param cellnum 单元格下标值
     * @return 单元格中的字符串值
     */
    public static String getCellStringValue(HSSFRow hssfRow, int cellnum) {
        HSSFCell cell = hssfRow.getCell(cellnum);
        if (cell == null) return null;
        if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        return cell.getStringCellValue();
    }

    /**
     * 获取单元格中的数字值（不是数值类型则进行强制转换）
     *
     * @param hssfRow 行
     * @param cellnum 单元格下标值
     * @return 单元格中的数字值
     */
    public static double getCellNumbericValue(HSSFRow hssfRow, int cellnum) {
        HSSFCell cell = hssfRow.getCell(cellnum);
        if (cell == null) return 0;
        try {
            if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            }
            return cell.getNumericCellValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

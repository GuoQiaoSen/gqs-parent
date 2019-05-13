package com.gqs.example;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

/**
 * 描述:
 * 生成pdf例子
 * 这个例子是做多多代账项目的时候，为了实现凭证连续打印功能而写的模板
 * <p>
 * 1.首先引入iText jar包
 * 2.Chunk Phrase Paragraph
 * 3.x y原点在纸张左下角
 * 4.1mm约等于2.83pt,A4尺寸是21cm*29.7cm,所以应该是594.3pt*840.51pt
 * page尺寸：w 595pt  h 842pt
 *
 * @author 郭乔森
 * @create 2019-05-10 14:42
 */
public class PdfExample {

    public static void main(String[] args) throws Exception {
        example1();
//        example2();
    }

    public static void example2() throws Exception {

        String fontPath = "D:\\个人资料\\msyh.ttf";
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 微软雅黑 16pt 加粗
        Font yahei1 = new Font(baseFont, 16);
        // 微软雅黑 13.6pt
        Font yahei2 = new Font(baseFont, 13.6F);
        // 微软雅黑 9pt
        Font yahei3 = new Font(baseFont, 9);
        // 微软雅黑 9pt
        Font yahei4 = new Font(baseFont, 9);

        Document doc = new Document(PageSize.A4);
        FileOutputStream out = new FileOutputStream("D:\\个人资料\\test.pdf");
        PdfWriter writer = PdfWriter.getInstance(doc, out);

        doc.open();

        PdfContentByte canvas = writer.getDirectContent();

        // 绘制图表
        draw1(yahei1, yahei3, yahei4, writer, canvas);
        draw2(yahei1, yahei3, yahei4, writer, canvas);
        doc.newPage();
        draw1(yahei1, yahei3, yahei4, writer, canvas);
        draw2(yahei1, yahei3, yahei4, writer, canvas);


        // 5.关闭文档
        doc.close();
    }

    /**
     * 绘制图表-上半页
     *
     * @param yahei1
     * @param yahei3
     * @param yahei4
     * @param writer
     * @param canvas
     * @throws DocumentException
     */
    private static void draw1(Font yahei1, Font yahei3, Font yahei4, PdfWriter writer, PdfContentByte canvas) throws DocumentException {
        Phrase phrase1 = new Phrase("记账凭证", yahei1);
        Phrase phrase2 = new Phrase(new Chunk("附件数：23333332", yahei3).setTextRise(5F));
        Phrase phrase3 = new Phrase("北京卅思云科技股份有限公司", yahei3);
        Phrase phrase4 = new Phrase("日期：2019-05-20", yahei3);
        Phrase phrase5 = new Phrase(new Chunk("凭证号：记-22(1/2)", yahei3));
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase1, 297.5F, 797.6F, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase2, 547.5F, 797.6F, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase3, 47.5F, 776.8F, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase4, 297.5F, 776.8F, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase5, 547.5F, 776.8F, 0);

        // 添加表格，4列
        PdfPTable table = new PdfPTable(4);
        //// 设置表格宽度比例为%100
        table.setWidthPercentage(100);
        // 设置表格的宽度
        table.setTotalWidth(500);
        // 也可以每列分别设置宽度
        table.setTotalWidth(new float[]{191, 191, 59, 59});
        // 锁住宽度
        table.setLockedWidth(true);
        // 设置表格上面空白宽度
//        table.setSpacingBefore(30f);
        // 设置表格默认为无边框
        table.getDefaultCell().setBorder(0);
        PdfContentByte cb = writer.getDirectContent();

        // 构建每个单元格
        PdfPCell cell1 = new PdfPCell(new Paragraph("摘要", yahei4));
        // 设置距左边的距离
        cell1.setPaddingLeft(10);
        // 设置高度
        cell1.setFixedHeight(40);
        // 设置内容水平居中显示
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Paragraph("科目", yahei4));
        cell2.setPaddingLeft(10);
        cell2.setFixedHeight(40);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Paragraph("借方", yahei4));
        cell3.setPaddingLeft(10);
        cell3.setFixedHeight(40);
        // 设置无边框
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Paragraph("贷方", yahei4));
        cell4.setPaddingLeft(10);
        cell4.setFixedHeight(40);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell4);

        for (int i = 0; i < 5; i++) {
            PdfPCell cell5 = new PdfPCell(new Paragraph("", yahei4));
            cell5.setPaddingLeft(10);
            cell5.setFixedHeight(40);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Paragraph("", yahei4));
            cell6.setPaddingLeft(10);
            cell6.setFixedHeight(40);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell6);

            PdfPCell cell7 = new PdfPCell(new Paragraph("", yahei4));
            cell7.setPaddingLeft(10);
            cell7.setFixedHeight(40);
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Paragraph("", yahei4));
            cell8.setPaddingLeft(10);
            cell8.setFixedHeight(40);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell8);
        }

        PdfPCell cell9 = new PdfPCell(new Paragraph("", yahei4));
        cell9.setPaddingLeft(10);
        cell9.setFixedHeight(40);
        cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell9.setColspan(2);
        table.addCell(cell9);

        PdfPCell cell10 = new PdfPCell(new Paragraph("", yahei4));
        cell10.setPaddingLeft(10);
        cell10.setFixedHeight(40);
        cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell10);

        PdfPCell cell11 = new PdfPCell(new Paragraph("", yahei4));
        cell11.setPaddingLeft(10);
        cell11.setFixedHeight(40);
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell11);

        table.writeSelectedRows(0, -1, 47.5F, 752, canvas);

        Phrase phrase6 = new Phrase("主管：", yahei3);
        Phrase phrase7 = new Phrase("审核：", yahei3);
        Phrase phrase8 = new Phrase("出纳：", yahei3);
        Phrase phrase9 = new Phrase("制单：", yahei3);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase6, 49.5F, 451, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase7, 222, 451, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase8, 347, 451, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase9, 545.5F, 451, 0);
    }

    /**
     * 绘制图表-下半页
     *
     * @param yahei1
     * @param yahei3
     * @param yahei4
     * @param writer
     * @param canvas
     * @throws DocumentException
     */
    private static void draw2(Font yahei1, Font yahei3, Font yahei4, PdfWriter writer, PdfContentByte canvas) throws DocumentException {
        Phrase phrase1 = new Phrase("记账凭证", yahei1);
        Phrase phrase2 = new Phrase(new Chunk("附件数：23333332", yahei3).setTextRise(5F));
        Phrase phrase3 = new Phrase("北京卅思云科技股份有限公司", yahei3);
        Phrase phrase4 = new Phrase("日期：2019-05-20", yahei3);
        Phrase phrase5 = new Phrase(new Chunk("凭证号：记-22(1/2)", yahei3));
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase1, 297.5F, 401, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase2, 547.5F, 401, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase3, 47.5F, 380.2F, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase4, 297.5F, 380.2F, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase5, 547.5F, 380.2F, 0);

        // 添加表格，4列
        PdfPTable table = new PdfPTable(4);
        //// 设置表格宽度比例为%100
        table.setWidthPercentage(100);
        // 设置表格的宽度
        table.setTotalWidth(500);
        // 也可以每列分别设置宽度
        table.setTotalWidth(new float[]{191, 191, 59, 59});
        // 锁住宽度
        table.setLockedWidth(true);
        // 设置表格上面空白宽度
//        table.setSpacingBefore(30f);
        // 设置表格默认为无边框
        table.getDefaultCell().setBorder(0);
        PdfContentByte cb = writer.getDirectContent();

        // 构建每个单元格
        PdfPCell cell1 = new PdfPCell(new Paragraph("摘要", yahei4));
        // 设置距左边的距离
        cell1.setPaddingLeft(10);
        // 设置高度
        cell1.setFixedHeight(40);
        // 设置内容水平居中显示
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Paragraph("科目", yahei4));
        cell2.setPaddingLeft(10);
        cell2.setFixedHeight(40);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Paragraph("借方", yahei4));
        cell3.setPaddingLeft(10);
        cell3.setFixedHeight(40);
        // 设置无边框
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Paragraph("贷方", yahei4));
        cell4.setPaddingLeft(10);
        cell4.setFixedHeight(40);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell4);

        for (int i = 0; i < 5; i++) {
            PdfPCell cell5 = new PdfPCell(new Paragraph("", yahei4));
            cell5.setPaddingLeft(10);
            cell5.setFixedHeight(40);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Paragraph("", yahei4));
            cell6.setPaddingLeft(10);
            cell6.setFixedHeight(40);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell6);

            PdfPCell cell7 = new PdfPCell(new Paragraph("", yahei4));
            cell7.setPaddingLeft(10);
            cell7.setFixedHeight(40);
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Paragraph("", yahei4));
            cell8.setPaddingLeft(10);
            cell8.setFixedHeight(40);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell8);
        }

        PdfPCell cell9 = new PdfPCell(new Paragraph("", yahei4));
        cell9.setPaddingLeft(10);
        cell9.setFixedHeight(40);
        cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell9.setColspan(2);
        table.addCell(cell9);

        PdfPCell cell10 = new PdfPCell(new Paragraph("", yahei4));
        cell10.setPaddingLeft(10);
        cell10.setFixedHeight(40);
        cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell10);

        PdfPCell cell11 = new PdfPCell(new Paragraph("", yahei4));
        cell11.setPaddingLeft(10);
        cell11.setFixedHeight(40);
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell11);

        table.writeSelectedRows(0, -1, 47.5F, 351, canvas);

        Phrase phrase6 = new Phrase("主管：", yahei3);
        Phrase phrase7 = new Phrase("审核：", yahei3);
        Phrase phrase8 = new Phrase("出纳：", yahei3);
        Phrase phrase9 = new Phrase("制单：", yahei3);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase6, 49.5F, 30, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase7, 222, 30, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase8, 347, 30, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase9, 545.5F, 30, 0);
    }

    /**
     * 这是最简单的生成pdf的代码
     * <p>
     * 生成pdf文件,并且写入内容。需要注意的是中文无法写入，需要给文字设置字体才行
     *
     * @throws Exception
     */
    public static void example1() throws Exception {
        // 1.建立com.lowagie.text.Document对象的实例
        Document document = new Document();
        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        PdfWriter.getInstance(document, new FileOutputStream("D:\\个人资料\\test1.pdf"));
        // 3.打开文档
        document.open();
        // 4.向文档中添加内容
        String fontPath = "D:\\个人资料\\msyh.ttf";
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 微软雅黑 16pt 加粗
        Font yahei1 = new Font(baseFont, 16);

        document.add(new Paragraph("asd记账凭证", yahei1));
        // 5.关闭文档
        document.close();
    }

}

package com.lmc.tools.file;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfService {

    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);

    /**
     * 给pdf模板中添加文字，pdf模板事先用Adobe Acrobat DC软件设置填写字段
     * @throws IOException
     * @throws DocumentException
     */
    public void setPdf() throws IOException, DocumentException {
        //获取pdf模板
        PdfReader reader = new PdfReader("fileTemplates/jpn.pdf");

        //生成文件地址
        File file = new File("D:/dfzq");

        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file));

        //获取pdf模板中可填字段
        AcroFields fields = stamp.getAcroFields();

        //设置字体
        BaseFont font = BaseFont.createFont("fonts/heiTi.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fields.addSubstitutionFont(font);

        //设置更多字段
        fields.setField("account_name", "account_name");
        fields.setField("account_no", "accountNumber");
        /**
         * 设置更多字段.................
         */

        stamp.setFormFlattening(true);
        //设置读写权限
        boolean isSetReadableSuccess = file.setReadable(true, false);
        boolean isSetWritableSuccess = file.setWritable(true, false);
        if(!isSetWritableSuccess || !isSetReadableSuccess){
            logger.warn("set file read write auth fail:{}", file.getName());
        }

        stamp.close();
        reader.close();
    }

    /**
     * pdf模板中  固定位子填入文字
     * @throws IOException
     * @throws DocumentException
     */
    public void setName() throws IOException, DocumentException {
        //获取pdf模板
        PdfReader reader = new PdfReader("fileTemplates/jpn.pdf");

        //生成文件地址
        File file = new File("D:/dfzq");

        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file));

        BaseFont bf = BaseFont.createFont("fonts/heiTi.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        PdfContentByte page1 = stamp.getOverContent(1);
        page1.beginText();
        page1.setFontAndSize(bf, 12);
        page1.setTextMatrix(145, 75);
        page1.newlineShowText("name");
        page1.endText();

        stamp.setFormFlattening(true);
        //设置读写权限
        boolean isSetReadableSuccess = file.setReadable(true, false);
        boolean isSetWritableSuccess = file.setWritable(true, false);
        if(!isSetWritableSuccess || !isSetReadableSuccess){
            logger.warn("set file read write auth fail:{}", file.getName());
        }

        stamp.close();
        reader.close();
    }
}

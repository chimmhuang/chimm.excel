package com.github.chimmhuang.excel.tablemodel;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Chimm Huang
 */
public enum PictureType {

    /**
     * see {@link Workbook}
     */
    EMF(Workbook.PICTURE_TYPE_EMF),
    WMF(Workbook.PICTURE_TYPE_WMF),
    PICT(Workbook.PICTURE_TYPE_PICT),
    JPEG(Workbook.PICTURE_TYPE_JPEG),
    PNG(Workbook.PICTURE_TYPE_PNG),
    DIB(Workbook.PICTURE_TYPE_DIB)
    ;

    private final int type;

    PictureType(int type) {
        this.type = type;
    }

    public int type() {
        return type;
    }
}

package com.github.chimmhuang.school;

import java.math.BigDecimal;

/**
 * @author Chimm Huang
 */
public class ClassScore extends Score {
    private int classId;
    private String className;

    public ClassScore(int classId, String className, String name, BigDecimal totalScore, BigDecimal chineseScore, BigDecimal mathScore, BigDecimal englishScore) {
        super(name, totalScore, chineseScore, mathScore, englishScore);
        this.classId = classId;
        this.className = className;
    }

    public ClassScore() {
    }

    public ClassScore(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}

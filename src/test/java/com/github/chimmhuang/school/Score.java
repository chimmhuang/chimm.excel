package com.github.chimmhuang.school;

import java.math.BigDecimal;

/**
 * @author Chimm Huang
 */
public class Score {
    private String name;
    private BigDecimal totalScore;
    private BigDecimal chineseScore;
    private BigDecimal mathScore;
    private BigDecimal englishScore;

    public Score(String name, BigDecimal totalScore, BigDecimal chineseScore, BigDecimal mathScore, BigDecimal englishScore) {
        this.name = name;
        this.totalScore = totalScore;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    public Score() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(BigDecimal chineseScore) {
        this.chineseScore = chineseScore;
    }

    public BigDecimal getMathScore() {
        return mathScore;
    }

    public void setMathScore(BigDecimal mathScore) {
        this.mathScore = mathScore;
    }

    public BigDecimal getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(BigDecimal englishScore) {
        this.englishScore = englishScore;
    }
}

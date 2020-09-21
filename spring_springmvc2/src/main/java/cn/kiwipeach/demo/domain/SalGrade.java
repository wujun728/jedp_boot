package cn.kiwipeach.demo.domain;

import java.math.BigDecimal;

public class SalGrade {
    private BigDecimal grade;

    private BigDecimal losal;

    private BigDecimal hisal;

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public BigDecimal getLosal() {
        return losal;
    }

    public void setLosal(BigDecimal losal) {
        this.losal = losal;
    }

    public BigDecimal getHisal() {
        return hisal;
    }

    public void setHisal(BigDecimal hisal) {
        this.hisal = hisal;
    }
}
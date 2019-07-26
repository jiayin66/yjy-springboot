package com.yjy.springboot.model;


//import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//数据应用车辆信息返回值模型
//@XmlRootElement(name = "row")
public class CLinfoModel {

    //@ApiModelProperty(value = "号牌号码")
    private String HPHM;

    //@ApiModelProperty(value = "号牌种类")
    private String HPZL;

    //@ApiModelProperty(value = "所有人")
    private String SYR;

    //@ApiModelProperty(value = "车身颜色")
    private String CSYS;

    //@ApiModelProperty(value = "中文品牌")
    private String CLPP1;

    //@ApiModelProperty(value = "英文品牌")
    private String CLPP2;

    //@ApiModelProperty(value = "CLXH")
    private String CLXH;

    //@ApiModelProperty(value = "身份证明号码")
    private String SFZMHM;

    //@ApiModelProperty(value = "车辆类型")
    private String CLLX;

    //@XmlElement(name = "HPHM")
    public String getHPHM() { return HPHM; }

    public void setHPHM(String HPHM) { this.HPHM = HPHM; }

    //@XmlElement(name = "HPZL")
    public String getHPZL() { return HPZL; }

    public void setHPZL(String HPZL) { this.HPZL = HPZL; }

    //@XmlElement(name = "SYR")
    public String getSYR() { return SYR; }

    public void setSYR(String SYR) { this.SYR = SYR; }

    //@XmlElement(name = "CSYS")
    public String getCSYS() { return CSYS; }

    public void setCSYS(String CSYS) { this.CSYS = CSYS; }

    //@XmlElement(name = "CLPP1")
    public String getCLPP1() { return CLPP1; }

    public void setCLPP1(String CLPP1) { this.CLPP1 = CLPP1; }

    //@XmlElement(name = "CLPP2")
    public String getCLPP2() { return CLPP2; }

    public void setCLPP2(String CLPP2) { this.CLPP2 = CLPP2; }

    //@XmlElement(name = "CLXH")
    public String getCLXH() { return CLXH; }

    public void setCLXH(String CLXH) { this.CLXH = CLXH; }

    //@XmlElement(name = "SFZMHM")
    public String getSFZMHM() { return SFZMHM; }

    public void setSFZMHM(String SFZMHM) { this.SFZMHM = SFZMHM; }

    //@XmlElement(name = "CLLX")
    public String getCLLX() { return CLLX; }

    public void setCLLX(String CLLX) { this.CLLX = CLLX; }
}

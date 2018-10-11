package com.kandy.tissot.oss;

public enum AliOssContentTypeEnum {

    IMAGE("image", "图片"), PDF("pdf", "pdf文档"), HTML("html", "html文档"), TXT("txt", "txt文本");

    private String type;
    private String des;

    private AliOssContentTypeEnum(String type, String des) {
        this.type = type;
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}

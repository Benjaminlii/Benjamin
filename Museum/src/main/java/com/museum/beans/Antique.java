package com.museum.beans;

/**
 * @author wangzhao
 * @date 2019/7/24 13:56
 */
// 文物
public class Antique {

    // 文物id
    private Integer antiqueId;

    // 文物名字
    private String antiqueName;

    // 所属展厅名字
    private String showName;

    // 文物描述
    private String antique_desc;

    // 文物资源
    private Resource resource;

    public Integer getAntiqueId() {
        return antiqueId;
    }

    public void setAntiqueId(Integer antiqueId) {
        this.antiqueId = antiqueId;
    }

    public String getAntiqueName() {
        return antiqueName;
    }

    public void setAntiqueName(String antiqueName) {
        this.antiqueName = antiqueName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getAntique_desc() {
        return antique_desc;
    }

    public void setAntique_desc(String antique_desc) {
        this.antique_desc = antique_desc;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Antique{" +
                "antiqueId=" + antiqueId +
                ", antiqueName='" + antiqueName + '\'' +
                ", showName='" + showName + '\'' +
                ", antique_desc='" + antique_desc + '\'' +
                ", resource=" + resource +
                '}';
    }
}

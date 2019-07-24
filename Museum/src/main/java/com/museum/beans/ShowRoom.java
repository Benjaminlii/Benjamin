package com.museum.beans;

/**
 * @author wangzhao
 * @date 2019/7/24 13:55
 */

// 展厅
public class ShowRoom {

    // 展厅id
    private Integer showId;

    // 展厅名字
    private String showName;

    // 展厅描述
    private String showDesc;

    // 展厅资源
    private Resource resource;

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowDesc() {
        return showDesc;
    }

    public void setShowDesc(String showDesc) {
        this.showDesc = showDesc;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ShowRoom{" +
                "showId=" + showId +
                ", showName='" + showName + '\'' +
                ", showDesc='" + showDesc + '\'' +
                ", resource=" + resource +
                '}';
    }
}

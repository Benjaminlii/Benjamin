package com.museum.beans;

/**
 * @author wangzhao
 * @date 2019/7/24 13:56
 */
// 资源
public class Resource {

    // 资源id
    private Integer resourceId;

    // 图片路径
    private String picturePath;

    // 音频路径
    private String videoPath;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", picturePath='" + picturePath + '\'' +
                ", videoPath='" + videoPath + '\'' +
                '}';
    }
}

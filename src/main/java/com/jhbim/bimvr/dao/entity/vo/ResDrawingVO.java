package com.jhbim.bimvr.dao.entity.vo;

import java.util.List;

public class ResDrawingVO {

    private String name;
    private List<ResDrawingUrl> res;

    class ResDrawingUrl {
        private String resName;
        private String resUrl;

        public String getResName() {
            return resName;
        }

        public void setResName(String resName) {
            this.resName = resName;
        }

        public String getResUrl() {
            return resUrl;
        }

        public void setResUrl(String resUrl) {
            this.resUrl = resUrl;
        }

    }

    public void setResUrl(String resAddress) {
        //为其添加资源的网络地址前缀
        for (ResDrawingUrl resDrawingUrl: this.res){
            resDrawingUrl.setResUrl(resAddress + resDrawingUrl.getResUrl());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResDrawingUrl> getRes() {
        return res;
    }

    public void setRes(List<ResDrawingUrl> res) {
        this.res = res;
    }

}

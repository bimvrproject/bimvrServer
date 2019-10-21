package com.jhbim.bimvr.dao.entity.vo;

import com.jhbim.bimvr.dao.entity.pojo.Printscreen;

import java.util.List;

public class PrintscreenVo {
    private List<Printscreen> printscreenslist;//返回用户所有的截图

    public List<Printscreen> getPrintscreenslist() {
        return printscreenslist;
    }

    public void setPrintscreenslist(List<Printscreen> printscreenslist) {
        this.printscreenslist = printscreenslist;
    }
}

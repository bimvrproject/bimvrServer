package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ExcelToHtml;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/${version}/excel")

public class ExcelToHtmlController {

    @RequestMapping("/exceltohtml")
    public Map<Object,String> exceltohtml(){
        Map<Object,String> map=new HashMap<>();
        ExcelToHtml excelToHtml=new ExcelToHtml();
        String convertByFile = excelToHtml.SubmitPost("http://dcs.yozosoft.com:80/upload", "C:\\Users\\Administrator\\Desktop\\招标清单.xlsx", "0");
        String url= String.valueOf(convertByFile.subSequence(21,87));
        map.put("aaa",url);
        return map;
    }
}

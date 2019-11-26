package com.jhbim.bimvr.controller.app.material;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhbim.bimvr.dao.entity.pojo.Chart;
import com.jhbim.bimvr.dao.entity.pojo.Material;
import com.jhbim.bimvr.pub.Response;
import com.jhbim.bimvr.service.IChartService;
import com.jhbim.bimvr.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-11-25 11:33
 */
@RestController
//@RequestMapping("/${version}/material")
public class MaterialController {

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IChartService chartService;

    /**
     * 分类集合
     * @return
     */
    @GetMapping("/getMaterial")
    public Response getMaterial(){
        List<Material> material = materialService.select();
        return Response.success().setData(material);
    }

    //按类分页查询
    @GetMapping("/findBypaging")
    public Response findByPaging(String materialId,Integer pageNum){
        Integer pageSize=6;
        PageHelper.startPage(pageNum,pageSize);
        Page<Chart> data = chartService.findByPaging(materialId);
        JSONObject result = new JSONObject();
        result.put("material",data);
        result.put("pages",data.getPages());
        result.put("total",data.getTotal());
        return Response.success().setData(result);
    }
}

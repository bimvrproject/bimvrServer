package com.jhbim.bimvr.controller.app.material;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhbim.bimvr.dao.entity.pojo.Material;
import com.jhbim.bimvr.dao.entity.pojo.MaterialSorts;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.MaterialMapper;
import com.jhbim.bimvr.dao.mapper.MaterialSortsMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private MaterialSortsMapper materialSortsMapper;

    @Autowired
    private MaterialMapper materialMapper;



    /**
     * 分类集合
     * @return
     */
    @GetMapping("/getMaterial")
    public Result getMaterial(){
        List<MaterialSorts> material = materialSortsMapper.select();
        return new Result(ResultStatusCode.OK,material);
    }

    //按类分页查询
    @GetMapping("/findBypaging")
    public Result findByPaging(String material_sortid,Integer pageNum){
        Integer pageSize=6;
        PageHelper.startPage(pageNum,pageSize);
        Page<Material> data = materialMapper.findByPaging(material_sortid);
        JSONObject result = new JSONObject();
        result.put("material",data);
        result.put("pages",data.getPages());
        result.put("total",data.getTotal());
        return new Result(ResultStatusCode.SUCCESS,result);
    }
}

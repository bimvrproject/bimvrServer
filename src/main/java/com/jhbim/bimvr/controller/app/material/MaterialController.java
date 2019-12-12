package com.jhbim.bimvr.controller.app.material;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhbim.bimvr.dao.entity.pojo.Furniture;
import com.jhbim.bimvr.dao.entity.pojo.FurnitureSorts;
import com.jhbim.bimvr.dao.entity.pojo.Material;
import com.jhbim.bimvr.dao.entity.pojo.MaterialSorts;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.FurnitureMapper;
import com.jhbim.bimvr.dao.mapper.FurnitureSortsMapper;
import com.jhbim.bimvr.dao.mapper.MaterialMapper;
import com.jhbim.bimvr.dao.mapper.MaterialSortsMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
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
@RequestMapping("/${version}/material")
public class MaterialController {

    /**
     *  佛曰:
     *          写字楼里写字间，写字间里程序员；
     *          程序人员写程序，又拿程序换酒钱。
     *          酒醒只在网上坐，酒醉还来网下眠；
     *          酒醉酒醒日复日，网上网下年复年。
     *          但愿老死电脑间，不愿鞠躬老板前；
     *          奔驰宝马贵者趣，公交自行程序员。
     *          别人笑我忒疯癫，我笑自己命太贱；
     *          不见满街漂亮妹，哪个归得程序员？
     */
    @Autowired
    private MaterialSortsMapper materialSortsMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private FurnitureSortsMapper furnitureSortsMapper;

    @Autowired
    private FurnitureMapper furnitureMapper;

    /**
     * 材质分类集合
     * @return
     */
    @GetMapping("/getMaterial")
    public Result getMaterial(){
        List<MaterialSorts> material = materialSortsMapper.select();
        return new Result(ResultStatusCode.OK,material);
    }

    //材质按类分页查询
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

    //按家具分类集合

    @GetMapping("/getFurniture")
    public Result getFurniture(){
        List<FurnitureSorts> furnitureSorts = furnitureSortsMapper.slelct();
        return new Result(ResultStatusCode.SUCCESS,furnitureSorts);
    }


    //家具按类分页查询
    @GetMapping("/findByFurn")
    public Result findByFurn(String furniture_sortid,Integer pageNum){
        Integer pageSize=6;
        PageHelper.startPage(pageNum,pageSize);
        Page<Furniture> data = furnitureMapper.findByFurn(furniture_sortid);
        JSONObject result = new JSONObject();
        result.put("furniture",data);
        result.put("pages",data.getPages());
        result.put("total",data.getTotal());
        return new Result(ResultStatusCode.SUCCESS,result);
    }

}

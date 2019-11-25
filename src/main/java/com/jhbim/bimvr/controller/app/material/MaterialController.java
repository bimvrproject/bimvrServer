package com.jhbim.bimvr.controller.app.material;

import com.jhbim.bimvr.dao.entity.pojo.Material;
import com.jhbim.bimvr.pub.Response;
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
@RequestMapping("/${version}/material")
public class MaterialController {

    @Autowired
    private IMaterialService materialService;

    /**
     * 分类集合
     * @return
     */
    @GetMapping("/getMaterial")
    public Response getMaterial(){
        List<Material> material = materialService.select();
        return Response.success().setData(material);
    }
}

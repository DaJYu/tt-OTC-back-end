package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.entity.IconInfoEntity;
import com.ydj.ttswap.mapper.IconInfoMapper;
import com.ydj.ttswap.service.IconInfoService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import com.ydj.ttswap.utils.ServiceSaveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

@Service("iconInfoService")
public class IconInfoServiceImpl extends ServiceImpl<IconInfoMapper, IconInfoEntity> implements IconInfoService {

    @Autowired
    IconInfoMapper iconInfoMapper;
    @Autowired
    SaveFileConfig saveFileConfig;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String fl= (String) params.get("fl");
        String id= (String) params.get("id");
        QueryWrapper<IconInfoEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(fl)) {
            wrapper.eq("fl",fl);
        }
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("id",id);
        }
        IPage<IconInfoEntity> page = this.page(
                new Query<IconInfoEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }


    @Override
    public Boolean saveIcon(MultipartFile file, String id, int fl) {
        String fname=file.getOriginalFilename();
//        System.out.print(file.getOriginalFilename());
        String fileName=id+fname.substring(fname.lastIndexOf("."), fname.length());;
        String fileUrl=saveFileConfig.getProfile();
        boolean fileUpload = ServiceSaveFile.singleFileUpload(file, fileName, fileUrl + "/icon-save/");
        if (fileUpload){
            IconInfoEntity iconInfo=new IconInfoEntity();
            iconInfo.setTbid(id);
            iconInfo.setMc(fileName);
            iconInfo.setFl(fl);
            try {
                Long cout= iconInfoMapper.selectCount(new QueryWrapper<IconInfoEntity>().eq("tbid",id));
                if (cout<1){
                    iconInfoMapper.insert(iconInfo);
                } else {
                    iconInfo.setXgsj(new Date());
                    iconInfoMapper.updateById(iconInfo);
                }
//                System.out.print(cout);
                return true;
            }catch (Exception e){
                return false;
            }
        } else {
            return false;
        }
    }
}

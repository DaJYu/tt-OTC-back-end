package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.IconInfoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 图标信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface IconInfoService extends IService<IconInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean saveIcon(MultipartFile file, String id, int fl);

//    List getIconList(Map<String,Object> params);
}


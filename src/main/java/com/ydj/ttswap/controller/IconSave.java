package com.ydj.ttswap.controller;

import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.service.GatorApplyResultService;
import com.ydj.ttswap.service.IconInfoService;
import com.ydj.ttswap.utils.MailUtil;
import com.ydj.ttswap.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("cs")
public class IconSave {
    @Autowired
    private IconInfoService infoService;
    @Autowired
    SaveFileConfig saveFileConfig;
    @Autowired
    GatorApplyResultService applyService;
    @Autowired
    MailUtil mailUtil;

    /*图标列表*/
    @RequestMapping("/list")
    public R list(){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3><a target=\"_blank\" href=\"http://121.5.135.52/ttswap\">查看</a>\n" +
                "</body>\n" +
                "</html>";
        mailUtil.sendHtmlMail("2542824491@qq.com","标题",content);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/saveIcon")
    public R saveIcon(@RequestParam("img") MultipartFile file,  @RequestParam("id") String id, @RequestParam("fl") int fl){
        Boolean aBoolean= infoService.saveIcon(file,id,fl);
        if (aBoolean){
            return R.ok();
        } else {
            return R.error();
        }
    }

    /*门户审核意见*/
//    @RequestMapping("/gatorApply")
//    public R gatorApply(@RequestParam Map<String, Object> params){
//        try {
//            applyService.saves(params);
//            return R.ok();
//        } catch (Exception e){
//            return R.error();
//        }
//    }
//    @RequestMapping("/gatorApplys")
//    public R gatorApplys(GatorApplyResult gatorApplyResult){
//        try {
//            applyService.save(gatorApplyResult);
//            return R.ok();
//        } catch (Exception e){
//            return R.error();
//        }
//    }

    /*门户审核列表*/
//    @RequestMapping("/gatorApplys/list")
//    public R lists(@RequestParam Map<String, Object> params){
//        List list = applyService.getgatorApplyist(params);
//        return R.ok().put("data",list);
//    }

}

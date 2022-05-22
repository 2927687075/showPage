package com.myshowpage.controller;

import com.github.pagehelper.PageInfo;
import com.myshowpage.pojo.ShowPage;
import com.myshowpage.service.ShowPageService;
import com.myshowpage.utils.UUIDUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@ResponseBody
public class ShouPageController {
    /*定义图片上传的文件夹*/

    private static final String PIC_BASE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";
    String name ="";

    @Resource
    private ShowPageService showPageService;




    @GetMapping("/showPage")
    public List<ShowPage> findAll(){
        List<ShowPage> showPages = showPageService.selectAll();
        return showPages;
    }

    @GetMapping("/showPageInfo")
    public PageInfo<ShowPage> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "3") Integer pageSize){
        PageInfo<ShowPage> showPagePageInfo = showPageService.splicePics(pageNum, pageSize);

        return showPagePageInfo;
    }

    @GetMapping("/showPageLike")
    public PageInfo<ShowPage> pageLike(@RequestParam String name,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "3") Integer pageSize){
        PageInfo<ShowPage> showPagePageInfo = showPageService.selectByName(name, pageNum, pageSize);
        return showPagePageInfo;
    }

    @GetMapping("/showPageLikePic")
    public PageInfo<ShowPage> pageLikePic(@RequestParam String name,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "3") Integer pageSize){


        PageInfo<ShowPage> showPagePageInfo = showPageService.selectByNamePic(name, pageNum, pageSize);
        return showPagePageInfo;
    }

    @RequestMapping("/uploadImg")
    public Object upLoadImg (@RequestParam(value = "upLoad") MultipartFile multipartFile,HttpServletRequest request){

        /*获取文件名*/
        String saveFileName = multipartFile.getOriginalFilename();
        /*图片名字*/
        name = UUIDUtil.getUUID()+saveFileName.substring(saveFileName.lastIndexOf("."));
        /* 图片存放的路径 + 文件名 */
        String path=PIC_BASE_PATH+name;
        /*定义保存的路径*/
//        String path = "E:\\IDEA\\showpage\\src\\main\\resources\\static\\img";

        try {
            /*转存到自己设置的路径中*/
            multipartFile.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回客户端JSON对象,封装图片的路径,为了在页面实现立即回显
        JSONObject object = new JSONObject();
        try {
            object.put("imgurl", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object.toString();
    }

    @PostMapping("/addInfo")
    public int addInfo(@RequestBody ShowPage showPage){
        /**
         * 1、获取img的地址
         * 2、调用set方法设置 img 的值
         * 3、调用service、dao方法保存到数据库中
         */
        String imgUrl = "../img/"+name;
        showPage.setImg(imgUrl);
        int i = showPageService.addInfo(showPage);
        return i;

    }

    @GetMapping("/delete")
    public void deletePic(@RequestParam Integer id){
        showPageService.deletePicById(id);
    }

    @PutMapping("/update")
    public void updatePic(@RequestBody ShowPage showPage){
        showPageService.updatePic(showPage);
    }


}

package com.books.App.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.books.App.common.Result;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${server.port}")
    private String port;

    private static final String ip="http://localhost";
    /*
    * 输上传接口
    * */
    @RequestMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
//        System.out.println(multipartFile);
//        System.out.println("1111111111111111");
//
//        String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1);
//        System.out.println(fileExt);
        String Filename=multipartFile.getOriginalFilename();//获取原文件的名称
//        System.out.println(multipartFile.getOriginalFilename()+"11111111");
        //定义文件的唯一标识
        String flag=IdUtil.fastSimpleUUID();
        String RootFile=System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"-"+Filename;//获取上传文件的路径
        FileUtil.writeBytes(multipartFile.getBytes(),RootFile);//把文件写入到上传的路径
        return Result.success().put("data",ip+":"+port+"/file/"+flag);
    }
    /*
    * 下载接口
    * */
    @GetMapping("/{flag}")
    public void getFiles(HttpServletResponse response,@PathVariable String flag){
        OutputStream os;//新建一个输出流对象
        String basePath=System.getProperty("user.dir")+"/src/main/resources/files/";//定义文件上传的根路径

        List<String> fileNames=FileUtil.listFileNames(basePath);//获取到所有的文件名称
        String fileName=fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);//通过文件路径读取文件字节流
                os = response.getOutputStream();//通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();

            }
        }catch (Exception e){
            System.out.println("文件下载失败！！！");
        }
    }
}

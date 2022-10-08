package com.books.App.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.books.App.common.excel.CustomHandler;
import com.books.App.common.excel.EasyExcelUtils;
import com.books.App.entity.Books;
import com.books.App.entity.User;
import com.books.App.service.BooksService;
import com.books.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class Test {

    @Autowired
    private UserService userService;

    @Autowired
    private BooksService booksService;

    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        List<Books> users=booksService.getBaseMapper().selectList(null);
        System.out.println(users);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String transformDate = simpleDateFormat.format(new Date());
        String fileName = "客户表格" + transformDate;
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;fileName=" + fileName + ".xlsx");

//        //根据用户传入字段 假设我们要忽略 date
//        Set<String> excludeColumnFiledNames = new HashSet<String>();
//        excludeColumnFiledNames.add("date");
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        EasyExcel.write(fileName, DemoData.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板")
//                .doWrite(data());
//        // 根据用户传入字段 假设我们只要导出 date
//        Set<String> includeColumnFiledNames = new HashSet<String>();
//        includeColumnFiledNames.add("date");

        // 标题样式
        WriteCellStyle headWriteCellStyle = EasyExcelUtils.getHeadStyle();
        // 内容样式
        WriteCellStyle contentWriteCellStyle = EasyExcelUtils.getContentStyle();
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        CustomHandler customHandler = new CustomHandler(headWriteCellStyle, contentWriteCellStyle);
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("id");
        excludeColumnFiledNames.add("userid");
        EasyExcel.write(response.getOutputStream(), Books.class)
                 .excludeColumnFiledNames(excludeColumnFiledNames)
//                使用LongestMatchColumnWidthStyleStrategy 进行列宽自适应设置,同样进行注册
                 .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                 .registerWriteHandler(customHandler)
                 .sheet("模板").doWrite(users);
    }
}

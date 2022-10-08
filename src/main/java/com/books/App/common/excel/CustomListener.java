package com.books.App.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.books.App.entity.Books;

class CustomListener extends AnalysisEventListener<Books> {



    @Override
    public void invoke(Books books, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("数据解析完成");
    }
}

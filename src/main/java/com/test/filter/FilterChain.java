package com.test.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author riemann
 * @date 2019/04/14 21:22
 *
 * 过滤链条
 */
public class FilterChain implements Filter{
    //用List集合来存储过滤规则
     List<Filter> filters = new ArrayList<Filter>();
    //用于标记规则的引用顺序
    int index = 0;
    //往规则链条中添加规则
    public FilterChain addFilter(Filter f) {
        filters.add(f);
        //代码的设计技巧:Chain链添加过滤规则结束后返回添加后的Chain，方便我们下面doFilter函数的操作
        return this;
    }
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        //index初始化为0,filters.size()为3，不会执行return操作
        if (index == filters.size()) {
            return;
        }
        //每添加一个过滤规则，index自增1
        Filter f = filters.get(index);
        index++;
        //根据索引值获取对应的规律规则对字符串进行处理
        f.doFilter(request, response, chain);
    }
}

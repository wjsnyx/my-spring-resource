package com.nyx.originclass;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author niuyx
 * @Classname InputStreamSource
 * @Description InputStreamSource
 * @date 2019/8/26
 */
public interface InputStreamSource {

    //提供一个总接口 根据解析的内容获取一个流
    //getInputStream()

    InputStream getInputStream() throws IOException;
}

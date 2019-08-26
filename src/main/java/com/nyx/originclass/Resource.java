package com.nyx.originclass;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * @author niuyx
 * @Classname Resource
 * @Description Resource接口
 * @date 2019/8/26
 */
public interface Resource extends InputStreamSource{


    //判断文件是否存在
    boolean exists();

    //判断文件是否可读
    boolean isReadable();

    //判断文件是否打开
    boolean isOpen();

    //从当前resource中获取URL
    URL getURL() throws IOException;

    //从当前资源中获取URI
    URI getURI() throws IOException;

    //从当前resource中获取File
    File getFile() throws IOException;

    // 获取资源长度
    long contentLength() throws IOException;

    // 获取文件的最后修改时间
    long lastModified() throws IOException;

    // 根据相对路径 创建相对于此资源的资源
    Resource createRelative(String relativePath) throws IOException;

    // 返回资源文件的名称
    String getFilename();

    // 获取资源描述
    String getDescription();

}

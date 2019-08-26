package com.nyx.originclass;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author niuyx
 * @Classname WritableResource
 * @Description Resource获取输入字节流
 *              WritableResource获取输出字节流
 * @date 2019/8/26
 */
public interface WritableResource extends Resource {

    // 是否可写
    boolean isWritable();

    // 获取输出流
    OutputStream getOutputStream() throws IOException;
}

package com.nyx.originclass;

import com.nyx.util.Assert;
import com.nyx.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * @author niuyx
 * @Classname EncodeResource
 * @Description EncodeResource 包装Resource,Charset,encoding
 *  Resource获取的是字节流，通过本类可以转化为字符流
 * @date 2019/8/26
 */
public class EncodedResource implements InputStreamSource {

    // Resource
    private final Resource resource;

    // 编码
    private final String encoding;

    // 字符
    private final Charset charset;

    //三种属性的get方法
    public final Resource getResource() {
        return resource;
    }

    public final String getEncoding() {
        return encoding;
    }

    public final Charset getCharset() {
        return charset;
    }

    public EncodedResource(Resource resource) {
        this(resource, null, null);
    }

    public EncodedResource(Resource resource, String encoding) {
        this(resource, encoding, null);
    }

    public EncodedResource(Resource resource, Charset charset) {
        this(resource, null, charset);
    }

    private EncodedResource(Resource resource, String encoding, Charset charset) {
        super();
        Assert.notNull(resource, "Resource must not be null");
        this.resource = resource;
        this.encoding = encoding;
        this.charset = charset;
    }
    // 实现总接口的方法获取流不过这个流依旧是从Resource中获取
    @Override
    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }

    //////////////////////////////////////////
    //                获取字符流
    /////////////////////////////////////////
    public boolean requiresReader() {
        return (this.encoding != null || this.charset != null);
    }

    // 获取Reader通过字节流向字符流转化
    public Reader getReader() throws IOException {
        if (this.charset != null) {
            return new InputStreamReader(this.resource.getInputStream(), this.charset);
        }
        else if (this.encoding != null) {
            return new InputStreamReader(this.resource.getInputStream(), this.encoding);
        }
        else {
            return new InputStreamReader(this.resource.getInputStream());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EncodedResource)) {
            return false;
        }
        EncodedResource otherResource = (EncodedResource) other;
        return (this.resource.equals(otherResource.resource) &&
                ObjectUtils.nullSafeEquals(this.charset, otherResource.charset) &&
                ObjectUtils.nullSafeEquals(this.encoding, otherResource.encoding));
    }

    @Override
    public int hashCode() {
        return this.resource.hashCode();
    }

    @Override
    public String toString() {
        return this.resource.toString();
    }
}

package com.nyx.originclass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author niuyx
 * @Classname ByteArrayResource
 * @Description TODO
 * @date 2019/8/26
 */
public class ByteArrayResource extends AbstractResource {

    // 属性
    private final byte[] byteArray;

    private final String description;


    public ByteArrayResource(byte[] byteArray) {
        this(byteArray, "resource loaded from byte array");
    }

    public ByteArrayResource(byte[] byteArray, String description) {
        if (byteArray == null) {
            throw new IllegalArgumentException("Byte array must not be null");
        }
        this.byteArray = byteArray;
        this.description = (description != null ? description : "");
    }

    public final byte[] getByteArray() {
        return this.byteArray;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public long contentLength() {
        return this.byteArray.length;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.byteArray);
    }

    @Override
    public String getDescription() {
        return "Byte array resource [" + this.description + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof ByteArrayResource && Arrays.equals(((ByteArrayResource) obj).byteArray, this.byteArray)));
    }

    @Override
    public int hashCode() {
        return (byte[].class.hashCode() * 29 * this.byteArray.length);
    }
}

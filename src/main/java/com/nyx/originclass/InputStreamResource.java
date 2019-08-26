package com.nyx.originclass;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author niuyx
 * @Classname InputStreamResource
 * @Description TODO
 * @date 2019/8/26
 */
public class InputStreamResource extends AbstractResource {

    // 属性以及get
    private final InputStream inputStream;

    private final String description;

    private boolean read = false;


    @Override
    public InputStream getInputStream() throws IOException, IllegalStateException {
        if (this.read) {
            throw new IllegalStateException("InputStream has already been read - " +
                    "do not use InputStreamResource if a stream needs to be read multiple times");
        }
        this.read = true;
        return this.inputStream;
    }

    @Override
    public String getDescription() {
        return "InputStream resource [" + this.description + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof InputStreamResource && ((InputStreamResource) obj).inputStream.equals(this.inputStream)));
    }

    @Override
    public int hashCode() {
        return this.inputStream.hashCode();
    }

    // 构造方法
    public InputStreamResource(InputStream inputStream) {
        this(inputStream, "resource loaded through InputStream");
    }

    public InputStreamResource(InputStream inputStream, String description) {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null");
        }
        this.inputStream = inputStream;
        this.description = (description != null ? description : "");
    }
}

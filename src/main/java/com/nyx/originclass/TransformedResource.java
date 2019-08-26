package com.nyx.originclass;

import java.io.IOException;

/**
 * @author niuyx
 * @Classname TransformedResource
 * @Description TODO
 * @date 2019/8/26
 */
public class TransformedResource extends ByteArrayResource {

    private final String filename;

    private final long lastModified;

    public TransformedResource(Resource original, byte[] transformedContent) {
        super(transformedContent);
        this.filename = original.getFilename();
        try {
            this.lastModified = original.lastModified();
        }
        catch (IOException ex) {
            // should never happen
            throw new IllegalArgumentException(ex);
        }
    }
}

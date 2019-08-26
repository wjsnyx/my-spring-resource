package com.nyx.originclass;

import com.nyx.util.Assert;
import com.nyx.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * @author niuyx
 * @Classname FileSystemResource
 * @Description TODO
 * @date 2019/8/26
 */
public class FileSystemResource extends AbstractResource implements WritableResource {

    // 属性以及get
    private final File file;

    private final String path;

    public String getPath() {
        return this.path;
    }

    @Override
    public File getFile() {
        return this.file;
    }

    // 构造方法
    public FileSystemResource(File file) {
        Assert.notNull(file, "File must not be null");
        this.file = file;
        this.path = StringUtils.cleanPath(file.getPath());
    }

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.file = new File(path);
        this.path = StringUtils.cleanPath(path);
    }

    @Override
    public boolean exists() {
        return this.file.exists();
    }

    @Override
    public boolean isReadable() {
        return (this.file.canRead() && !this.file.isDirectory());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public boolean isWritable() {
        return (this.file.canWrite() && !this.file.isDirectory());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.file);
    }

    @Override
    public URL getURL() throws IOException {
        return this.file.toURI().toURL();
    }

    @Override
    public URI getURI() throws IOException {
        return this.file.toURI();
    }

    @Override
    public long contentLength() throws IOException {
        return this.file.length();
    }

    @Override
    public Resource createRelative(String relativePath) {
        String pathToUse = StringUtils.applyRelativePath(this.path, relativePath);
        return new FileSystemResource(pathToUse);
    }

    @Override
    public String getFilename() {
        return this.file.getName();
    }

    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }


    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof FileSystemResource && this.path.equals(((FileSystemResource) obj).path)));
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
}

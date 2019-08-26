package com.nyx.originclass;

import com.nyx.util.Assert;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author niuyx
 * @Classname PathResource
 * @Description TODO
 * @date 2019/8/26
 */
public class PathResource extends AbstractResource implements WritableResource {

    // 资源文件路径
    private final Path path;

    public String getPath() {
        return this.path.toString();
    }

    //PathResource的构造方法
    public PathResource(Path path) {
        Assert.notNull(path, "Path must not be null");
        this.path = path.normalize();
    }

    public PathResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.path = Paths.get(path).normalize();
    }

    public PathResource(URI uri) {
        Assert.notNull(uri, "URI must not be null");
        this.path = Paths.get(uri).normalize();
    }



    @Override
    public boolean exists() {
        return Files.exists(this.path);
    }

    @Override
    public boolean isReadable() {
        return (Files.isReadable(this.path) && !Files.isDirectory(this.path));
    }


    @Override
    public InputStream getInputStream() throws IOException {
        if (!exists()) {
            throw new FileNotFoundException(getPath() + " (no such file or directory)");
        }
        if (Files.isDirectory(this.path)) {
            throw new FileNotFoundException(getPath() + " (is a directory)");
        }
        return Files.newInputStream(this.path);
    }

    @Override
    public boolean isWritable() {
        return (Files.isWritable(this.path) && !Files.isDirectory(this.path));
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        if (Files.isDirectory(this.path)) {
            throw new FileNotFoundException(getPath() + " (is a directory)");
        }
        return Files.newOutputStream(this.path);
    }

    @Override
    public URL getURL() throws IOException {
        return this.path.toUri().toURL();
    }


    @Override
    public URI getURI() throws IOException {
        return this.path.toUri();
    }

    @Override
    public File getFile() throws IOException {
        try {
            return this.path.toFile();
        }
        catch (UnsupportedOperationException ex) {
            // Only paths on the default file system can be converted to a File:
            // Do exception translation for cases where conversion is not possible.
            throw new FileNotFoundException(this.path + " cannot be resolved to " + "absolute file path");
        }
    }

    @Override
    public long contentLength() throws IOException {
        return Files.size(this.path);
    }

    @Override
    public long lastModified() throws IOException {
        // We can not use the superclass method since it uses conversion to a File and
        // only a Path on the default file system can be converted to a File...
        return Files.getLastModifiedTime(path).toMillis();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        return new PathResource(this.path.resolve(relativePath));
    }

    @Override
    public String getFilename() {
        return this.path.getFileName().toString();
    }

    @Override
    public String getDescription() {
        return "path [" + this.path.toAbsolutePath() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj ||
                (obj instanceof PathResource && this.path.equals(((PathResource) obj).path)));
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
}

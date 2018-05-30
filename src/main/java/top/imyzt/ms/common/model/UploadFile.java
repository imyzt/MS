package top.imyzt.ms.common.model;

import java.io.Serializable;

/**
 * <p>
 * 上传文件信息实体类
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class UploadFile implements Serializable{

    /**
     * 序列化ID
     */
    private Integer id;

    /**
     * 文件名称(没有文件后缀)
     */
    private String fileName;

    /**
     * 文件全名称(有文件后缀)
     */
    private String fullName;

    /**
     * 使用UUID生成的文件名称(有文件后缀)
     */
    private String UUIDName;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件存储路径 (可用于后续存储数据库之后回显) <br\>
     * UPLOAD_FILE_SAVE_PATH + 年 + 月 + 日 + UUIDName
     */
    private transient String fileSavePath;

    public Integer getId() {
        return id;
    }

    public UploadFile() {
    }

    public UploadFile(Integer id, String fileName, String fullName, String UUIDName, String suffix, long fileSize, String fileSavePath) {
        this.id = id;
        this.fileName = fileName;
        this.fullName = fullName;
        this.UUIDName = UUIDName;
        this.suffix = suffix;
        this.fileSize = fileSize;
        this.fileSavePath = fileSavePath;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", UUIDName='" + UUIDName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", fileSize=" + fileSize +
                ", fileSavePath='" + fileSavePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UploadFile that = (UploadFile) o;

        if (fileSize != that.fileSize) return false;
        if (!id.equals(that.id)) return false;
        if (!fileName.equals(that.fileName)) return false;
        if (!fullName.equals(that.fullName)) return false;
        if (!UUIDName.equals(that.UUIDName)) return false;
        if (!suffix.equals(that.suffix)) return false;
        return fileSavePath.equals(that.fileSavePath);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fileName.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + UUIDName.hashCode();
        result = 31 * result + suffix.hashCode();
        result = 31 * result + (int) (fileSize ^ (fileSize >>> 32));
        result = 31 * result + fileSavePath.hashCode();
        return result;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUUIDName() {
        return UUIDName;
    }

    public void setUUIDName(String UUIDName) {
        this.UUIDName = UUIDName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSavePath() {
        return fileSavePath;
    }

    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }
}

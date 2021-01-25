package site.qifen.qiaqia.data;

import java.io.Serializable;

public class Result<D> implements Serializable {
    int code;
    String message;
    D data;

    public Result(int code, String message, D data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}

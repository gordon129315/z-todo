package zz.common.net;

public class Response {

    public final static  String success = "success";
    public final static  String failure = "failure";

    private String status;
    private String data;

    public boolean isSuccess() {
        return status.equals(success);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}

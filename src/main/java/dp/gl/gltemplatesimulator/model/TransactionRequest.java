package dp.gl.gltemplatesimulator.model;

public class TransactionRequest {
    String requestid;
    Integer amount;
    String json;

    public TransactionRequest() {
    }

    public TransactionRequest(Integer amount, String json) {
        this.amount = amount;
        this.json = json;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}

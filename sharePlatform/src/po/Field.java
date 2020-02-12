package po;

public class Field {
    private String fieldcn;

    private String fielden;

    public String getFieldcn() {
        return fieldcn;
    }

    public void setFieldcn(String fieldcn) {
        this.fieldcn = fieldcn == null ? null : fieldcn.trim();
    }

    public String getFielden() {
        return fielden;
    }

    public void setFielden(String fielden) {
        this.fielden = fielden == null ? null : fielden.trim();
    }
}
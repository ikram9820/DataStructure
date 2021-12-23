package ik.tech.datastructure.models;

public class CodeModel {
    String codeId;
    String code;

    public  CodeModel(){}
    public CodeModel(String codeId, String code) {
        this.codeId = codeId;
        this.code = code;
    }

    public String getCodeId() {
        return codeId;
    }

    public String getCode() {
        return code;
    }
}

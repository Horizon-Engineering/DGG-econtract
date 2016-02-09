package utils.pojos;

import java.util.ArrayList;

public class FieldPOJO {

    private String tag;
    private String hint;
    private String value;
    private String ipType;
    private String fType;
    private ArrayList<StringWithID> listValues;
    private boolean isMandatory;

    private String name;
    private String description;
    private String mandatory;
    private String comment;
    private String valueType;
    long id;

    public FieldPOJO(long id, String name, String description, String mandatory, String comment, String value_type) {
        super();
        this.name = name;
        this.description = description;
        this.mandatory = mandatory;
        this.comment = comment;
        this.valueType = value_type;
        this.id = id;
    }

    public FieldPOJO() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setIpType(String ipType) {
        this.ipType = ipType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public ArrayList<StringWithID> getListValues() {
        return listValues;
    }

    public void setListValues(ArrayList<StringWithID> listValues) {
        this.listValues = listValues;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String csvString() {
        final String COMMA_DELIMITER = ",";
        StringBuilder builder = new StringBuilder();
        builder.append('\n');
        builder.append(id);
        builder.append(COMMA_DELIMITER);
        builder.append(fixCSVString(name));
        builder.append(COMMA_DELIMITER);
        builder.append(fixCSVString(description));
        builder.append(COMMA_DELIMITER);
        builder.append(fixCSVString(mandatory));
        builder.append(COMMA_DELIMITER);
        builder.append(fixCSVString(comment));
        builder.append(COMMA_DELIMITER);
        builder.append(fixCSVString(valueType));
        return builder.toString();
    }

    private String fixCSVString(String data) {
        if (data.contains(",")) {
            return "\"" + data + "\"";
        }
        return data;
    }
}

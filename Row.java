public class Row {
    private String person;
    private String type;
    private String account;

    public Row(String person, String type, String account) {
        this.person = person;
        this.type = type;
        this.account = account;
    }

    public String getPerson() {
        return person;
    }

    public String getType() {
        return type;
    }

    public String getAccount() {
        return account;
    }
}
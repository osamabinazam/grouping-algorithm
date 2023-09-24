import java.util.List;

public class Type {
    String type;
    List<String> accounts;
    List<String> persons;
    public Type(String type, List<String> accounts, List<String> persons) {
        this.type = type;
        this.accounts = accounts;
        this.persons = persons;
    }

    public String toString(){
        String result="";
        result += "Type : " +this.type+"\nAssociated Accounts : ";
        for (String string : accounts) {
            result+=string+", ";
        }
        result+= "\nPersons Associated : ";
        for (String string : persons) {
            result+=string+", ";
        }

        result+="\n";
        return result;
    }
    
}




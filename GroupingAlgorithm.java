
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupingAlgorithm {

    public static void main(String[] args) {

        List<Row> rows = new ArrayList<Row>();

        // For Three Person

        rows.add((new Row("P1", "T1", "A1111")));
        rows.add(new Row("P2", "T1", "A5000"));
        rows.add((new Row("P3", "T1", "A1111")));

        rows.add(new Row("P1", "T2", "A4444"));
        rows.add(new Row("P2", "T2", "A5000"));
        rows.add(new Row("P3", "T2", "A5000"));

        rows.add(new Row("P1", "T3", "A4444"));
        rows.add(new Row("P2", "T3", "A5000"));
        rows.add(new Row("P3", "T3", "A5000"));

        rows.add(new Row("P1", "T5", ""));
        rows.add(new Row("P2", "T5", "A5000"));
        rows.add(new Row("P3", "T5", ""));

        rows.add(new Row("P1", "T6", ""));
        rows.add(new Row("P2", "T6", "A5555"));
        rows.add(new Row("P3", "T6", "A5555"));

        rows.add(new Row("P1", "T8", "A6665"));
        rows.add(new Row("P2", "T8", "A5000"));
        rows.add(new Row("P3", "T8", "A5000"));

        rows.add(new Row("P1", "T9", ""));
        rows.add(new Row("P2", "T9", "A5000"));
        rows.add(new Row("P3", "T9", ""));

        System.out.println("For Three Persons");
        System.out.println("Groups: \n");
        groupingAlgorithm(rows);        //Calling Grouping Algorithm
        rows.clear();

        // For Two Person
        rows.add((new Row("P1", "T1", "A1111")));
        rows.add(new Row("P2", "T1", "A5000"));


        rows.add(new Row("P1", "T2", "A4444"));
        rows.add(new Row("P2", "T2", "A5000"));

        rows.add(new Row("P1", "T3", "A4444"));
        rows.add(new Row("P2", "T3", "A5000"));

        rows.add(new Row("P1", "T5", "A5000"));
        rows.add(new Row("P2", "T5", ""));

        rows.add(new Row("P1", "T6", ""));
        rows.add(new Row("P2", "T6", "A5555"));

        rows.add(new Row("P1", "T8", ""));
        rows.add(new Row("P2", "T8", "A5000"));

        rows.add(new Row("P1", "T9", ""));
        rows.add(new Row("P2", "T9", "A5000"));

        rows.add(new Row("P1", "T10", "A5000"));
        rows.add(new Row("P2", "T10", "A9999"));

        System.out.println("For Two Persons");
        System.out.println("Groups: \n");
        groupingAlgorithm(rows);   

        
    }

    public static void groupingAlgorithm(List<Row> rows) {
        Map<String, Integer> Group = new HashMap<String, Integer>();
        List<Group> groups = new ArrayList<>();
        List<Type> types = new ArrayList<>();

        // First Get All Types with respective accounts and persons
        for (Row row : rows) {
            String person = row.getPerson();
            String type = row.getType();
            String account = row.getAccount();
            ArrayList<String> persons = new ArrayList<>();
            ArrayList<String> accounts = new ArrayList<>();
            boolean flag = false;
            // Gettting Data Associated To A Type
            for (Row innerRow : rows) {

                if (innerRow.getType().equals(type)) {
                    // flag= true;
                    persons.add(innerRow.getPerson());
                    accounts.add(innerRow.getAccount());

                }
            }

            for (Type type2 : types) {
                if ((type2.type == type)) {
                    flag = true;
                }
            }
            if (!flag) {
                Type newType = new Type(type, accounts, persons);
                types.add(newType);
            }

        }

        // Now we have all types with respective accounts and persons
        // Now we have to group them
        int groupNumber = 1;
        for (Type type : types) {

            if (groups.isEmpty()) {
                List<Type> associatedType = new ArrayList<>();
                associatedType.add(type);
                groups.add(new Group(groupNumber, associatedType));
                groupNumber++;
            } else {

                Boolean canNotAssiggn= true;
                for (int i = 0; i < groups.size(); i++) {
                    if (canAssignToExistingGroup(type,groups.get(i))){
                        canNotAssiggn=false;
                        groups.get(i).associatedTypes.add(type);
                        break;
                    }
                }

                if (canNotAssiggn){
                    List<Type> associatedType = new ArrayList<>();
                    associatedType.add(type);
                    groups.add(new Group(groupNumber, associatedType));
                    groupNumber++;
                }

            }
            
        }

        // Printing Groups
        String result="";
        for (Group g : groups) {
            result+= "Group : "+g.grouNumber+", ";
            for (Type type : g.associatedTypes) {
                result+= type.type+" ";
            }
            result+="\n";
        }
        System.out.println(result);
    }

    public static Boolean canAssignToExistingGroup(Type targetType, Group group){

        List<String> targetAccounts = targetType.accounts;
        List<Type> groupTypes = group.associatedTypes;


        Boolean [] flags = new Boolean[targetAccounts.size()];
        for(int i=0; i<flags.length; i++){
            flags[i]=true;
        }
        

        // When All Values are Equal
        for (Type type : groupTypes) {
            List<String> existingTypeAccounts = type.accounts;
            for(int i=0; i<existingTypeAccounts.size(); i++){
                if (!(existingTypeAccounts.get(i).equals(targetAccounts.get(i)))){
                    // return false;
                    flags[i]=false;
                }
            }
        }
        Boolean flag=true;
        for (Boolean boolean1 : flags) {
            if (!boolean1.equals(false)){
                flag=false;
            }
        }
        if (flag){
            return false;
        }
        else{
        // Checking Empty Values and Assigning it to Existing Group
        for (int i=0; i<targetType.accounts.size();i++){
            if (targetType.accounts.get(i).equals("")){
                continue;
            }
            else{
                // Index where a account value found and that index represet the col number where we have to search for match
                int index = i;
                for (Type type : groupTypes) {
                    List<String> existingTypeAccounts = type.accounts;
                    for (int j = 0; j < existingTypeAccounts.size(); j++) {
                        if (!existingTypeAccounts.get(index).equals(targetAccounts.get(index))){
                            return false;
                        }
                    }
                }

            }
        }
    }
        return true;
    }

}



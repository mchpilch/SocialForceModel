package core.MathTool;

public class ExcelFriendly {
    public static void main(String[] args) {
        String data = "";
        if(data.charAt(0) == '[' && data.charAt(data.length()-1) == ']'){
            data = data.substring(1,data.length()-1); //pozbycie się []
        }
        String[] splittedResults = data.split(",");
        System.out.println(splittedResults.length);
        for(int i = 0; i < splittedResults.length; i++){
            splittedResults[i] = splittedResults[i].replace(".",",");
        }
        System.out.println("Początek");
        for(int i = 0; i < splittedResults.length; i++){
            System.out.print(splittedResults[i]+ " \n") ;
        }
        System.out.println("Koniec");
    }
}

package core.MathTool;

import java.util.UUID;

public class UniqueID {
    public static void main(String[] args) {
        int k = 10;
        while(k > 0){
            UUID uniqueKey = UUID.randomUUID();
            System.out.println (k + ":  " +  uniqueKey);
            k--;
        }
        }

}

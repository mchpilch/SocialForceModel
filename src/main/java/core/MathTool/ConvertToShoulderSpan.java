package core.MathTool;

public class ConvertToShoulderSpan {
    static float OldMax = 33; //32 jeden zapasu
    static float OldMin = 13; //14 jeden zapasu
    static float OldRange = 0;

    static float NewMin = 0.65f ;
    static float NewMax = 0.9f;
    static float NewRange = 0;

    static float OldValue = 29;
    static float NewValue = 0 ;

    public static void main(String[] args) {
        OldRange = (OldMax - OldMin);
        if (OldRange == 0)
            NewValue = NewMin;
        else
        {
            NewRange = (NewMax - NewMin);
            NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin;
        }
        System.out.println(NewValue);
        float roundOff = (float) (Math.round(NewValue * 100.0) / 100.0);
        System.out.println(roundOff);
    }

    public static float convertToShoulderSpan(float B){
        OldValue = B;
        OldRange = (OldMax - OldMin);
        if (OldRange == 0)
            NewValue = NewMin;
        else
        {
            NewRange = (NewMax - NewMin);
            NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin;
        }
        float roundNewValue = (float) (Math.round(NewValue * 100.0) / 100.0);//dwa miejsca po przecinku
        return roundNewValue;
    }
}

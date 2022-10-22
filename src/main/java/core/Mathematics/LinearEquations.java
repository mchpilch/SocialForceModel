package core.Mathematics;

public class LinearEquations
{
    //https://danceswithcode.net/engineeringnotes/linear_equations/linear_equations.html
    
    public static void main(String[] args) {
        System.out.println(3/4f);
        System.out.println(-4/3f);
        float[] answer = LinearEquations.solve2x2LinearEquation( 3/4f, 1f, -4/3f, 1f, 5f, -23f );//

        if( answer == null ){
            System.out.println( "No unique solution exists" );
        }
        else
        {
            System.out.println("Punkt przecięcia dwóch prostych ");
            System.out.println( "x1 = Xsz "  + answer[0] );	//x1 = -2
            System.out.println( "x2 = Ysz "  + answer[1] );	//x2 = -4
        }
    }

    public static float[] solve2x2LinearEquation(
            float a11, float a12,
            float a21, float a22,
            float k1,  float k2 )
    {
        float det = findDet2x2( a11, a12, a21, a22 );
        if( det == 0.0 )
        {
            return( null );    //No unique solution
        }
        float[] result = { (k1*a22 - k2*a12) / det, (k2*a11 - k1*a21) / det };
        return( result );
    }

    private static float findDet2x2( float a11, float a12, float a21, float a22 ){
        return( a11*a22 - a21*a12 );
    }
}
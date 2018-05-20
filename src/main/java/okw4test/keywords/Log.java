package okw4test.keywords;

public class Log extends AbstarctKeyWord
{
    public Log( String FN, String Value )
    {
        this.setFN( FN );
        this.setValue( Value );
    }

    public String writeJUnit()
    {
        return "\n        // =================================================================="
             + "\n        // " + this.getFN() + " - " + this.getValue() 
             + "\n        // ==================================================================";
    }

}

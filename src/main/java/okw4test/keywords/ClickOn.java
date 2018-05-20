package okw4test.keywords;

public class ClickOn extends AbstarctKeyWord
{
    public ClickOn( String FN )
    {
        this.setFN( FN );
    }
    
    public String writeJUnit()
    {
        return "        " + this.getKeyWordName() + "( \"" + this.getFN() + "\" )\n";
    }
}

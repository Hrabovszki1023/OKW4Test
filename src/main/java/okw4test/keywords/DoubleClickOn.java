package okw4test.keywords;

public class DoubleClickOn extends AbstarctKeyWord
{
    public DoubleClickOn( String FN )
    {
        this.setValue( FN );
    }

    public String writeJUnit()
    {
        return "        " + this.getKeyWordName() + "( \"" + this.getFN() + "\" )\n";
    }
}

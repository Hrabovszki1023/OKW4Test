package okw4test.keywords;

public class SetFocus extends AbstarctKeyWord
{
    public SetFocus( String FN )
    {
        this.setValue( FN );
    }

    public String writeJUnit()
    {
        return "        " + this.getKeyWordName() + "( \"" + this.getFN() + "\" )\"";
    }
}

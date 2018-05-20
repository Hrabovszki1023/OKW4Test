package okw4test.keywords;

public class SelectWindow extends AbstarctKeyWord
{
    public SelectWindow( String FN )
    {
        this.setFN( FN );
    }

    public String writeJUnit()
    {
        return "\n" + "        " + this.getKeyWordName() + "( \"" + this.getFN() + "\" )\n";
    }
}

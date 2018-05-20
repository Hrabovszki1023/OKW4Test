package okw4test.keywords;

public class BeginTestcase extends AbstarctKeyWord
{

    public BeginTestcase( String Value )
    {
        this.setValue( Value );
    }
    
    public String writeJUnit()
    {
        return "        " + this.getKeyWordName() + "( \"" + this.getValue() + "\" )\n";
    }
}

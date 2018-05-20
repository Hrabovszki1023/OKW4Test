package okw4test.keywords;

public class EndTestcase extends AbstarctKeyWord
{
    public EndTestcase( )
    {
        
    }

    public String writeJUnit()
    {
        return "\n" + "        " + this.getKeyWordName() + "(  )\n";
    }
}

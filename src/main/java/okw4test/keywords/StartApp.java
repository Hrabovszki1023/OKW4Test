package okw4test.keywords;

public class StartApp extends AbstarctKeyWord
{
    
    public StartApp( String AN )
    {
        this.setFN( AN );
    }

    public String writeJUnit()
    {
        return "\n" + "        " + this.getKeyWordName() + "( \"" + this.getFN() + "\" )\n";
    }

}

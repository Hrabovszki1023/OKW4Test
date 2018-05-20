package okw4test.keywords;

public class StopApp extends AbstarctKeyWord
{
    public StopApp( String AN )
    {
        this.setFN( AN );
    }

    public String writeJUnit()
    {
        return "        " + this.getKeyWordName() + "( \"" + this.getFN() + "\" )\n";
    }

}

package okw4test.keywords;

public abstract class AbstarctKeyWord
{
    private String KeyWordName = "EN." + this.getClass().getSimpleName();
    public String getKeyWordName()
    {
        return KeyWordName;
    }
    public void setKeyWordName( String keyWordName )
    {
        KeyWordName = keyWordName;
    }
    
    private String FN;
    public String getFN()
    {
        return FN;
    }
    public void setFN( String fN )
    {
        FN = fN;
    }

    private String Value;
    public String getValue()
    {
        return Value;
    }
    public void setValue( String value )
    {
        Value = value;
    }
    
    public String writeJUnit()
    {
        return "        " + this.getKeyWordName() + "( \"" + this.getFN() + "\", \"" + this.getValue() + "\" )\n";
    }
}

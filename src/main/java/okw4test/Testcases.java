package okw4test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;


public class Testcases
{

    private ArrayList<Testcase> Testcases = new ArrayList<Testcase>();
    String SheetName;

    public Testcases( Sheet currentSheet )
    {

        // Variables

        Cell currentCell;
        String currentCellValue;

        // Action
        
        this.SheetName = currentSheet.getSheetName();
        
        for ( int iRow = 0; iRow < currentSheet.getLastRowNum(); iRow++ )
        {

            Row currentRow = currentSheet.getRow( iRow );

            if ( currentRow != null )
            {
                currentCell = currentRow.getCell( 0, MissingCellPolicy.CREATE_NULL_AS_BLANK );
                currentCellValue = currentCell.toString();

                if ( "Test case".equals( currentCellValue ) )
                {
                    System.out.println( "   Testcase: '" + currentRow.getCell( 1, MissingCellPolicy.CREATE_NULL_AS_BLANK ).getStringCellValue() + "'" );
                    Testcases.add( new Testcase( currentSheet, iRow ) );
                }
            }
        }
    }

    
    public String writeJUnit() throws IOException
    {
        
        StringBuilder myJUnit = new StringBuilder();
        Iterator<Testcase> TestcaseIterator = Testcases.iterator();
        
        GregorianCalendar now = new GregorianCalendar(); 
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG); // 14.04.12 21:34:07 MESZ 

        File file = new File( SheetName + ".java");
        System.out.println( SheetName + ".java" );

        // Generate JUnit-testclass
        myJUnit.append( "package hc.testcases.hc.hcMainPage;\n\n");
        myJUnit.append( "import okw.core.EN;\n\n");

        myJUnit.append( "/*\n");
        myJUnit.append( " * Genereted with 4Test2OKW\n");
        myJUnit.append( " * Generation Date: " + df.format(now.getTime()) + " \n");
        myJUnit.append( " */\n");
        
        myJUnit.append( "public class " + SheetName + "\n { " );
        
        while ( TestcaseIterator.hasNext() )
        {
            Testcase currentTestcase = TestcaseIterator.next();
            
            myJUnit.append( currentTestcase.writeJUnit() );                
        }
        
        myJUnit.append( "} // close class " + SheetName );
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            writer.write( myJUnit.toString());
        }
        
        return myJUnit.toString();
        
    }
}

package okw4test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class okw4test
{

    // private static final String FILE_NAME = "/Users/zoltan/Downloads/4test.xlsx";
    private static final String FILE_NAME = "/Users/zoltan/Downloads/4test___HC.xlsx";

    public static void main( String[] args )
    {

        try
        {
            FileInputStream excelFile = new FileInputStream( new File( FILE_NAME ) );
            Workbook workbook = new XSSFWorkbook( excelFile );

            Iterator<Sheet> sheetIterator = workbook.iterator();

            while ( sheetIterator.hasNext() )
            {
                Sheet currentSheet = sheetIterator.next();

                String lvsSheetName = currentSheet.getSheetName();

                switch ( lvsSheetName )
                {
                    case "Summary":
                        break;

                    default:
                        if ( lvsSheetName.toUpperCase().endsWith( "Test".toUpperCase() ) )
                        {
                            System.out.println( "Sheetname:" + currentSheet.getSheetName() );
                            
                            Testcases myTestCases = new Testcases( currentSheet );
                            
                            System.out.println( myTestCases.writeJUnit() );
                        }
                        break;
                }
            }
            
            workbook.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
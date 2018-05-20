package okw4test;

import okw4test.keywords.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class Testcase
{
    public String getTestcaseName()
    {
        return TestcaseName;
    }

    public void setTestcaseName( String testcaseName )
    {
        TestcaseName = testcaseName.replace( " ", "_" ).replace( "(", "_" ).replace( ")", "_" );
    }

    private String TestcaseName = "";
    
    ArrayList<AbstarctKeyWord> Keywords = new ArrayList<AbstarctKeyWord>();
     
     public Testcase( Sheet currentSheet, int iRow )
     {     

         // Variables
         
         Row currentRow = null;
         
         Cell CellTestCase = null;
         Cell CellFN = null;
         Cell CellValue = null;
         
         // Action

         // Tesfalldaten merken und in die nächste Zeile...
         currentRow = currentSheet.getRow( iRow++ );
         
         // Save Testcase name
         setTestcaseName( currentRow.getCell( 1, MissingCellPolicy.CREATE_NULL_AS_BLANK ).getStringCellValue() );
         
         // Add BegnTestcase as starting keyword
         Keywords.add( new BeginTestcase( TestcaseName ) );
         
         // Testschritte scannen
         for ( int i = iRow; i < currentSheet.getLastRowNum(); i++ )
         {

             currentRow = currentSheet.getRow( i );

             CellTestCase = currentRow.getCell( 0, MissingCellPolicy.CREATE_NULL_AS_BLANK );
             CellFN = currentRow.getCell( 1, MissingCellPolicy.CREATE_NULL_AS_BLANK );
             CellValue = currentRow.getCell( 2, MissingCellPolicy.CREATE_NULL_AS_BLANK );

             if ( "".equals( CellTestCase.toString() ) )
             {
                 getTestStep( CellFN.toString().trim(), CellValue.toString().trim() );
             }
             else if ( "Test case".equals( CellTestCase.toString() ) )
             {
                 // Abbruch + eine Zeile zurück + return da das Nächste Tetscase erreichwurde
                 iRow = i - 1;
                 break;
             }
         }
         
         Keywords.add( new EndTestcase(  ) );
     }
     
     private void getTestStep( String Categorie, String Value )
     {

         String lvsFN;

         if ( Categorie.startsWith( "#" ) )
         {
         if ( Categorie.contains( "#StartApp" ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#StartApp" ); 
             this.Keywords.add( new StartApp( Value ) );
         
             // System.out.println( "    StartApp ( '" + Value + "' )" );
         }

         else if ( Categorie.contains( "#StopApp" ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#StopApp" );
             this.Keywords.add( new StopApp( Value ) );

             // System.out.println( "    StopApp ( '" + Value + "' )" );
         }

         // ClickOn( FN )
         else if ( Categorie.contains( "#ClickOn " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#ClickOn" );
             this.Keywords.add( new ClickOn( Value ) );

             // System.out.println( "    ClickOn ( '" + Value + "' )" );
         }

         // DoubleClickOn( FN )
         else if ( Categorie.contains( "#DoubleClickOn " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#DoubleClickOn" );
             this.Keywords.add( new DoubleClickOn( Value ) );

             // System.out.println( "    DoubleClickOn ( '" + Value + "' )" );
         }

         // SetFocus( FN )
         else if ( Categorie.contains( "#SetFocus " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#SetFocus" );
             this.Keywords.add( new SetFocus( Value ) );

             // System.out.println( "    SetFocus ( '" + Value + "' )" );
         }

         // SetValue( FN, Val )
         else if ( Categorie.contains( "#SetValue " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#SetValue" );
             System.out.println( "    SetValue ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // Select( FN, Val ) - More...
         else if ( Categorie.contains( "#Select " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#Select" );
             this.Keywords.add( new Select( lvsFN, Value ) );
             // System.out.println( "    Select ( '" + lvsFN + "', '" + Value + "' )" );
         }

         /* SelectMenu( FN )
         else if ( Categorie.contains( "#SelectMenu" ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#SelectMenu" );
             System.out.println( "    SelectMenu ( '" + Value + "' )" );
         }
         
         // SelectMenu( FN, Val )
         else if ( Categorie.contains( "#SelectMenu" ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#SelectMenu" );
             System.out.println( "    SelectMenu ( '" + lvsFN + "', '" + Value + "' )" );
         }
         */

         // TypeKey( FN, Val )
         else if ( Categorie.contains( "#TypeKey" ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#TypeKey" );
             this.Keywords.add( new TypeKey( lvsFN, Value ) );
             // System.out.println( "    TypeKey ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyExists( FN, ExpVal)
         else if ( Categorie.contains( "#VerifyExists " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyExists" );
             this.Keywords.add( new VerifyExists( lvsFN, Value ) );
             // System.out.println( "    VerifyExists ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyHasFocus( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyHasFocus " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyHasFocus" );
             this.Keywords.add( new VerifyHasFocus( lvsFN, Value ) );
             // System.out.println( "    VerifyHasFocus ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyIsActive( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyIsActive " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyIsActive" );
             this.Keywords.add( new VerifyIsActive( lvsFN, Value ) );
             // System.out.println( "    VerifyIsActive ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyCaption( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyCaption " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyCaption" );
             this.Keywords.add( new VerifyCaption( lvsFN, Value ) );
             // System.out.println( "    VerifyIsActive ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyCaptionWCM( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyCaptionWCM " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyCaptionWCM" );
             this.Keywords.add( new VerifyCaptionWCM( lvsFN, Value ) );
             // System.out.println( "    VerifyCaptionWCM ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyCaptionREGX( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyCaptionREGX " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyCaptionREGX" );
             this.Keywords.add( new VerifyCaptionREGX( lvsFN, Value ) );
             // System.out.println( "    VerifyCaptionREGX ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyLabel( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyLabel " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyLabel" );
             this.Keywords.add( new VerifyLabel( lvsFN, Value ) );
             // System.out.println( "    VerifyLabel ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyLabelWCM( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyLabelWCM " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyLabelWCM" );
             this.Keywords.add( new VerifyLabelWCM( lvsFN, Value ) );
             // System.out.println( "    VerifyLabelWCM ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyLabelREGX( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyLabelREGX " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyLabelREGX " );
             this.Keywords.add( new VerifyLabelREGX( lvsFN, Value ) );
             // System.out.println( "    VerifyLabelREGX ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyTooltip( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyTooltip " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyTooltip " );
             this.Keywords.add( new VerifyTooltip( lvsFN, Value ) );
             // System.out.println( "    VerifyTooltip ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyTooltipWCM( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyTooltipWCM " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyTooltipWCM" );
             this.Keywords.add( new VerifyLabelWCM( lvsFN, Value ) );
             // System.out.println( "    VerifyTooltipWCM ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyTooltipREGX( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyTooltipREGX " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyTooltipREGX" );
             this.Keywords.add( new VerifyLabelREGX( lvsFN, Value ) );
             // System.out.println( "    VerifyTooltipREGX ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyValue( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyValue " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyValue " );
             this.Keywords.add( new VerifyValue( lvsFN, Value ) );
             // System.out.println( "    VerifyValue ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyValueWCM( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyValueWCM " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyValueWCM" );
             this.Keywords.add( new VerifyValueWCM( lvsFN, Value ) );
             // System.out.println( "    VerifyValueWCM ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // VerifyValueREGX( FN, ExpVal )
         else if ( Categorie.contains( "#VerifyValueREGX " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#VerifyValueREGX" );
             this.Keywords.add( new VerifyValueREGX( lvsFN, Value ) );
             // System.out.println( "    VerifyValueREGX ( '" + lvsFN + "', '" + Value + "' )" );
         }

         // SelectWindow( FN, ExpVal )
         else if ( Categorie.contains( "#SelectWindow " ) )
         {
             lvsFN = getFNFromCategorie( Categorie, "#SelectWindow" );
             this.Keywords.add( new SelectWindow( lvsFN ) );
             // System.out.println( "\n    SelectWindow ( '" + lvsFN + "' )" );
         }
         }
         else
         {
             if ( Categorie.contains( "(I)" ) )
             {
                 lvsFN = Categorie.replace( "(I)", "" ).trim();
                 this.Keywords.add( new SetValue( lvsFN, Value ) );
                 // System.out.println( "    SetValue ( '" + lvsFN + "', '" + Value + "' )" );
             }
             else if ( Categorie.contains( "(O)" ) )
             {
                 lvsFN = Categorie.replace( "(O)", "" ).trim();
                 this.Keywords.add( new VerifyValue( lvsFN, Value ) );
                 // System.out.println( "    VerifyValue ( '" + lvsFN + "', '" + Value + "' )" );
             }
             else if ( Categorie.contains( "(F)" ) )
             {
                 lvsFN = Categorie;
                 this.Keywords.add( new Log( lvsFN, Value ) );
                 // System.out.println( " '" + lvsFN + "', '" + Value + "' )" );
             }
         }
     }
     
     private static String getFNFromCategorie( String fsCategorie, String KeyWord )
     {
         String myFN = fsCategorie.replace( KeyWord, "" );

         myFN = myFN.replace( "(I)", "" );
         myFN = myFN.replace( "(O)", "" );
         myFN = myFN.replace( "(A)", "" );
         myFN = myFN.replace( "(F)", "" );
         
         return myFN.trim();
     }
     
     public String writeJUnit()
     {
         StringBuilder myKeywords = new StringBuilder();
         StringBuilder myJUnit = new StringBuilder();
         
         Iterator<AbstarctKeyWord> KeywordIterator = Keywords.iterator();
         // 
         while ( KeywordIterator.hasNext() )
         {
             AbstarctKeyWord currentKeyword = KeywordIterator.next();
             
             myKeywords.append( currentKeyword.writeJUnit() );              
         }
         
         myJUnit.append( "\n\n    @Test\n" );
         myJUnit.append( "    public void " + TestcaseName + "() throws Exception\n");
         
         myJUnit.append( "    {\n" );
         myJUnit.append( myKeywords );
         myJUnit.append( "    } // close testcase method " + TestcaseName + "\n" );
         
         return myJUnit.toString();
     }
}

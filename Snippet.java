package com.excel.xml.excel2xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Snippet {
	public static void main(String[]args) throws IOException
		{
		
		Snippet s = new Snippet();
		s.exceldata();
		
			
		
		}

	private  void exceldata() throws FileNotFoundException, IOException {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("Book1.xlsx").getFile());
		
		InputStream ExcelFileToRead = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		HashMap<String, String>xmlmap= new HashMap<>();
		//XSSFSheet sheet=wb.getSheetAt(2);
		List<String> sheetNames = new ArrayList<String>();
		for (int i=0; i<wb.getNumberOfSheets(); i++) {
		    sheetNames.add( wb.getSheetName(i) );
		}
		
		for(int i=0; i<wb.getNumberOfSheets(); i++) {
			
			XSSFRow row; 
			XSSFCell cell;
			

			XSSFSheet sheet=wb.getSheetAt(i);
		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();
		
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING && cell.getColumnIndex()==3)
				{
				if(!cell.getStringCellValue().isEmpty()&& !cell.getStringCellValue().equals("input tag")) {
					xmlmap.put(sheetNames.get(i)+" "+Integer.toString(row.getRowNum()+1)+" "+Integer.toString(cell.getColumnIndex()+1), cell.getStringCellValue());
				}//System.out.println(cell.getAddress()+" address idhu aprom "+cell.getColumnIndex());
					//System.out.print(cell.getStringCellValue()+" ");
					System.out.print(xmlmap.toString());
				}
				/*else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}*/
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}
	}
	}
}


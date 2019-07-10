/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfconversion;

/**
 *
 * @author NIHAL SINGH
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;


 public  class MergFile {
     
     public static void Merge(String[] filename,String storepath) throws FileNotFoundException, DocumentException, IOException
     {
         List<InputStream> list=new ArrayList<InputStream>();
         for(int i=0;i<filename.length;i++)
         {
             list.add(new FileInputStream(new File(filename[i])));
         }
          OutputStream out = new FileOutputStream(new File(storepath));
          doMerge(list,out);
     }
     
       public static void doMerge(List<InputStream> list, OutputStream outputStream)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        
        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
                cb.addTemplate(page, 0, 0);
            }
        }
        
        outputStream.flush();
        document.close();
        outputStream.close();
    }

 }
     

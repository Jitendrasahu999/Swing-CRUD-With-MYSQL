package pdfGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class Invoice {

	public static void main(String[] args) {
		Document document = new Document(PageSize.A4, 16f, 16f, 16f, 16f);

		try {
			System.out.println("Pdf created successfully");
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("GMS_Invoice.pdf"));
			document.open();

			float fntSize, lineSpacing;
			fntSize = 18.7f;
			lineSpacing = 10f;
			Paragraph paragraph = new Paragraph(
					new Phrase(lineSpacing, "INVOICE", FontFactory.getFont(FontFactory.COURIER, fntSize)));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			
			// Create Table here.
			PdfPTable pdfPTable = new PdfPTable(4);
			pdfPTable.setWidthPercentage(100);
			pdfPTable.setSpacingBefore(11F);
			pdfPTable.setSpacingAfter(11F);
			float cellWidth [] = {2f, 2f, 2f, 2f};
			pdfPTable.setWidths(cellWidth);
			
			//Create Table Cell Here.
			PdfPCell cell_1 = new PdfPCell(new Paragraph("Cell 1"));
			PdfPCell cell_2 = new PdfPCell(new Paragraph("Cell 2"));
			PdfPCell cell_3 = new PdfPCell(new Paragraph("Cell 3"));
			PdfPCell cell_4 = new PdfPCell(new Paragraph("Cell 4"));
			
			pdfPTable.addCell(cell_1);
			pdfPTable.addCell(cell_2);
			pdfPTable.addCell(cell_3);
			pdfPTable.addCell(cell_4);
			
			
			
			document.add(pdfPTable);
			
			
			document.close();
			pdfWriter.close();

		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

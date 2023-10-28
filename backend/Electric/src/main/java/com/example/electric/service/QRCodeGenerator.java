package com.example.electric.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

	/**
	 * Generate QR Code Image
	 *
	 * This method generates a QR code image from the provided text and saves it to the specified file path.
	 *
	 * @param text The text or data to encode in the QR code.
	 * @param width The width of the QR code image in pixels.
	 * @param height The height of the QR code image in pixels.
	 * @param filePath The file path where the generated QR code image will be saved.
	 * @throws WriterException If an error occurs during QR code generation.
	 * @throws IOException If an error occurs while writing the QR code image to the file.
	 */
		public static void generateQRCodeImage(String text, int width, int height, String filePath)
	            throws WriterException, IOException {
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

	        Path path = FileSystems.getDefault().getPath(filePath);
	        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	       
	    }

	/**
	 * Get QR Code Image as Byte Array
	 *
	 * This method generates a QR code image from the provided text and returns it as a byte array.
	 *
	 * @param text The text or data to encode in the QR code.
	 * @param width The width of the QR code image in pixels.
	 * @param height The height of the QR code image in pixels.
	 * @return A byte array containing the generated QR code image in PNG format.
	 * @throws WriterException If an error occurs during QR code generation.
	 * @throws IOException If an error occurs while converting the QR code image to a byte array.
	 */
		public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
		    QRCodeWriter qrCodeWriter = new QRCodeWriter();
		    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		    
		    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		    byte[] pngData = pngOutputStream.toByteArray(); 
		    return pngData;
		}

}
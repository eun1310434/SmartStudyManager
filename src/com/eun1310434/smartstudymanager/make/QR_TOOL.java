package com.eun1310434.smartstudymanager.make;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QR_TOOL {

	private Map<EncodeHintType, ErrorCorrectionLevel> hintMap = null;
	public QR_TOOL(){	
		hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	}
	
	
	
	
	private int qrCodeheight = 500;
	private int qrCodewidth = 500;
	public void createQRCode(String qrCodeData, String filePath)
			throws WriterException, IOException {
		
		BitMatrix matrix = 
				new MultiFormatWriter().encode(
						new String(qrCodeData.getBytes("UTF-8"), "ISO-8859-1"),//한글 설정을 위해서 UTF-8, ISO-8859-1로 설정
						BarcodeFormat.QR_CODE, qrCodewidth, 
						qrCodeheight, 
						hintMap
						);

		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));//이미지 생성
	}

	
	
	public String readQRCode(String filePath)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = 
				new BinaryBitmap(
						new HybridBinarizer(
								new BufferedImageLuminanceSource(
										ImageIO.read(
												new FileInputStream(filePath)
												)
										)
								)
						);
		
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,(Map) hintMap);
		return qrCodeResult.getText();
	}

}

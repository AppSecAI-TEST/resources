package com.travelsky.szcares.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PictureUtils {

	public static String[] getFileName(String path) {
		File file = new File(path);
		String[] fileName = file.list();
		return fileName;
	}

	public static void distributeFile(String path, String type) {
		ArrayList<String> fileName = new ArrayList<>();
		File file = new File(path);
		File[] files = file.listFiles();
		String[] names = file.list();
		if (names != null)
			fileName.addAll(Arrays.asList(names));

		for (File a : files) {
			if (a.isDirectory()) {
				if (a.getName().contains(type)) {
					String fileDir="";
					if (type == "港澳") {
						fileDir="HongKong-Macau laissez-passer";
					}
					if (type == "身份证") {
						fileDir="IDCard";
					}
					if (type == "护照") {
						fileDir="Passport";
					}
					if (type == "台湾") {
						fileDir="Taiwan pass process";
					}
					if (type == "拍摄") {
						fileDir="Photos";
					}

					
					File[] subfiles = a.listFiles();
					for (File subfile : subfiles) {
						String no = a.getName().split("-")[0];
						String[] fileNames = subfile.getName().split("\\.");
						String subfix = fileNames[fileNames.length - 1];
						File afile = new File(subfile.getAbsolutePath());
						if(afile.getName().contains("Rfid")){
							no=no+"_Rfid";
						}
						if(afile.getName().contains("Face")){
							no=no+"_Face";
						}
						try {
							afile.renameTo(new File("D:\\Img\\" + fileDir + "\\"
									+ no + "." + subfix));

						} catch (Throwable e) {
							// TODO: handle exception
						}

						/*
						 * if (afile.renameTo(new File("D:\\Img\\" + type+"\\" +
						 * no+"."+subfix))) {
						 * System.out.println("moved successful!"); } else {
						 * System.out.println("failed to move!"); }
						 */
					}

				}
			}
		}

	}

	public static void getAllFileName(String path, ArrayList<String> fileName) {
		File file = new File(path);
		File[] files = file.listFiles();
		String[] names = file.list();
		if (names != null)
			fileName.addAll(Arrays.asList(names));
		for (File a : files) {
			if (a.isDirectory()) {
				getAllFileName(a.getAbsolutePath(), fileName);
			}
		}
	}

	public static Map<String, String> GetPicNamePair(int fileCount,
			String path, String pathCompare, int start) {
		String[] pics = getFileName(path);
		String[] picsCompare = getFileName(pathCompare);
		Map<String, String> filesPairs = new HashMap<>();
		int picSize = pics.length;
		int picCompareSize = picsCompare.length;
		if ((picSize - start) < fileCount
				|| (picCompareSize - start) < fileCount) {
			if (picCompareSize < picSize) {
				fileCount = picCompareSize - start;
			} else {
				fileCount = picSize - start;
			}
		}
		for (int i = 0; i < fileCount; i++) {
			filesPairs.put(pics[i], picsCompare[i]);
		}
		return filesPairs;
	}

	public static List<String> GetFilesNameSingle(int fileCount, String path,
			int start) {
		String[] pics = getFileName(path);
		List<String> files = new ArrayList<String>();
		if ((pics.length - start) < fileCount) {
			fileCount = pics.length - start;
		}
		for (int i = 0; i < fileCount; i++) {
			files.add(pics[start + i]);
		}
		return files;
	}

	public static void main(String[] args) {
		// int fileCount = 4;
		// String path =
		// "D:\\SoftwarePackage\\Downloads\\最全人脸图片库\\最全人脸库\\ORL人脸库\\ORL92112\\bmp\\s1";
		// String pathCompare =
		// "D:\\SoftwarePackage\\Downloads\\最全人脸图片库\\最全人脸库\\Yale人脸库\\yalefaces\\01";
		// int start = 2;
		// Map<String, String> pairs = GetPicNamePair(fileCount, path,
		// pathCompare, start);
		// List<String> files = GetFilesNameSingle(fileCount, path, start);
		// System.out.println(pairs);
		// System.out.println(files);
		//		File file = new File(
		//				"D:\\Images\\1-港澳\\__20170803085006310_FaceDetect.jpg");
		//		if (file.exists()) {
		//			file.renameTo(new File("D:\\Img\\港澳\\1.jpg"));
		//		}

		distributeFile("D:\\Images", "港澳");
		distributeFile("D:\\Images", "护照");
		distributeFile("D:\\Images", "台湾");
		distributeFile("D:\\Images", "身份证");
		distributeFile("D:\\Images", "拍摄");
	}

}

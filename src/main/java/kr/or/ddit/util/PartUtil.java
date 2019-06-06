package kr.or.ddit.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;

public class PartUtil {
	private static final String UPLOAD_PATH = "d:"+ File.separator +"upload"+ File.separator;
	
	/**
	* Method : getFileNameTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 파일의 이름을 가져오는 메서드 테스트
	*/
	public static String getFileName(String contentDisposition) {
		String[] splited = contentDisposition.split("; ");
		String fileName = "";
		for(String split : splited){
			if (split.startsWith("filename")) {
				String[] fileNames = split.split("=");
				fileName = fileNames[1].substring(1, fileNames[1].length() - 1);
			}
		}
		return fileName;
	}
	/**
	* Method : getExtTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 파일이름에서 확장자를 가져오는 메서드
	*/
	public static String getExt(String fileName) {
		String ext = "";
		if (fileName.contains(".")) {
			int extIndex = fileName.lastIndexOf(".");
			ext = fileName.substring(extIndex);
		}
		return ext;
	}
	/**
	* Method : setMkdir
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 해당하는 폴더가 없으면 생성해주는 메서드
	*/
	public static Map<String, Object> setMkdir(String boardName) {
		
		Date dt = new Date();
		SimpleDateFormat yyyyMMSdf = new SimpleDateFormat("yyyyMM");
		
		String yyyyMM = yyyyMMSdf.format(dt);
		
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);
		
		String sp = File.separator;
		// 게시글에 해당하는 폴더를 만들어야함
		File boardNameFolder = new File(UPLOAD_PATH + boardName);
		if (!boardNameFolder.exists()) {
			boardNameFolder.mkdir();
		}
		
		File yyyyFolder = new File(UPLOAD_PATH + boardName + sp +  yyyy);
		if (!yyyyFolder.exists()) {
			yyyyFolder.mkdir();
		}
		
		File mmFolder = new File(UPLOAD_PATH  + boardName + sp + yyyy + sp + mm);
		if (!mmFolder.exists()) {
			mmFolder.mkdir();
		}
		
		
		String uploadPath = UPLOAD_PATH + boardName + sp + yyyy + sp + mm + sp;
		File uploadFolder = new File(uploadPath);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("uploadPath", uploadPath);
		resultMap.put("uploadFolder", uploadFolder);
		return resultMap;
	}
	
	static List<UploadFileVO> uploadFileList = new ArrayList<UploadFileVO>();
	/**
	* Method : uploadFile
	* 작성자 : PC25
	* 변경이력 :
	* @param file
	* Method 설명 : 파일의 개수에 따라서 업로드 처리를 하는 메서드
	 * @throws IOException 
	*/
	public static void uploadFile(Part file, BoardVO boardVo, int notiId) throws IOException {
			String contentDisposition = file.getHeader("content-disposition");
			String fileName = PartUtil.getFileName(contentDisposition);
			String ext = PartUtil.getExt(fileName);
			String sp = File.separator;
			
			Map<String, Object> resultMap = PartUtil.setMkdir(boardVo.getName());
			File uploadFolder = (File) resultMap.get("uploadFolder");
			String uploadPath = (String) resultMap.get("uploadPath");
			
			if (uploadFolder.exists()) {
				String filePath = uploadPath + UUID.randomUUID().toString() + ext; 
				String fileId = filePath;
				String path = uploadPath;
				UploadFileVO uploadFile = new UploadFileVO(fileId, notiId, path, fileName);
				uploadFileList.add(uploadFile);
				file.write(filePath);
				file.delete();
			}
	}
	
	public static List<UploadFileVO> uploadFileList(){
		return uploadFileList;
	}
	
	public static void uploadFileListClear(){
		uploadFileList.clear();
	}
	
}

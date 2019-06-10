package kr.or.ddit.uploadFile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;

@WebServlet("/fileDownload")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IUploadFileService fileService;
	
	@Override
	public void init() throws ServletException {
		fileService = new UploadFileService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
	
		// 다운로드할 첨부파일 아이디
		String fileId = request.getParameter("fileId");
		
		// 아이디에 맞는 정보를 담은 첨부파일 객체
		UploadFileVO fileVo = fileService.getFileVo(fileId);
		
		// 첨부파일 이름
		String fileName = fileVo.getFileName();
		
		response.setContentType("application/octet-stream");
		
		// 다운로드할 첨부파일 이름 설정
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		
		// 파일 객체 생성
		File file = new File(fileId);
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte[] b = new byte[1024];
		int data = 0;
		
		while((data=(fileInputStream.read(b,0,b.length))) != -1){
			servletOutputStream.write(b,0,data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}
}

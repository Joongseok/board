package kr.or.ddit.uploadFile.model;

public class UploadFileVO {

	private String fileId;  //첨부파일아이디
	private int notiId;     //게시글아이디
	private String path;    //첨부파일경로
	private String fileName;//원본파일명
	
	public UploadFileVO() {
		// TODO Auto-generated constructor stub
	}

	public UploadFileVO(String fileId, int notiId, String path, String fileName) {
		super();
		this.fileId = fileId;
		this.notiId = notiId;
		this.path = path;
		this.fileName = fileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getNotiId() {
		return notiId;
	}

	public void setNotiId(int notiId) {
		this.notiId = notiId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "UploadFileVO [fileId=" + fileId + ", notiId=" + notiId
				+ ", path=" + path + ", fileName=" + fileName + "]";
	}
	
}

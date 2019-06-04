package kr.or.ddit.notice.model;

import java.util.Date;

public class NoticeVO {

	private int notiId;	//게시글아이디
	private String userid;  //작성자
	private String title;   //제목
	private String content; //내용
	private Date reg_dt;  //작성일시
	private int parentId;//부모 게시글아이디
	private int id;      //게시판아이디
	private String del_yn;  //삭제여부
	
	public NoticeVO() {
		// TODO Auto-generated constructor stub
	}

	public NoticeVO(int notiId, String userid, String title, String content,
			Date reg_dt, int parentId, int id, String del_yn) {
		super();
		this.notiId = notiId;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.reg_dt = reg_dt;
		this.parentId = parentId;
		this.id = id;
		this.del_yn = del_yn;
	}

	public int getNotiId() {
		return notiId;
	}

	public void setNotiId(int notiId) {
		this.notiId = notiId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	@Override
	public String toString() {
		return "NoticeVO [notiId=" + notiId + ", userid=" + userid + ", title="
				+ title + ", content=" + content + ", reg_dt=" + reg_dt
				+ ", parentId=" + parentId + ", id=" + id + ", del_yn="
				+ del_yn + "]";
	}

	
	
}

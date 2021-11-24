package com.yuyi.bean;

//Field   Type         Collation        Null    Key     Default  Extra   Privileges                       Comment  
//------  -----------  ---------------  ------  ------  -------  ------  -------------------------------  ---------
//cid     varchar(32)  utf8_general_ci  NO      PRI     (NULL)           select,insert,update,references           
//cname   varchar(20)  utf8_general_ci  YES             (NULL)           select,insert,update,references  
public class Category {
	private String cid;
	private String cname;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
}

package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
private Integer userId;
private String userName;
private String userPass;
private String userAddress;
private String userMail;
private Date userCreated;
private Date userUpdated;
private Date userDeleted;

public Users(Integer userId, String userName, String userPass, String userAddress, String userMail) {
	this.userId = userId;
	this.userName = userName;
	this.userPass = userPass;
	this.userAddress = userAddress;
	this.userMail = userMail;
}
public Users() {}
}


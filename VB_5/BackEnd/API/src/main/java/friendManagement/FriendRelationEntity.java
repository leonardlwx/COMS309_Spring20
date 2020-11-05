package friendManagement;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FriendRelationEntity {
	@Id
	private int id;

	private String usernameo;
	private String usernamet;

	public FriendRelationEntity() {

	}

	public FriendRelationEntity(int id, String u1, String u2) {
		super();
		this.id = id;
		usernameo = u1;
		usernamet = u2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsernameo() {
		return usernameo;
	}

	public void setUsernameo(String usernameo) {
		this.usernameo = usernameo;
	}

	public String getUsernamet() {
		return usernamet;
	}

	public void setUsernamet(String usernamet) {
		this.usernamet = usernamet;
	}

	@Override
	public boolean equals(Object o) {
		return ((usernameo.equals(((FriendRelationEntity) o).usernameo))
				&& (usernamet.equals(((FriendRelationEntity) o).usernamet)));
	}
}

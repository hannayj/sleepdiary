package eg.sleepdiary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;
	private UserLevel userLevel;

	public User(String name, String password, UserLevel userLevel) {
		this.name = name;
		this.password = password;
		this.userLevel = userLevel;
	}
}

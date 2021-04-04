package Security;
import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt{

	public String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean isMatch(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}
	
	public void checkPass(String plainpassword , String hashedpassword) {
		if(BCrypt.checkpw(plainpassword, hashedpassword)) {
			// 평문암호 와 해시화 암호 매칭 true 일시 ,
			System.out.println("\n" + plainpassword + " == " + 
								hashedpassword + "\n");
		}
		else {
			// 평문암호 와 해시화 암호 매칭 false 일시 ,
			System.out.println("\n" + plainpassword + " =/= " + 
					hashedpassword + "\n");
		}
	}
}

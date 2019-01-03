import java.sql.Date;

public class User implements Comparable<User>{
    
    //private int id;
    private String user;
    private String pass;
    private int score;
    private Date date;
    private String achievements;
    private int numPlay;
    
    public User(String user, String pass, int score, Date date, String achievements, int numPlay)
    {
        //this.id = id;
        this.user = user;
        this.pass = pass;
        this.score = score;
        this.date = date;
        this.achievements = achievements;
        this.numPlay = numPlay;
    }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	
	public int getNumPlay() {
		return numPlay;
	}

	public void setNumPlay(int numPlay) {
		this.numPlay = numPlay;
	}
	
	@Override
	public int compareTo(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
  
}
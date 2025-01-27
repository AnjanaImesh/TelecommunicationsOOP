package models;

public class ServiceRating {
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private String name;
    private String email;
    private String experience;
    private String likes;
    private String comment;

    // Constructor, getters, and setters
    public ServiceRating(String name, String email, String experience, String likes, String comment) {
        this.name = name;
        this.email = email;
        this.experience = experience;
        this.likes = likes;
        this.comment = comment;
    }

    // Getters and setters omitted for brevity
}

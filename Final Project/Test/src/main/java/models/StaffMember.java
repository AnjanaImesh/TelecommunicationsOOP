package models;

public class StaffMember {
    private int staffMemberId;
    private String name;
    private String address;
    private String phoneNumber;
    public int getStaffMemberId() {
		return staffMemberId;
	}

	public void setStaffMemberId(int staffMemberId) {
		this.staffMemberId = staffMemberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private int age;
    private String gender;

    // Constructor, getters, and setters
    public StaffMember(int staffMemberId, String name, String address, String phoneNumber, int age, String gender) {
        this.staffMemberId = staffMemberId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
    }


}

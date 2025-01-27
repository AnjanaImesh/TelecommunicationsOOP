package models;
public class Package {
    private int customerId;
    private int packageId;
    private String packageName;
    private String dataLimit;
    private double price;
    private String status;

    // Constructor, getters, and setters
    public Package(int customerId, int packageId, String packageName, String dataLimit, double price, String status) {
        this.customerId = customerId;
        this.packageId = packageId;
        this.packageName = packageName;
        this.dataLimit = dataLimit;
        this.price = price;
        this.status = status;
    }

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDataLimit() {
		return dataLimit;
	}

	public void setDataLimit(String dataLimit) {
		this.dataLimit = dataLimit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

package ContactService;

public class Contact {
	private final String contactId;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	public Contact(String contactId, String firstName, String lastName, String phone, String address) {
		validateContactId(contactId);
        validateName(firstName, "firstName");
        validateName(lastName, "lastName");
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;				
	}
	
	public String getContactId() {
		return contactId;
	}
	
	public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    
    // Updatable fields (package-private setters so ContactService can update safely)
    void setFirstName(String firstName) {
        validateName(firstName, "firstName");
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        validateName(lastName, "lastName");
        this.lastName = lastName;
    }

    void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    // Validation helpers
    private static void validateContactId(String contactId) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("contactId must be non-null and <= 10 characters");
        }
    }

    private static void validateName(String value, String fieldName) {
        if (value == null || value.length() > 10) {
            throw new IllegalArgumentException(fieldName + " must be non-null and <= 10 characters");
        }
    }

    private static void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("phone must be non-null and exactly 10 digits");
        }
    }

    private static void validateAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("address must be non-null and <= 30 characters");
        }
    }
	
}

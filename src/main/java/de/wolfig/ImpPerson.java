package de.wolfig;

public class ImpPerson {
    private final String name;
    private final String position;
    private final String company;
    private final String role;

    public ImpPerson(String _name, String _position, String _company, String _role) {
        this.name = _name;
        this.position = _position;
        this.company = _company;
        this.role = _role;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }

    public String getRole() {
        return role;
    }
}

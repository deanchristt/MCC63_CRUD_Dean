package models;

/**
 *
 * @author deanchristt
 */
public class Department {

    private int departmenId;
    private String departmentName;
    private int managerId;
    private int locationId;

    public Department() {
    }

    public Department(int departmenId, String departmentName, int managerId, int locationId) {
        this.departmenId = departmenId;
        this.departmentName = departmentName;
        this.managerId = managerId;
        this.locationId = locationId;
    }

    public int getDepartmenId() {
        return departmenId;
    }

    public void setDepartmenId(int departmenId) {
        this.departmenId = departmenId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Department{" + "departmenId=" + departmenId + ", departmentName=" + departmentName + ", managerId=" + managerId + ", locationId=" + locationId + '}';
    }

}

package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Employee;

/**
 *
 * @author deanchristt
 */
public class EmployeeDAO {

    private final Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEE";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7),
                        resultSet.getDouble(8), resultSet.getDouble(9), resultSet.getInt(10), resultSet.getInt(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean insert(Employee employee) {
        String query = "INSERT INTO EMPLOYEE(id, first_name, last_name, email, phone_number, hire_date, "
                + "job, salary, comission_pct, manager, department) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setDate(6, employee.getHireDate());
            preparedStatement.setString(7, employee.getJobTitle());
            preparedStatement.setDouble(8, employee.getSalary());
            preparedStatement.setDouble(9, employee.getCommissionPCT());
            preparedStatement.setInt(10, employee.getManagerId());
            preparedStatement.setInt(11, employee.getDepartmentId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM EMPLOYEE WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean update(int id, Employee employee) {
        String query = "UPDATE EMPLOYEE SET email=?, phone_number=?, salary=?, comission_pct=?, "
                + "manager=?, department=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setString(2, employee.getPhoneNumber());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setDouble(4, employee.getCommissionPCT());
            preparedStatement.setInt(5, employee.getManagerId());
            preparedStatement.setInt(6, employee.getDepartmentId());
            preparedStatement.setInt(7, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employee getById(int id) {
        String query = "SELECT * FROM EMPLOYEE where id = ?";
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                employee.setEmployeeId(rs.getInt(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setPhoneNumber(rs.getString(5));
                employee.setHireDate(rs.getDate(6));
                employee.setJobTitle(rs.getString(7));
                employee.setSalary(rs.getDouble(8));
                employee.setCommissionPCT(rs.getDouble(9));
                employee.setManagerId(rs.getInt(10));
                employee.setDepartmentId(rs.getInt(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public boolean getBye(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

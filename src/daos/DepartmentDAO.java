package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Department;

/**
 *
 * @author deanchristt
 */

public class DepartmentDAO {        
        
    private final Connection connection;

    public DepartmentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM DEPARTMENT";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                departments.add(new Department(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), 
                        resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }
    
        public boolean insert(Department department) {
                    String query = "INSERT INTO "
                + "DEPARTMENT(id, name, manager, location) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, department.getDepartmenId());
            preparedStatement.setString(2, department.getDepartmentName());
            preparedStatement.setInt(3, department.getManagerId());
            preparedStatement.setInt(4, department.getLocationId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        }

    public boolean delete(int id){
        String query = "DELETE FROM DEPARTMENT WHERE id =?";
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    
    public boolean update(int id, Department department) {
        String query = "UPDATE DEPARTMENT SET name=?, manager=?, location=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);            
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setInt(2, department.getManagerId());
            preparedStatement.setInt(3, department.getLocationId());
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Department getById(int id){
        String query = "SELECT * FROM DEPARTMENT where id = ?";
            Department department = new Department();
         try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

             while (rs.next()) {
                 department.setDepartmenId(rs.getInt(1));
                 department.setDepartmentName(rs.getString(2));
                 department.setManagerId(rs.getInt(3));
                 department.setLocationId(rs.getInt(4));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    public boolean getBye(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

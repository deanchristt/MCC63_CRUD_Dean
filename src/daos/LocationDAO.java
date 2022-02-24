package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Location;

/**
 *
 * @author deanchristt
 */
public class LocationDAO {

    private final Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM LOCATION";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                locations.add(new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public boolean insert(Location location) {
        String query = "INSERT INTO "
                + "LOCATION(id, street_address, postal_code, city, state_province, country) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, location.getLocationId());
            preparedStatement.setString(2, location.getStreetAddress());
            preparedStatement.setString(3, location.getPostalCode());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getStateProvince());
            preparedStatement.setString(6, location.getCountryId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM LOCATION WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean update(int id, Location location) {
        String query = "UPDATE LOCATION SET street_address=?, postal_code=?, city=?, "
                + "state_province=?, country=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, location.getStreetAddress());
            preparedStatement.setString(2, location.getPostalCode());
            preparedStatement.setString(3, location.getCity());
            preparedStatement.setString(4, location.getStateProvince());
            preparedStatement.setString(5, location.getCountryId());
            preparedStatement.setInt(6, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Location getById(int id) {
        String query = "SELECT * FROM LOCATION where id = ?";
        Location location = new Location();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                location.setLocationId(rs.getInt(1));
                location.setStreetAddress(rs.getString(2));
                location.setPostalCode(rs.getString(3));
                location.setCity(rs.getString(4));
                location.setStateProvince(rs.getString(5));
                location.setCountryId(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }

    public boolean getBye(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

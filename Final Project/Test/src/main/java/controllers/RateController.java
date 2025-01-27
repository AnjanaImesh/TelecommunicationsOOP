package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import configs.DBConnection;
import models.ServiceRating;

public class RateController {
    private Connection connection = DBConnection.getNewConnection();

    public boolean addServiceRating(ServiceRating rating) {
        String sql = "INSERT INTO ServiceRatings (Name, Email, Experience, Likes, Comment) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rating.getName());
            statement.setString(2, rating.getEmail());
            statement.setString(3, rating.getExperience());
            statement.setString(4, rating.getLikes());
            statement.setString(5, rating.getComment());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ServiceRating getServiceRatingById(int ratingId) {
        String sql = "SELECT * FROM ServiceRatings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ratingId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            ServiceRating r =	new ServiceRating(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Experience"),
                        resultSet.getString("Likes"),
                        resultSet.getString("Comment")
                    );
            r.setId(ratingId);
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ServiceRating> getAllServiceRatings() {
        List<ServiceRating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM ServiceRatings";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
            	ServiceRating service =  new ServiceRating(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Experience"),
                        resultSet.getString("Likes"),
                        resultSet.getString("Comment")
                    );
            	service.setId(Integer.parseInt(resultSet.getString("id")));
                ratings.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    public boolean updateServiceRating(ServiceRating rating) {
        String sql = "UPDATE ServiceRatings SET Name = ?, Email = ?, Experience = ?, Likes = ?, Comment = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rating.getName());
            statement.setString(2, rating.getEmail());
            statement.setString(3, rating.getExperience());
            statement.setString(4, rating.getLikes());
            statement.setString(5, rating.getComment());
            statement.setInt(6, rating.getId());  // Assuming there is a RatingID field
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteServiceRating(int ratingId) {
        String sql = "DELETE FROM ServiceRatings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ratingId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

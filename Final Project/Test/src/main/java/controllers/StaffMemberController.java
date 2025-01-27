package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import configs.DBConnection;
import models.StaffMember;

public class StaffMemberController {
    private Connection connection = DBConnection.getNewConnection();

    public boolean addStaffMember(StaffMember staffMember) {
        String sql = "INSERT INTO StaffMembers (Name, Address, PhoneNumber, Age, Gender) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, staffMember.getName());
            statement.setString(2, staffMember.getAddress());
            statement.setString(3, staffMember.getPhoneNumber());
            statement.setInt(4, staffMember.getAge());
            statement.setString(5, staffMember.getGender());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public StaffMember getStaffMemberById(int staffMemberId) {
        String sql = "SELECT * FROM StaffMembers WHERE StaffMemberID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, staffMemberId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new StaffMember(
                    resultSet.getInt("StaffMemberID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("PhoneNumber"),
                    resultSet.getInt("Age"),
                    resultSet.getString("Gender")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StaffMember> getAllStaffMembers() {
        List<StaffMember> staffMembers = new ArrayList<>();
        String sql = "SELECT * FROM StaffMembers";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                staffMembers.add(new StaffMember(
                    resultSet.getInt("StaffMemberID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("PhoneNumber"),
                    resultSet.getInt("Age"),
                    resultSet.getString("Gender")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffMembers;
    }

    public boolean updateStaffMember(StaffMember staffMember) {
        String sql = "UPDATE StaffMembers SET Name = ?, Address = ?, PhoneNumber = ?, Age = ?, Gender = ? WHERE StaffMemberID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, staffMember.getName());
            statement.setString(2, staffMember.getAddress());
            statement.setString(3, staffMember.getPhoneNumber());
            statement.setInt(4, staffMember.getAge());
            statement.setString(5, staffMember.getGender());
            statement.setInt(6, staffMember.getStaffMemberId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStaffMember(int staffMemberId) {
        String sql = "DELETE FROM StaffMembers WHERE StaffMemberID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, staffMemberId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

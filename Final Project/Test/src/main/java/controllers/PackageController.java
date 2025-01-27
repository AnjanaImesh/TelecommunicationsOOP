package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import configs.DBConnection;
import models.Package;

public class PackageController {
    private Connection connection = DBConnection.getNewConnection();

    public boolean addPackage(Package pkg) {
        String sql = "INSERT INTO Packages (CustomerID, PackageName, DataLimit, Price, Status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pkg.getCustomerId());
            statement.setString(2, pkg.getPackageName());
            statement.setString(3, pkg.getDataLimit());
            statement.setDouble(4, pkg.getPrice());
            statement.setString(5, pkg.getStatus());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Package getPackageById(int packageId) {
        String sql = "SELECT * FROM Packages WHERE PackageID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, packageId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Package(
                    resultSet.getInt("CustomerID"),
                    resultSet.getInt("PackageID"),
                    resultSet.getString("PackageName"),
                    resultSet.getString("DataLimit"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        String sql = "SELECT * FROM Packages";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                packages.add(new Package(
                    resultSet.getInt("CustomerID"),
                    resultSet.getInt("PackageID"),
                    resultSet.getString("PackageName"),
                    resultSet.getString("DataLimit"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }

    public boolean updatePackage(Package pkg) {
        String sql = "UPDATE Packages SET CustomerID = ?, PackageName = ?, DataLimit = ?, Price = ?, Status = ? WHERE PackageID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pkg.getCustomerId());
            statement.setString(2, pkg.getPackageName());
            statement.setString(3, pkg.getDataLimit());
            statement.setDouble(4, pkg.getPrice());
            statement.setString(5, pkg.getStatus());
            statement.setInt(6, pkg.getPackageId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePackage(int packageId) {
        String sql = "DELETE FROM Packages WHERE PackageID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, packageId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

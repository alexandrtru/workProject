package com.workSolutionProject.Helpers;

import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientIdException;
import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientNameException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidDeviceIdException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidDeviceNameException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidOwnerValueException;
import com.workSolutionProject.Exceptions.ModelExceptions.ModelException;
import com.workSolutionProject.Model.Classes.Client;
import com.workSolutionProject.Model.Classes.Device;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Created by user on 03.05.2016.
 */

public class DatabaseWorker {
    private Connection connection;
    private Statement statement;
    private final String databasePath = "jdbc:sqlite:.\\database\\databaseTest.db";
    private Logger logger = Logger.getLogger("DataBaseWorkerLogger");
    private  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DatabaseWorker() throws  ClassNotFoundException{
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException ex)
        {
            logger.warning("Error in init connection. Message: \n" + ex.getMessage());
        }
    }

    public DatabaseRowsList<Client> getAllClientsFromDatabase() throws  ModelException{
        DatabaseRowsList<Client> result = new DatabaseRowsList<Client>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients WHERE DELETED = 'false'");
            while (resultSet.next()) {
                result.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            resultSet.close();
        }
        catch (SQLException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
        return result;
    }

    public DatabaseRowsList<Device> getAllDevicesFromDatabase(DatabaseRowsList clients) throws ModelException{
        DatabaseRowsList<Device> result = new DatabaseRowsList<Device>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Devices WHERE DELETED = 'false'");

            while (resultSet.next()) {
                java.util.Date dateOut = null;
                if (resultSet.getString("date_out") != null && !resultSet.getString("date_out").trim().isEmpty() ) dateOut = dateFormat.parse(resultSet.getString("date_out"));
                result.add(new Device(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        (Client)clients.containsByDatabaseId(resultSet.getInt(3)),
                                        dateFormat.parse(resultSet.getString("date_in")),
                                        dateOut,
                                        resultSet.getBoolean(6),
                                        resultSet.getString(7)));
            }
            resultSet.close();
        }
            catch (SQLException | InvalidDeviceIdException | InvalidOwnerValueException | InvalidDeviceNameException | ParseException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
        return result;
    }

    public Client addClient(String name, String contactInfo) throws ModelException{
        Client generatedClient = null;
        try {
            String query = String.format("INSERT INTO Clients (name, contactInfo) VALUES ('%1s', '%2s')", name, contactInfo);
            connection = getConnection();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            if (statement.executeUpdate(query)>= 1) {
                ResultSet returnedRow = statement.executeQuery("SELECT id FROM clients WHERE rowID = LAST_INSERT_rowID()");
                if (returnedRow.next())
                    generatedClient = new Client(returnedRow.getInt(1), name, contactInfo);
                returnedRow.close();
            }
        }
        catch (SQLException | InvalidClientNameException | InvalidClientIdException ex )
        {
            throw new ModelException(ex.getMessage());
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
        return generatedClient;
    }

    public Device addDevice(String device, Client owner, String defect) throws  ModelException {
        Device newDevice = null;
        try
        {
            connection= getConnection();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            String query = String.format("INSERT INTO Devices (device, owner, date_in, date_out, defect, complete) VALUES ('%1s', '%2d', '%3s', null, '%4s', 'false')",
                                                    device, owner.getId(), dateFormat.format(new java.util.Date()), defect);
            if (statement.executeUpdate(query)>= 1) {
                ResultSet returnedRow = statement.executeQuery("SELECT id FROM Devices WHERE rowID = LAST_INSERT_rowID()");
                if (returnedRow.next())
                    newDevice = new Device(returnedRow.getInt(1), device, owner, defect);
                returnedRow.close();
            }

            statement.close();
            connection.close();
        }
        catch (SQLException | InvalidDeviceIdException | InvalidDeviceNameException | InvalidOwnerValueException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
        return newDevice;
    }

    public boolean updateClient(Client client){
        try {
            connection = getConnection();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            String query = String.format("UPDATE Clients SET name = '%1s', contactInfo = '%2s' WHERE id = '%3d'", client.getName(), client.getContactInfo(), client.getId());
            int result = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return result>=1;
        }
        catch (SQLException ex)
        {
            logger.warning("Error in updating client. Message: \n" + ex.getMessage());
            return false;
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
    }

    public boolean updateDevice(Device device) {
        try {
            connection = getConnection();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            String dateOut = null;
            if (device.getDateOut() != null) dateOut = dateFormat.format(device.getDateOut());
            String query = String.format("UPDATE Devices SET device = '%1s', owner = '%2d', date_in = '%3s', date_out = '%4s', defect = '%5s', complete = '%6s' WHERE id = '%6d'",
                    device.getDeviceName(), device.getOwner().getId(), dateFormat.format(device.getDateIn()), dateOut, device.getDefect(), device.getComplete(), device.getId());
            int result = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return result>=1;
        }
        catch (SQLException ex)
        {
            logger.warning("Error in updating device. Message: \n" + ex.getMessage());
            return false;
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
    }

    public boolean deleteClient(Client client){
        try {
            connection = getConnection();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            String query = String.format("UPDATE Clients SET DELETED = 'true' WHERE id = '%1d'", client.getId());
            int result = statement.executeUpdate(query);
            if (result>=1) {
                query = String.format("UPDATE Devices SET DELETED = 'true' WHERE owner = '%1d'", client.getId());
                result = statement.executeUpdate(query);
            }
            statement.close();
            connection.close();
            return result>=1;
        }
        catch (SQLException ex)
        {
            logger.warning("Error in updating device. Message: \n" + ex.getMessage());
            return false;
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
    }

    public boolean deleteDevice(Device device){
        try {
            connection = getConnection();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            String query = String.format("UPDATE Devices SET DELETED = 'true' WHERE id = '%1d'", device.getId());
            int result = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return result>=1;
        }
        catch (SQLException ex)
        {
            logger.warning("Error in updating device. Message: \n" + ex.getMessage());
            return false;
        }
        finally {
            try {
                if (!statement.isClosed()) statement.close();
                if (!connection.isClosed()) connection.close();
            } catch (SQLException ex)
            {
                logger.warning(ex.getMessage());
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databasePath);
    }

}

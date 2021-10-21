package com.example.week2.Utilities;

import com.example.week2.models.Camera;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student";
    private static String pw = "student";
    private static String connectUrl = "jdbc:mysql://localhost:3306/javaProjects";

    public static XYChart.Series<String, Integer> getSalesDataByCamera()
    {
        XYChart.Series<String, Integer> salesData = new XYChart.Series<>();

        String sql = "SELECT CONCAT(make, '-', model) AS 'camera', COUNT(salesId) AS 'Units Sold'\n" +
                "FROM cameras INNER JOIN cameraSales ON cameras.cameraId = cameraSales.cameraId\n" +
                "GROUP BY cameras.cameraId;";

        //use the try with resources ensure that anything opened in the (...) will be closed
        try(
                Connection conn = DriverManager.getConnection(connectUrl, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while(resultSet.next())
            {
                salesData.getData().add(new XYChart.Data<>(resultSet.getString("camera"), resultSet.getInt("Units Sold")));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return salesData;
    }

    public static ArrayList<Camera> getCameraDetails()
    {
        ArrayList<Camera> cameras = new ArrayList<>();

        String sql = "SELECT cameras.cameraId, make, model, megapixels, price, digital, mirrorless, COUNT(salesId) AS 'Units Sold'\n" +
                "FROM cameras LEFT JOIN cameraSales ON cameras.cameraId = cameraSales.cameraId\n" +
                "GROUP BY cameras.cameraId;";

        //use the try with resources ensure that anything opened in the (...) will be closed
        try(
                Connection conn = DriverManager.getConnection(connectUrl, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while(resultSet.next())
            {
                int cameraId = resultSet.getInt("cameraId");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                double mp = resultSet.getDouble("megapixels");
                double price = resultSet.getDouble("price");
                boolean digital = resultSet.getBoolean("digital");
                boolean mirrorless = resultSet.getBoolean("mirrorless");
                int unitsSold = resultSet.getInt("Units Sold");

                Camera camera = new Camera(make,model,mp,price,digital,mirrorless);
                camera.setCameraId(cameraId);
                camera.setUnitsSold(unitsSold);

                cameras.add(camera);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return cameras;
    }

    /* This method will write a Camera object into the DB and return the cameraID
    to the calling function */
    public static int insertCameraDB(Camera camera)
    {
        int cameraID = -1;
        ResultSet rs = null;

        String sql = "INSERT INTO cameras(make, model, megaPixels, price, digital, mirrorless)\n" +
                     "VALUES(?, ?, ?, ?, ?, ?);";

        try(
                Connection conn = DriverManager.getConnection(connectUrl, user, pw);
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"cameraId"})
        )
        {
            ps.setString(1, camera.getMake());
            ps.setString(2, camera.getModel());
            ps.setDouble(3, camera.getMegaPixels());
            ps.setDouble(4, camera.getPrice());
            ps.setBoolean(5, camera.isDigital());
            ps.setBoolean(6, camera.isMirrorless());

            // Execute the update
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            while(rs.next())
            {
                cameraID = rs.getInt(1);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return cameraID;
    }

}

package com.example.week2.Utilities;

import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
}

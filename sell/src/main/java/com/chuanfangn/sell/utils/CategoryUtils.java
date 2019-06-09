package com.chuanfangn.sell.utils;

import java.sql.*;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-06 19:04
 * @version:
 **/
public class CategoryUtils {
    public static String get(Integer categoryType) {
        //连接数据库
        String category_name = null;
        Connection root = null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            root = DriverManager.getConnection("jdbc:mysql:///sell?useSSL=false&amp&serverTimezone=UTC&characterEncoding=utf-8", "root", "123456");
            String sql = "SELECT CATEGORY_NAME FROM PRODUCT_CATEGORY WHERE CATEGORY_TYPE = ?";
            preparedStatement = root.prepareStatement(sql);
            preparedStatement.setInt(1,categoryType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                category_name = resultSet.getString("category_name");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                root.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return category_name;
    }
}

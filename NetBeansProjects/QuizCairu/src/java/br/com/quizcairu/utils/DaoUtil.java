package br.com.quizcairu.utils;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoUtil {
    private Connection con = null;

    private Connection getConnection() throws SQLException, ClassNotFoundException {

        if (con == null) {
            String url = "jdbc:mysql://127.0.0.1:3307/quizcairu?zeroDateTimeBehavior=convertToNull";
            String usuario = "root";
            String senha = "aluno";
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, usuario, senha);
        }

        return con;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {

        return this.getConnection().prepareStatement(sql);

    }

    public Statement getStatment() throws SQLException, ClassNotFoundException {

        return (Statement) this.getConnection().createStatement();
    }

    public void fechaTudo() throws SQLException {
        if (con != null) {
            this.con.close();
            this.con = null;

        }
    }
}

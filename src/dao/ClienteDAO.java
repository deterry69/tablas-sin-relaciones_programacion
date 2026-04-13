package dao;

import pojo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private String url = "jdbc:sqlite:identifier.sqlite";

    public void insertarCliente(Cliente c) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "insert into clientes (nombre, email, telefono, edad, dinero_gastado, productos_comprados) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getEmail());
            pstmt.setString(3, c.getTelefono());
            pstmt.setInt(4, c.getEdad());
            pstmt.setDouble(5, c.getDineroGastado());
            pstmt.setInt(6, c.getProductosComprados());
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    public void actualizarCliente(Cliente c, int id) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "update clientes set nombre=?, email=?, telefono=?, edad=?, dinero_gastado=?, productos_comprados=? where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getEmail());
            pstmt.setString(3, c.getTelefono());
            pstmt.setInt(4, c.getEdad());
            pstmt.setDouble(5, c.getDineroGastado());
            pstmt.setInt(6, c.getProductosComprados());
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    public void eliminarCliente(int id) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "delete from clientes where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    public Cliente obtenerPorId(int id) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "select * from clientes where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return null;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select * from clientes");
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public List<Cliente> obtenerMayores30() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select * from clientes where edad > 30");
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public List<Cliente> obtenerGastadoMas500() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select * from clientes where dinero_gastado > 500");
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public List<Cliente> obtenerOrdenadosPorGasto() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select * from clientes order by dinero_gastado desc");
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public List<Cliente> obtenerTop3Productos() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select * from clientes order by productos_comprados desc limit 3");
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public double obtenerSumaTotal() {
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select sum(dinero_gastado) from clientes");
            return rs.next() ? rs.getDouble(1) : 0;
        } catch (SQLException e) { return 0; }
    }

    public double obtenerMediaProductos() {
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select avg(productos_comprados) from clientes");
            return rs.next() ? rs.getDouble(1) : 0;
        } catch (SQLException e) { return 0; }
    }

    public double obtenerMediaFiltro() {
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select avg(dinero_gastado) from clientes where edad > 25 and productos_comprados > 3");
            return rs.next() ? rs.getDouble(1) : 0;
        } catch (SQLException e) { return 0; }
    }

    public int contarGastadoMas100() {
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select count(*) from clientes where dinero_gastado > 100");
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) { return 0; }
    }

    public int contarEdadEntre30y50() {
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select count(*) from clientes where edad between 30 and 50");
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) { return 0; }
    }

    public List<Cliente> obtenerGasto200OrdenadoProductos() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select * from clientes where dinero_gastado > 200 order by productos_comprados asc");
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public Cliente obtenerMasJovenGasto400() {
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet rs = conn.createStatement().executeQuery("select * from clientes where dinero_gastado > 400 order by edad asc limit 1");
            return rs.next() ? mapear(rs) : null;
        } catch (SQLException e) { return null; }
    }

    private Cliente mapear(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        c.setEmail(rs.getString("email"));
        c.setTelefono(rs.getString("telefono"));
        c.setEdad(rs.getInt("edad"));
        c.setDineroGastado(rs.getDouble("dinero_gastado"));
        c.setProductosComprados(rs.getInt("productos_comprados"));
        return c;
    }
}
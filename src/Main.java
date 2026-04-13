import dao.ClienteDAO;
import pojo.Cliente;

public class Main {
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();

        System.out.println("========== a, b, c. PRUEBAS CRUD ==========");
        Cliente obj = new Cliente("Examen Test", "test@mail.com", "999", 40, 600.0, 10);

        dao.insertarCliente(obj);
        obj.setNombre("Examen MODIFICADO");
        dao.actualizarCliente(obj, 11);
        dao.eliminarCliente(11);

        System.out.println("-> Operaciones CRUD realizadas con éxito.\n");


        System.out.println("d-i CONSULTAS");

        System.out.println("e. Obtener todos los clientes:");
        for (Cliente c : dao.obtenerClientes()) System.out.println(c);

        System.out.println("\nf. Obtener los clientes mayores de 30 años:");
        for (Cliente c : dao.obtenerMayores30()) System.out.println(c);

        System.out.println("\ng. Obtener los clientes que han gastado más de 500€:");
        for (Cliente c : dao.obtenerGastadoMas500()) System.out.println(c);

        System.out.println("\nh. Obtener los clientes ordenados por gasto (Mayor a Menor):");
        for (Cliente c : dao.obtenerOrdenadosPorGasto()) System.out.println(c);

        System.out.println("\ni. Obtener los 3 clientes que más productos han comprado:");
        for (Cliente c : dao.obtenerTop3Productos()) System.out.println(c);

        System.out.println("\nd. Obtener un cliente según su ID (ID 5):");
        System.out.println(dao.obtenerPorId(5));

        System.out.println("\n j-p. ESTADÍSTICAS Y FILTROS");

        System.out.println("j. Suma total de dinero gastado: " + dao.obtenerSumaTotal() + "€");

        System.out.println("k. Media de productos comprados: " + dao.obtenerMediaProductos());

        System.out.println("l. Media de gasto (>25 años y >3 productos): " + dao.obtenerMediaFiltro() + "€");

        System.out.println("m. Número de clientes que han gastado más de 100€: " + dao.contarGastadoMas100());

        System.out.println("n. Número de clientes con edad entre 30 y 50: " + dao.contarEdadEntre30y50());

        System.out.println("\no. Clientes con gasto > 200€ ordenados por productos (ASC):");
        for (Cliente c : dao.obtenerGasto200OrdenadoProductos()) System.out.println(c);

        System.out.println("\np. Cliente más joven que haya gastado más de 400€:");
        System.out.println(dao.obtenerMasJovenGasto400());

        System.out.println("ACTIVIDAD FINALIZADA CON ÉXITO");
    }
}
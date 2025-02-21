import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// johansitoweb
public class App {
    private JFrame frame;
    private JTextField nombreProductoField;
    private JTextField precioProductoField;
    private DefaultListModel<String> listaProductosModel;
    private ArrayList<Double> preciosProductos;
    private JLabel totalVentasLabel;

    public App() {
        frame = new JFrame("Sistema de Ventas de Cerámica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        preciosProductos = new ArrayList<>();

        // Panel para agregar productos
        JPanel panelAgregar = new JPanel();
        panelAgregar.setLayout(new GridLayout(3, 2));

        panelAgregar.add(new JLabel("Nombre del Producto:"));
        nombreProductoField = new JTextField();
        panelAgregar.add(nombreProductoField);

        panelAgregar.add(new JLabel("Precio del Producto:"));
        precioProductoField = new JTextField();
        panelAgregar.add(precioProductoField);

        JButton botonAgregar = new JButton("Agregar Producto");
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        panelAgregar.add(botonAgregar);

        frame.add(panelAgregar, BorderLayout.NORTH);

        // Lista para mostrar productos
        listaProductosModel = new DefaultListModel<>();
        JList<String> listaProductos = new JList<>(listaProductosModel);
        frame.add(new JScrollPane(listaProductos), BorderLayout.CENTER);

        // Panel para eliminar productos y mostrar total de ventas
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(2, 1));

        JButton botonEliminar = new JButton("Eliminar Producto");
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(listaProductos.getSelectedIndex());
            }
        });
        panelOpciones.add(botonEliminar);

        totalVentasLabel = new JLabel("Total de Ventas: $0.00");
        panelOpciones.add(totalVentasLabel);

        frame.add(panelOpciones, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void agregarProducto() {
        String nombre = nombreProductoField.getText();
        String precio = precioProductoField.getText();
        if (!nombre.isEmpty() && !precio.isEmpty()) {
            try {
                double precioDouble = Double.parseDouble(precio);
                listaProductosModel.addElement(nombre + " - $" + precio);
                preciosProductos.add(precioDouble);
                actualizarTotalVentas();
                nombreProductoField.setText("");
                precioProductoField.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingrese un precio válido.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
        }
    }

    private void eliminarProducto(int index) {
        if (index >= 0) {
            preciosProductos.remove(index);
            listaProductosModel.remove(index);
            actualizarTotalVentas();
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, seleccione un producto para eliminar.");
        }
    }

    private void actualizarTotalVentas() {
        double total = 0;
        for (double precio : preciosProductos) {
            total += precio;
        }
        totalVentasLabel.setText("Total de Ventas: $" + String.format("%.2f", total));
    }

    public static void main(String[] args) {
        new App();
    }
}

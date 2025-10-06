package gm.zona_fit.Gui;

import gm.zona_fit.Modelo.Cliente;
import gm.zona_fit.Servicio.ClienteServicio;
import gm.zona_fit.Servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class ZonaFitForm extends JFrame{
    private JPanel panelPrincipal;
    private JTable tblCliente;
    private JTextField lblNombre;
    private JTextField lblApellido;
    private JTextField lblMembresia;
    private JButton btnAgregar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloCliente;
    private Integer idCliente;

    @Autowired
    public ZonaFitForm(ClienteServicio cli){
        this.clienteServicio = cli;
        inicialForma();
        btnAgregar.addActionListener(e -> guardarCliente());
        tblCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSel();
            }
        });
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnEliminar.addActionListener(e -> eliminarRegistro());
    }

    private void inicialForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //this.tablaModeloCliente = new DefaultTableModel(0,4);
        //Evitamos que cambie los datos directo desde la tabla
        this.tablaModeloCliente = new DefaultTableModel(0,4){
            @Override
            public boolean isCellEditable(int row, int colum){
                return false;
            }
        };
        String[] cabecera = {"Id", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloCliente.setColumnIdentifiers(cabecera);
        this.tblCliente = new JTable(tablaModeloCliente);

        //Restringimos la seleccion de mas de una fila
        this.tblCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Cargar el listado de cliente
        listarClientes();
    }

    private void guardarCliente() {
        if (lblNombre.getText().equals("")){
            mostrarMensaje("Debe ingresar un nombre!");
            lblNombre.requestFocusInWindow();
            return;
        }
        if (lblApellido.getText().equals("")){
            mostrarMensaje("Debe ingresar un apellido!");
            lblNombre.requestFocusInWindow();
            return;
        }
        if (lblMembresia.getText().equals("")){
            mostrarMensaje("Debe ingresar una membresia!");
            lblNombre.requestFocusInWindow();
            return;
        }

        //Se recuperan los datos
        var nombre = lblNombre.getText();
        var apellido = lblApellido.getText();
        int membresia = Integer.parseInt(lblMembresia.getText());

        var cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setMembresia(membresia);
        if (this.idCliente > 0){
            cliente.setClienteID(this.idCliente);
            try {
                this.clienteServicio.guardarCliente(cliente);
                mostrarMensaje("Cliente modificado correctamente");
            }catch (Exception e){
                mostrarMensaje("Error al modificar cliente: "+e);
            }
        }else {
            this.clienteServicio.guardarCliente(cliente);
            mostrarMensaje("Cliente guardado");
        }
        limpiarCampos();
        listarClientes();
    }

    private void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    private void cargarClienteSel() {
        var filaSel = tblCliente.getSelectedRow();
        if (filaSel != -1){//-1 Significa que no se selecciono ningun registro
            var id = tblCliente.getModel().getValueAt(filaSel, 0).toString();
            this.idCliente = Integer.parseInt(id);
            var nombre = tblCliente.getModel().getValueAt(filaSel, 1).toString();
            var apellido = tblCliente.getModel().getValueAt(filaSel, 2).toString();
            var membresia = tblCliente.getModel().getValueAt(filaSel, 3).toString();

            //Se cargan los datos
            lblNombre.setText(nombre);
            lblApellido.setText(apellido);
            lblMembresia.setText(membresia);
        }

    }

    private void listarClientes() {
        this.tablaModeloCliente.setRowCount(0);
        var clientes = this.clienteServicio.listarClientes();
        clientes.forEach(cliente -> {
            Object[] reglaCliente = {
                    cliente.getClienteID(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia(),
            };
            this.tablaModeloCliente.addRow(reglaCliente);
        });
    }


    private void eliminarRegistro() {
        try {
            var cliente = new Cliente();
            cliente.setClienteID(this.idCliente);
            this.clienteServicio.eliminarCliente(cliente);
            mostrarMensaje("Registro eliminado correctamente.");
        } catch (Exception e) {
            mostrarMensaje("Error al intentar eliminar: "+e);
        }
        limpiarCampos();
        listarClientes();
    }

    private void limpiarCampos(){
        this.idCliente = 0;
        lblNombre.setText("");
        lblApellido.setText("");
        lblMembresia.setText("");
        this.tblCliente.getSelectionModel().clearSelection();
    }
}

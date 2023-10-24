package org.example.ui;

import org.example.domain.*;
import org.example.domain.tarea.Tarea;
import org.example.domain.tarea.TareaAdapter;
import org.example.domain.tarea.TareaDAOOImp;
import org.example.domain.usuario.Usuario;
import org.example.domain.usuario.UsuarioDAOImp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Ventana extends JFrame{
    private final TareaDAOOImp daoTarea = new TareaDAOOImp(DBConnetion.getConnection());
    private JPanel panel1;
    private JTable table1;
    private JLabel info;
    private JButton button1;
    private JTextField txtDescripcion;
    private JTextField txtCategoria;
    private JTextField txtPrioridad;
    private JTextField txtTarea;
    private JComboBox comboPrioridad;
    private JComboBox comboCategoria;
    DefaultTableModel data;
    ArrayList<Tarea> tareas = new ArrayList<>(0);
    public Ventana(){
        this.setContentPane(panel1);
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Acceso con JDBC");
        table1.setRowHeight(48);
        data = (DefaultTableModel) table1.getModel();
        data.addColumn("id");
        data.addColumn("tarea");
        data.addColumn("prioridad");
        data.addColumn("usuario");
        data.addColumn("categoria");
        data.addColumn("descripcion");

        //var datos = DataBase.getAll();
        //datos.forEach((e)->data.addRow(e));
        //doLayout();



        //var tareas = DataBase.getAllTarea();
        tareas = daoTarea.loadAll();
        fillTable(tareas);

        table1.getSelectionModel().addListSelectionListener(ev -> showDetails(ev));

        guardarTarea();
    }

    private void guardarTarea() {
        button1.addActionListener(e -> {
            Tarea t = new Tarea();
            Usuario u = (new UsuarioDAOImp(DBConnetion.getConnection())).load(1L);
            t.setUsuario(u);
            t.setUsuario_id(u.getId());
            t.setCategoria((String) comboCategoria.getSelectedItem());
            t.setDescripcion(txtDescripcion.getText());
            t.setTitulo(txtTarea.getText());
            t.setPrioridad((String) comboPrioridad.getSelectedItem());
            t=(new TareaDAOOImp(DBConnetion.getConnection())).save(t);
            tareas = daoTarea.loadAll();
            fillTable(tareas);
            txtDescripcion.setText("");
            txtTarea.setText("");

        });
    }

    private void showDetails(ListSelectionEvent ev) {
        if (!ev.getValueIsAdjusting()){
            Tarea selected = tareas.get(table1.getSelectedRow());
            info.setText(String.valueOf(selected));
        }
    }

    private void fillTable(ArrayList<Tarea> tareas) {
        data.setRowCount(0);
        tareas.forEach((t)->{
            data.addRow(new TareaAdapter(t).toArrayString());
        });
    }

    public void load(){
        setVisible(true);
    }
}

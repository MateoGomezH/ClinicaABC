package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.vo.PersonaVO;

public class TablaPersonas extends JFrame {
    public TablaPersonas(List<PersonaVO> personas) {
        setTitle("Lista de Personas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columnas = {"Documento", "Nombre", "Apellido", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (PersonaVO persona : personas) {
            Object[] fila = {persona.getDocumento(), persona.getNombre(), persona.getApellido(), persona.getTelefono()};
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }
}

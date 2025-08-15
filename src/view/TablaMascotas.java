package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.vo.MascotaVO;

public class TablaMascotas extends JFrame {
    public TablaMascotas(List<MascotaVO> mascotas) {
        setTitle("Lista de Mascotas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        String[] columnas = {"Nombre", "Raza", "Especie", "Sexo", "Documento Dueño"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (MascotaVO mascota : mascotas) {
            Object[] fila = {
                mascota.getNombre(),
                mascota.getRaza(),
                mascota.getEspecie(),
                mascota.getSexo(),
                mascota.getPropietario().getDocumento()
            };
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }
}

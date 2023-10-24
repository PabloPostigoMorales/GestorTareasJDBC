package org.example.domain.tarea;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TareaDAO {
    public Tarea  load(Long id) throws SQLException;
    public ArrayList<Tarea> loadAll();
    public ArrayList<Tarea> loadAllByResponsable(Long responsable);
    public Tarea save(Tarea t) throws SQLException;
    public Tarea update(Tarea t);

    public void remove(Tarea t);


}

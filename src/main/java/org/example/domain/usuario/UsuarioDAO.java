package org.example.domain.usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {
    public Usuario load(Long id) throws SQLException;
    public ArrayList<Usuario> loadAll();
    public Usuario save(Usuario t);
    public Usuario update(Usuario t);

    public void remove(Usuario t);

}

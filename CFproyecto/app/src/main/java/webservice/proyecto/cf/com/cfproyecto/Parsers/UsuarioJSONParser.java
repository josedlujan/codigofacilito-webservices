package webservice.proyecto.cf.com.cfproyecto.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import webservice.proyecto.cf.com.cfproyecto.POJO.Usuario;

/**
 * Created by jose on 28/06/16.
 */
public class UsuarioJSONParser {

    public static List<Usuario> parse(String content){
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<Usuario> usuarioList = new ArrayList<>();

            for (int i = 0; i <jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();

                usuario.setUsuarioId(jsonObject.getInt("usuarioid"));
                usuario.setNombre(jsonObject.getString("nombre"));
                usuario.setTwitter(jsonObject.getString("twitter"));

                usuarioList.add(usuario);

            }

            return usuarioList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


}

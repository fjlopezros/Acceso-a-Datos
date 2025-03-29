package mejora;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Actividad11_Main {

    public static void main(String[] args) {

        ODB odb = ODBFactory.open("d:\\EQUIPOS.DB");

        IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("deporte", "tenis"));
        query.orderByAsc("nombre,edad");

        Objects<Object> objects = odb.getObjects(query);
        System.out.println("Hay "+ objects.size()+" Jugadores de tenis:");
        int i = 1;

        while(objects.hasNext()) {
            Jugadores jug = (Jugadores) objects.next();
            System.out.println((i++)+" - "+"Nombre: "+jug.getNombre()+", Deporte: "+ jug.getDeporte()+", Pais: "+ jug.getPais().getNombrePais());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * @author hcadavid
 */
@Component
@Qualifier("inMemoryPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts2=new Point[]{new Point(0, 1),new Point(100, 120)};
        Point[] pts3=new Point[]{new Point(160, 500),new Point(70, 70)};
        Blueprint bp=new Blueprint("marcelo", "casita ",pts);
        Blueprint bp2=new Blueprint("johan", "casota ",pts2);
        Blueprint bp3=new Blueprint("marcelo", "casa ",pts3);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);

        
    }
    //Si se daña un pixel significa que circuito generador de matrices dejo de funcionar
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> bpsrta = new HashSet<Blueprint>();
        for (Tuple<String,String> bps : blueprints.keySet()){
            if (bps.o1.equals(author)){
                bpsrta.add(blueprints.get(bps));
            }
        }
        return bpsrta;
    }
    public Set<Blueprint>  retornarPuntos(){
        Set<Blueprint> aux = new HashSet<>();
        for (Tuple<String,String> bps : blueprints.keySet()){
           aux.add(blueprints.get(bps));
        }
        return aux;
    }


}

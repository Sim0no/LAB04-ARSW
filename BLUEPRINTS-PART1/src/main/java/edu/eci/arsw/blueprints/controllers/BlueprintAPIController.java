/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    @Autowired
    @Qualifier("servicios")
    BlueprintsServices data;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorBlueprints() {
        //obtener datos que se enviarán a través del API
        return new ResponseEntity<>(data.getAllBlueprints(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorBlueprintsByAuthor(@PathVariable("author") String author) {

        try {
            //obtener datos que se enviarán a través del API
            Set<Blueprint> datos =data.getBlueprintsByAuthor(author);
            return !datos.isEmpty()? new ResponseEntity<>(datos, HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/{author}/{plane}",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorBlueprintsByAuthor(@PathVariable("author") String author,@PathVariable("plane") String plane) {

        try {
            //obtener datos que se enviarán a través del API
            Set<Blueprint> datos =data.getBlueprintsByAuthorAndName(author,plane);
            return !datos.isEmpty()? new ResponseEntity<>(datos, HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

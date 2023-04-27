package com.techedusoln.selfserve.datainjestion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.techedusoln.selfserve.datainjestion.model.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.techedusoln.selfserve.datainjestion.repository.ConnectionRepository;


@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class ConnectionController {

	@Autowired
	ConnectionRepository connectionRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}
	@GetMapping("/connections")
	public ResponseEntity<List<DbConnection>> getAllConnections(@RequestParam(required = false) String name) {
		try {
			List<DbConnection> dbConnections = new ArrayList<DbConnection>();

			if (name == null)
				connectionRepository.findAll().forEach(dbConnections::add);
			else
				//connectionRepository.findByNameContaining(name).forEach(tutorials::add);

			if (dbConnections.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(dbConnections, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/connection/{id}")
	public ResponseEntity<DbConnection> getTutorialById(@PathVariable("id") Integer id) {
		Optional<DbConnection> connectionData = connectionRepository.findById(id);

		if (connectionData.isPresent()) {
			return new ResponseEntity<>(connectionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/connection")
	public ResponseEntity<DbConnection> createTutorial(@RequestBody DbConnection newConnection) {
		try {
			DbConnection conn = new DbConnection();
			conn.setName(newConnection.getName());
			conn.setDescription(newConnection.getDescription());
			conn.setProperty(newConnection.getProperty());
			DbConnection dbconn = connectionRepository
					.save(conn);
			return new ResponseEntity<>(dbconn, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/connection/{id}")
	public ResponseEntity<DbConnection> updateTutorial(@PathVariable("id") Integer id, @RequestBody DbConnection dbConnection) {
		Optional<DbConnection> connectionData = connectionRepository.findById(id);

		if (connectionData.isPresent()) {
			DbConnection conn = connectionData.get();
			conn.setName(dbConnection.getName());
			conn.setDescription(dbConnection.getDescription());
		    conn.setProperty(dbConnection.getProperty());
			return new ResponseEntity<>(connectionRepository.save(conn), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/connection/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Integer id) {
		try {
			connectionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

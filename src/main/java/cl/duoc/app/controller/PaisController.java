package cl.duoc.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.duoc.app.entity.Pais;
import cl.duoc.app.service.PaisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/app/v1/pais")
@Api(value = "Pais", tags = { "Pais" })
public class PaisController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaisController.class);

	@Autowired
	private PaisService service;

	private static final String ERROR = "ERROR";
	private static final String REGISTRO_NO_ENCONTRADO = "Registro no encontrado";

	@ApiOperation(value = "find all", tags = { "Pais" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(value = "", consumes = {}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> findAll() {
		LOGGER.info("findAll init");
		try {
			List<Pais> paises = service.findAll();
			if (paises != null) {
				return new ResponseEntity<>(paises.toArray(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Registros no encontrados", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			LOGGER.error(ERROR, ex);
			return new ResponseEntity<>("Problemas al crear el registro", HttpStatus.FORBIDDEN);
		} finally {
			LOGGER.info("findAll finish");
		}
	}

	@ApiOperation(value = "create", tags = { "Pais" })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Object[].class),
			@ApiResponse(code = 500, message = "Failure") })
	@PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> create(@RequestBody Pais pais) {
		LOGGER.info("create init");
		try {
			service.create(pais);
			return new ResponseEntity<>("Registro creado exitósamente", HttpStatus.CREATED);
		} catch (Exception ex) {
			LOGGER.error(ERROR, ex);
			return new ResponseEntity<>("Problemas al crear el registro", HttpStatus.FORBIDDEN);
		} finally {
			LOGGER.info("create finish");
		}
	}

	@ApiOperation(value = "update", tags = { "Pais" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Updating", response = Object[].class),
			@ApiResponse(code = 500, message = "Failure") })
	@PutMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> update(@RequestBody Pais pais) {
		LOGGER.info("update init");
		try {
			if (service.findById(pais.getId()) != null) {
				service.create(pais);
				return new ResponseEntity<>(pais, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(REGISTRO_NO_ENCONTRADO, HttpStatus.FORBIDDEN);
			}
		} catch (Exception ex) {
			LOGGER.error(ERROR, ex);
			return new ResponseEntity<>("Problemas al actualizar el registro", HttpStatus.FORBIDDEN);
		} finally {
			LOGGER.info("update finish");
		}
	}

	@ApiOperation(value = "find-by-id", tags = { "Pais" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object[].class),
			@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(value = "/{id}", consumes = {}, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		LOGGER.info("findById init");
		try {
			Pais pais = service.findById(id);
			if (pais != null) {
				return new ResponseEntity<>(pais, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(REGISTRO_NO_ENCONTRADO, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			LOGGER.error(ERROR, ex);
			return new ResponseEntity<>("Problemas al buscar el registro", HttpStatus.FORBIDDEN);
		} finally {
			LOGGER.info("findById finish");
		}
	}
	
	@ApiOperation(value = "delete-by-id", tags = { "Pais" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object[].class),
			@ApiResponse(code = 500, message = "Failure") })
	@DeleteMapping(value = "/{id}", consumes = {}, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		LOGGER.info("delete init");
		try {
			Pais pais = service.findById(id);
			if (pais != null) {
				service.delete(pais);
				return new ResponseEntity<>("registro eliminado.", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(REGISTRO_NO_ENCONTRADO, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			LOGGER.error(ERROR, ex);
			return new ResponseEntity<>("Problemas al buscar el registro", HttpStatus.FORBIDDEN);
		} finally {
			LOGGER.info("delete finish");
		}
	}	
}
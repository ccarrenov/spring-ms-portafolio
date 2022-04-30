package cl.duoc.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/app/v1/pais")
@Api(value="Paises", tags={"Pais"})
public class PaisController {

    @ApiOperation(value="hola-mundo", tags={"Paises"})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class), @ApiResponse(code = 500, message = "Failure") })
    @GetMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> holaMundo(){
        return new ResponseEntity<>("Hola Mundo", HttpStatus.OK);
    }
}
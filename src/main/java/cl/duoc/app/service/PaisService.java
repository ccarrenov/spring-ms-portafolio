package cl.duoc.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.app.entity.Pais;
import cl.duoc.app.repository.PaisRepository;

@Service
public class PaisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaisService.class);
	
	@Autowired
	private PaisRepository dao;
	
	public void create(Pais pais) {
		LOGGER.info("create init");
		dao.save(pais);
		LOGGER.info("create finish");
	}
	
	public void delete(Pais pais) {
		dao.delete(pais);
	}
	
	public Pais findById(Long id) {
		Optional<Pais> result = dao.findById(id);
		return (result.isPresent())?result.get():null;
	}
	
	public List<Pais> findAll(){
		return dao.findAll();
	}
}

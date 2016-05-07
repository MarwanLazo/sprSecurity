package com.sprSecurity.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.enums.Status;

/**
 * @author Marwan
 * 
 */
@ManagedBean(name = "temp")
@ViewScoped
public class TempController implements Serializable {
	private static final long	   serialVersionUID	= 1L;
	
	private List<TempTableDTO> results;
	private TempTableDTO		   temp;
	@ManagedProperty("#{tempTableService}")
	private TempTableService	   tempTableService;
	
	@PostConstruct
	private void init () {
		results = tempTableService.findAll();
		temp = new TempTableDTO();
		// for (TempTableDTO tempTableDTO : results) {
		// System.out.println(tempTableDTO.getTempRef());
		// }
	}
	
	public List<TempTableDTO> getResults () {
		return results;
	}
	
	public void createTempTable (ActionEvent event) {
		temp.setId(temp.getId().replace(" ", ""));
		if (temp.getStatus() == null || temp.getStatus().equals(Status.ACTIVE)) {
			temp.setStatus(Status.IN_ACTIVE);
		} else {
			temp.setStatus(Status.ACTIVE);
		}
		tempTableService.createEntity(temp);
		results = tempTableService.findAll();
		temp = new TempTableDTO();
	}
	
	public void deleteTempTable (TempTableDTO temp) {
		tempTableService.deleteEntity(temp);
		results = tempTableService.findAll();
	}
	
	public void updateTempTable (TempTableDTO temp) {
		this.temp = temp;
	}
	
	// ------------- Setters & getters -------------
	public TempTableDTO getTemp () {
		return temp;
	}
	
	public void setTemp (TempTableDTO temp) {
		this.temp = temp;
	}
	
	public void setTempTableService (TempTableService tempTableService) {
		this.tempTableService = tempTableService;
	}
	
}

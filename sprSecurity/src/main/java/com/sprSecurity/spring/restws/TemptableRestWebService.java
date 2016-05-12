package com.sprSecurity.spring.restws;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;

@RestController
public class TemptableRestWebService {

	@Inject
	private TempTableService temp;

	@RequestMapping(value = "/temptable", method = RequestMethod.GET)
	public ResponseEntity<List<TempTableDTO>> getAllTempTable() {
		List<TempTableDTO> temps = temp.findAll();
		if (temps.isEmpty()) {
			return new ResponseEntity<List<TempTableDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TempTableDTO>>(temps, HttpStatus.OK);
	}

	@RequestMapping(value = "/addtemptable/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody TempTableDTO tempTableDTO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + tempTableDTO.getTempTableName());
		temp.createEntity(tempTableDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/tempTable/{tempName}").buildAndExpand(tempTableDTO.getTempTableName()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/temptable/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TempTableDTO> getTempTable(@PathVariable("id") String id) {
		System.out.println("Fetching Temp Table with id " + id);
		TempTableDTO dto = temp.findEntityById(id);
		if (dto == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<TempTableDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TempTableDTO>(dto, HttpStatus.OK);
	}

	/**
	 * 
	 * @RestController public class HelloWorldRestController {
	 * 
	 * @Autowired UserService userService; //Service which will do all data
	 *            retrieval/manipulation work
	 * 
	 * 
	 *            //-------------------Retrieve All
	 *            Users--------------------------------------------------------
	 * 
	 * @RequestMapping(value = "/user/", method = RequestMethod.GET) public
	 *                       ResponseEntity<List<User>> listAllUsers() { List
	 *                       <User> users = userService.findAllUsers();
	 *                       if(users.isEmpty()){ return new ResponseEntity<List
	 *                       <User>>(HttpStatus.NO_CONTENT);//You many decide to
	 *                       return HttpStatus.NOT_FOUND } return new
	 *                       ResponseEntity<List<User>>(users, HttpStatus.OK); }
	 * 
	 * 
	 *                       //-------------------Retrieve Single
	 *                       User-----------------------------------------------
	 *                       ---------
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
	 *                       produces = MediaType.APPLICATION_JSON_VALUE) public
	 *                       ResponseEntity <User> getUser(@PathVariable("id")
	 *                       long id) { System.out.println(
	 *                       "Fetching User with id " + id); User user =
	 *                       userService.findById(id); if (user == null) {
	 *                       System.out.println("User with id " + id +
	 *                       " not found"); return new ResponseEntity
	 *                       <User>(HttpStatus.NOT_FOUND); } return new
	 *                       ResponseEntity<User>(user, HttpStatus.OK); }
	 * 
	 * 
	 * 
	 *                       //-------------------Create a
	 *                       User-----------------------------------------------
	 *                       ---------
	 * 
	 * @RequestMapping(value = "/user/", method = RequestMethod.POST) public
	 *                       ResponseEntity<Void> createUser(@RequestBody User
	 *                       user, UriComponentsBuilder ucBuilder) {
	 *                       System.out.println( "Creating User " +
	 *                       user.getName());
	 * 
	 *                       if (userService.isUserExist(user)) {
	 *                       System.out.println("A User with name " +
	 *                       user.getName() + " already exist"); return new
	 *                       ResponseEntity <Void>(HttpStatus.CONFLICT); }
	 * 
	 *                       userService.saveUser(user);
	 * 
	 *                       HttpHeaders headers = new HttpHeaders();
	 *                       headers.setLocation(ucBuilder.path("/user/{id}").
	 *                       buildAndExpand(user.getId()).toUri()); return new
	 *                       ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 *                       }
	 * 
	 * 
	 *                       //------------------- Update a User
	 *                       ---------------------------------------------------
	 *                       -----
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT) public
	 *                       ResponseEntity<User> updateUser(@PathVariable("id")
	 *                       long id, @RequestBody User user) {
	 *                       System.out.println( "Updating User " + id);
	 * 
	 *                       User currentUser = userService.findById(id);
	 * 
	 *                       if (currentUser==null) { System.out.println(
	 *                       "User with id " + id + " not found"); return new
	 *                       ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 *                       currentUser.setName(user.getName());
	 *                       currentUser.setAge(user.getAge());
	 *                       currentUser.setSalary(user.getSalary());
	 * 
	 *                       userService.updateUser(currentUser); return new
	 *                       ResponseEntity<User>(currentUser, HttpStatus.OK); }
	 * 
	 *                       //------------------- Delete a User
	 *                       ---------------------------------------------------
	 *                       -----
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	 *                       public ResponseEntity
	 *                       <User> deleteUser(@PathVariable("id") long id) {
	 *                       System.out.println(
	 *                       "Fetching & Deleting User with id " + id);
	 * 
	 *                       User user = userService.findById(id); if (user ==
	 *                       null) { System.out.println(
	 *                       "Unable to delete. User with id " + id +
	 *                       " not found"); return new ResponseEntity
	 *                       <User>(HttpStatus.NOT_FOUND); }
	 * 
	 *                       userService.deleteUserById(id); return new
	 *                       ResponseEntity<User>(HttpStatus.NO_CONTENT); }
	 * 
	 * 
	 *                       //------------------- Delete All Users
	 *                       ---------------------------------------------------
	 *                       -----
	 * 
	 * @RequestMapping(value = "/user/", method = RequestMethod.DELETE) public
	 *                       ResponseEntity<User> deleteAllUsers() {
	 *                       System.out.println("Deleting All Users");
	 * 
	 *                       userService.deleteAllUsers(); return new
	 *                       ResponseEntity <User>(HttpStatus.NO_CONTENT); }
	 * 
	 *                       }
	 *
	 */
}
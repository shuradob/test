package net.dobrov.rest;

/**
 * Created by Shura on 04.06.2015.
 */
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import net.dobrov.dao.UserDao;
import net.dobrov.model.User;

@RestController
@RequestMapping("/service")
public class RestServiceController {

    private UserDao userDao =new UserDao();

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> list() {
        return userDao.getUsers();
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public User createList(@RequestBody User user) {
        return userDao.create(user);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable("id") int id) {
        if(id != 0)
        {
            return userDao.getUser(id);
        }
        return null;
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.POST)
    public User create(@RequestBody User user) {
        return userDao.create(user);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        if(id != 0)
        {
            userDao.remove(id);
        }
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    public User update(@PathVariable("id") long userId,  @RequestBody User user) {
        return userDao.update(user);
    }

}

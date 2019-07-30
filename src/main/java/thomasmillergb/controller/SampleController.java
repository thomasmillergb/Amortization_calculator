package thomasmillergb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import thomasmillergb.service.Service;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
public class SampleController {
    private final Service service;

    @Autowired
    public SampleController(final Service service) {
        this.service = service;
    }

    @RequestMapping(method = GET, path = "/hello/{id}")
    @ResponseStatus(OK)
    public String getName(@PathVariable("id") final String id) {
        return "Hello";
    }

    @RequestMapping(method = PUT, path = "/hello/{id}")
    @ResponseStatus(OK)
    public void setName(@PathVariable("id") final String id, @RequestBody final String name) {

    }
}

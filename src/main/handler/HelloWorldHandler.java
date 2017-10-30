package main.handler;

import main.definitation.HandleRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldHandler implements HandleRequest {

    public String helloworld(HttpServletRequest request, HttpServletResponse response){

        return "helloWorld.jsp";
    }

    public String handle(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }


}

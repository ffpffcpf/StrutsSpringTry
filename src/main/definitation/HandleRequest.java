package main.definitation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandleRequest {

    String handle(HttpServletRequest request, HttpServletResponse response);

}

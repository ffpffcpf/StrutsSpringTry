package main.handler;

import main.definitation.HandleRequest;
import main.definitation.RequestMapDefinition;
import main.parse.Dom4jRequestMapParser;
import main.parse.RequestMapDefinitionParser;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class ForwardHandler extends HttpServlet {

//    private static final Logger LOGGER = Logger.getLogger(ForwardHandler.class);

    private Map<String, RequestMapDefinition> requestMapDefinitionMap;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();

        RequestMapDefinition definition = requestMapDefinitionMap.get(requestUrl);

        try {
            Class<HandleRequest> handleRequestClass = (Class<HandleRequest>) Class.forName(definition.getClassName());

            HandleRequest handler =  handleRequestClass.newInstance();

            String path ="";

            if (null != definition.getMethod() && !"".equals(definition.getMethod())) {
                Method handleMethod = handleRequestClass.getMethod(definition.getMethod(), HttpServletRequest.class, HttpServletResponse.class);

                path = (String) handleMethod.invoke(handler, req, resp);
            }else {
                path = handler.handle(req, resp);
            }

            if(definition.isRedirect()){
                resp.sendRedirect(path);
            }else {
                req.getRequestDispatcher(path).forward(req, resp);
            }

        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String mapperName = config.getInitParameter("mapperName");

        RequestMapDefinitionParser parser = new Dom4jRequestMapParser(mapperName);

        parser.parseXml();

        requestMapDefinitionMap = parser.getRequestMap();
    }

}

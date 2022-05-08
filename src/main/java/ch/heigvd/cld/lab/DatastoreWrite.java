package ch.heigvd.cld.lab;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DatastoreWrite", value = "/datastorewrite")
public class DatastoreWrite extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, String> parameters = new HashMap<>();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterContent = request.getParameter(parameterName);
            parameters.put(parameterName, parameterContent);
        }

        // The `_kind` field is mandatory
        if (!parameters.containsKey("_kind"))
            return;

        Entity entity;
        if (parameters.containsKey("_key"))
            entity = new Entity(parameters.remove("_kind"), parameters.remove("_key"));
        else
            entity = new Entity(parameters.remove("_kind"));

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            entity.setProperty(entry.getKey(), entry.getValue());
        }

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(entity);
    }
}

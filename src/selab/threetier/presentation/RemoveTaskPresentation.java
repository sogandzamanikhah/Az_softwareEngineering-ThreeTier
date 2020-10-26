package selab.threetier.presentation;

import org.json.JSONObject;
import selab.threetier.logic.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import selab.threetier.logic.Task ;


public class RemoveTaskPresentation extends JSONPresentation {

    @Override
    public JSONObject getData(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject request = new JSONObject(new BufferedReader(new InputStreamReader(body)).lines().collect(Collectors.joining("\n")));

        int id = request.getInt("id");
        // TODO: Add codes here to delete a task with the id
        for (int i = 0; i < Task.getAll().size(); i++) {
            if(id == Task.getAll().get(i).getId()){
                Task.getAll().get(i).remove();
                break;
            }

        }
        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JSONObject(result);



    }
}

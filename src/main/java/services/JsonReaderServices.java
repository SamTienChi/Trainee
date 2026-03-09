package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import model_v2.MenuItem;
import utils.jsonUtils_v2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderServices {
    public List<MenuItem> read(String path) throws Exception{
        File file = new File(path);

        if(!file.exists()){
            throw new IllegalArgumentException("File not found");
        }

        if(file.length() == 0){
            throw new IllegalArgumentException("Json file is empty");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(path));
        List<MenuItem> items = new ArrayList<>();
        for(JsonNode node : root){
            MenuItem item = jsonUtils_v2.create(node);
            items.add(item);
        }
        return items;
    }
}

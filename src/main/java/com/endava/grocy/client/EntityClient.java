package com.endava.grocy.client;

import com.endava.grocy.model.Entity;
import com.endava.grocy.model.EntityType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EntityClient extends BaseClient {

    public Response createEntity(EntityType entityType, Entity entity) {

        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(entity)
                .pathParam("entity", entityType)
                .post("/objects/{entity}");


    }

    public Response deleteEntityById(EntityType entity, Integer productId) {

        return getBasicRestConfig()
                .pathParam("entity", entity)
                .pathParam("objectId", productId)
                .delete("/objects/{entity}/{objectId}");
    }

    public Response getEntityById(EntityType entity, Integer productId) {

        return getBasicRestConfig()
                .pathParam("entity", entity)
                .pathParam("objectId", productId)
                .get("/objects/{entity}/{objectId}");
    }

    public Response getTheListOfObjectsInEntity(EntityType entity) {

        return getBasicRestConfig()
                .pathParam("entity", entity)
                .get("/objects/{entity}");
    }

    public Response updateEntityById(EntityType entityType, Entity entity, Integer productId) {

        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(entity)
                .pathParam("entity", entityType)
                .pathParam("objectId", productId)
                .put("/objects/{entity}/{objectId}");
    }
}

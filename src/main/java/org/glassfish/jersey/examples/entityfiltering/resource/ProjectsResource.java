package org.glassfish.jersey.examples.entityfiltering.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.jersey.examples.entityfiltering.DBDataSources;
import org.glassfish.jersey.examples.entityfiltering.domain.EntityStore;
import org.glassfish.jersey.examples.entityfiltering.domain.Price;

@Path("priceList")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectsResource {

    DBDataSources dbDataSources;


    // Получает цену товара на указанную дату. Если товар не
    // продается в указанную дату, то возвращает пустой ответ с HTTP-кодом No Content.
    //
    // Если товар с переданным именем еще не был внесен в прайс-лист,
    // возвращает пустой ответ с HTTP-кодом Not Found.
    @GET
    public Response getPrice(@NotNull @QueryParam("productName") String productName, @NotNull @QueryParam("date") RESTDateParam date) {//},

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lDate = LocalDate.parse((date.getDate()).toString(), format);
        List<Price> priceList = EntityStore.getListPrices(productName);
        if (priceList.size() > 0) {
            Price price = EntityStore.getPrice(productName, lDate);

            if (price != null) {
                GenericEntity<Price> entity = new GenericEntity<Price>(price) {
                };
                return Response.ok(entity).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("NO_CONTENT").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT_FOUND").build();
        }
    }


    // Получает цены товара с промежутками действия цены.
    // Результат отсортирован в порядке возрастания даты действия
    // цены.
    //
    // Если товар с переданным именем еще не был внесен в прайс-лист,
    // возвращает пустой ответ с HTTP-кодом Not Found.
    @GET
    @Path("listPrices")
    public Response getPrices(@NotNull @QueryParam("productName") String productName) {
        List<Price> priceList = EntityStore.getListPrices(productName);
        if (priceList.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT_FOUND").build();
        } else {
            GenericEntity<List<Price>> entity =
                    new GenericEntity<List<Price>>(Lists.newArrayList(priceList)) {
                    };
            return Response.ok(entity).build();
        }
    }

}

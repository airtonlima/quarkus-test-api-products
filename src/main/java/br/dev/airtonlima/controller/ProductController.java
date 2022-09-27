package br.dev.airtonlima.controller;

import br.dev.airtonlima.dto.ProductDTO;
import br.dev.airtonlima.service.ProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {
    @Inject
    ProductService service;

    @GET
    public Response listAll() {
        List<ProductDTO> products = service.listAll();
        return Response.ok(products).build();
    }

    @POST
    @Transactional
    public Response create(ProductDTO dto) {
        try {
            service.create(dto);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("{productId}")
    @Transactional
    public Response update(@PathParam("productId") Long id, ProductDTO dto) {
        try {
            service.update(id, dto);
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("{productId}")
    @Transactional
    public Response delete(@PathParam("productId") Long id) {
        try {
            service.delete(id);
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }
}

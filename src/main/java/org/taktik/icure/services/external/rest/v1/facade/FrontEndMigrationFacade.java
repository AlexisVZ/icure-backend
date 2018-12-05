package org.taktik.icure.services.external.rest.v1.facade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.taktik.icure.dao.impl.idgenerators.UUIDGenerator;
import org.taktik.icure.entities.FrontEndMigration;
import org.taktik.icure.entities.Insurance;
import org.taktik.icure.exceptions.DeletionException;
import org.taktik.icure.logic.*;
import org.taktik.icure.services.external.rest.v1.dto.AccessLogDto;
import org.taktik.icure.services.external.rest.v1.dto.FrontEndMigrationDto;
import org.taktik.icure.services.external.rest.v1.dto.InsuranceDto;
import org.taktik.icure.services.external.rest.v1.dto.ListOfIdsDto;
import org.taktik.icure.utils.ResponseUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/frontendmigration")
@Api(tags = { "frontendmigration" })
@Consumes({"application/json"})
@Produces({"application/json"})
public class FrontEndMigrationFacade implements OpenApiFacade {

    private static final Logger logger = LoggerFactory.getLogger(FrontEndMigrationFacade.class);

    private FrontEndMigrationLogic frontEndMigrationLogic;
    private MapperFacade mapper;
    private SessionLogic sessionLogic;
    private UserLogic userLogic;
    private UUIDGenerator uuidGenerator;


    @ApiOperation(response = AccessLogDto.class, value = "Creates a front end migration")
    @POST
    public Response createInsurance(FrontEndMigrationDto frontEndMigrationDto) {
        Response response;

        if (frontEndMigrationDto == null) {
            response = ResponseUtils.badRequest("Cannot create front end migration: supplied frontEndMigrationDto is null");

        } else {
            FrontEndMigration frontEndMigration = frontEndMigrationLogic.createFrontEndMigration(mapper.map(frontEndMigrationDto, FrontEndMigration.class));
            if (frontEndMigration != null) {
                response = ResponseUtils.ok(mapper.map(frontEndMigration, FrontEndMigrationDto.class));

            } else {
                response = ResponseUtils.internalServerError("Insurance creation failed");
            }
        }

        return response;
    }

    @ApiOperation(value = "Deletes a front end migration")
    @DELETE
    @Path("/{frontEndMigrationId}")
    public Response deleteInsurance(@PathParam("frontEndMigrationId") String frontEndMigrationId) throws DeletionException {
        Response response;

        if (frontEndMigrationId == null) {
            response = ResponseUtils.badRequest("Cannot delete front end migration: supplied frontEndMigrationId is null");

        } else {
            String deletedFrontEndMigrationId = frontEndMigrationLogic.deleteFrontEndMigration(frontEndMigrationId);
            if (deletedFrontEndMigrationId != null) {
                response = ResponseUtils.ok();

            } else {
                response = ResponseUtils.internalServerError("Insurance deletion failed");
            }
        }

        return response;
    }

    @ApiOperation(response = FrontEndMigrationDto.class, value = "Gets a front end migration")
    @GET
    @Path("/{frontEndMigrationId}")
    public Response getFrontEndMigration(@PathParam("frontEndMigrationId") String frontEndMigrationId) {
        Response response;

        if (frontEndMigrationId == null) {
            response = ResponseUtils.badRequest("Cannot get front end migration: supplied frontEndMigrationId is null");

        } else {
            FrontEndMigration migration = frontEndMigrationLogic.getFrontEndMigration(frontEndMigrationId);
            if (migration != null) {
                response = ResponseUtils.ok(mapper.map(migration, FrontEndMigrationDto.class));
            } else {
                response = ResponseUtils.internalServerError("front end migration fetching failed");
            }
        }

        return response;
    }

    @ApiOperation(response = FrontEndMigrationDto.class,
            value = "Gets an front end migration")
    @GET
    @Path("/byName/{frontEndMigrationName}")
    public Response listInsurancesByCode(@PathParam("frontEndMigrationName") String frontEndMigrationName) {
        Response response;

        String userId = sessionLogic.getCurrentSessionContext().getUserId();
        if(userId == null){
            return ResponseUtils.badRequest("Not authorized");
        }

        FrontEndMigration migration = frontEndMigrationLogic.getFrontEndMigrationByUserIdName(userId, frontEndMigrationName);
        if (migration != null) {
            response = ResponseUtils.ok(mapper.map(migration, FrontEndMigrationDto.class));
        } else {
            response = ResponseUtils.internalServerError("front end migration fetching failed");
        }

        return response;
    }

    @ApiOperation(response = FrontEndMigrationDto.class, value = "Modifies a front end migration")
    @PUT
    public Response modifyFrontEndMigration(FrontEndMigrationDto frontEndMigrationDto) {
        Response response;

        if (frontEndMigrationDto == null) {
            response = ResponseUtils.badRequest("Cannot modify front end migration: supplied frontEndMigrationDto is null");

        } else {
            FrontEndMigration migration = frontEndMigrationLogic.modifyFrontEndMigration(mapper.map(frontEndMigrationDto, FrontEndMigration.class));
            if (migration != null) {
                response = ResponseUtils.ok(mapper.map(migration, FrontEndMigrationDto.class));
            } else {
                response = ResponseUtils.internalServerError("Insurance modification failed");
            }
        }

        return response;
    }

    @Context
    public void setMapper(MapperFacade mapper) {
        this.mapper = mapper;
    }

    @Context
    public void setFrontEndMigrationLogic(FrontEndMigrationLogic frontEndMigrationLogic) {
        this.frontEndMigrationLogic = frontEndMigrationLogic;
    }

    @Context
    public void setSessionLogic(SessionLogic sessionLogic) {
        this.sessionLogic = sessionLogic;
    }

    @Context
    public void setUserLogic(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Context
    public void setUuidGenerator(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }

    @ExceptionHandler(Exception.class)
    Response exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseUtils.internalServerError(e.getMessage());
    }

}

package com.bristle.orderservice.controller;

import com.bristle.orderservice.service.ProductEntryService;
import com.bristle.orderservice.util.UuidUtils;
import com.bristle.proto.common.ApiError;
import com.bristle.proto.common.ResponseContext;
import com.bristle.proto.order.GetProductEntriesRequest;
import com.bristle.proto.order.GetProductEntriesResponse;
import com.bristle.proto.order.PatchProductionTicketInfoRequest;
import com.bristle.proto.order.PatchProductionTicketInfoResponse;
import com.bristle.proto.order.ProductEntry;
import com.bristle.proto.order.ProductEntryFilter;
import com.bristle.proto.order.ProductEntryServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@GrpcService
public class ProductEntryServiceGrpcController extends ProductEntryServiceGrpc.ProductEntryServiceImplBase {

    private final ProductEntryService m_productEntryService;

    private final UuidUtils m_uuidUtils;

    Logger log = LoggerFactory.getLogger(ProductEntryServiceGrpcController.class);

    public ProductEntryServiceGrpcController(ProductEntryService m_productEntryService, UuidUtils m_uuidUtils) {
        this.m_productEntryService = m_productEntryService;
        this.m_uuidUtils = m_uuidUtils;
    }

    @Override
    public void getProductEntries(GetProductEntriesRequest request, StreamObserver<GetProductEntriesResponse> responseObserver) {
        String requestId = request.getRequestContext().getRequestId();
        log.info("Request id: " + requestId + " , getUnAssignedProductEntries grpc request received.");
        ResponseContext.Builder responseContextBuilder = ResponseContext.newBuilder();
        responseContextBuilder.setRequestId(requestId);

        try {
            if (!request.hasFilter()) {
                throw new Exception("Required filter missing when getProductEntries");
            }
            ProductEntryFilter filter = request.getFilter();
            List<ProductEntry> unAssignedProductEntries
                    = m_productEntryService.getProductEntries(filter.getFilterField(), filter.getProductEntryId());
            responseObserver.onNext(
                    GetProductEntriesResponse.newBuilder()
                            .addAllProductEntry(unAssignedProductEntries)
                            .setResponseContext(responseContextBuilder).build());

        } catch (Exception e) {
            log.error("Request id: " + requestId + " " + e.getMessage());
            e.printStackTrace();
            responseContextBuilder.setError(ApiError.newBuilder()
                    .setErrorMessage(e.getMessage())
                    .setExceptionName(e.getClass().getName()));

            responseObserver.onNext(
                    GetProductEntriesResponse.newBuilder()
                            .setResponseContext(responseContextBuilder).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void patchProductionTicketInfo(PatchProductionTicketInfoRequest request, StreamObserver<PatchProductionTicketInfoResponse> responseObserver) {
        String requestId = request.getRequestContext().getRequestId();
        log.info("Request id: " + requestId + " , patchProductionTicketInfo grpc request received.");
        ResponseContext.Builder responseContextBuilder = ResponseContext.newBuilder();
        responseContextBuilder.setRequestId(requestId);

        try {
            if (!m_uuidUtils.isValidUuid(request.getProductEntryId())) {
                throw new Exception("product entry must be a valid uuid");
            }
            ProductEntry updatedProductEntry;
            if (request.getIsResetToNull()) {
                updatedProductEntry =
                        m_productEntryService.patchProductionTicketInfoOfProductEntry(
                                request.getProductEntryId(),
                                null);
            } else {
                if (!m_uuidUtils.isValidUuid(request.getProductionTicketId())) {
                    throw new Exception("product ticket id must be a valid uuid if not resetting to null");
                }
                updatedProductEntry =
                        m_productEntryService.patchProductionTicketInfoOfProductEntry(
                                request.getProductEntryId(),
                                request.getProductionTicketId());
            }
            responseObserver.onNext(
                    PatchProductionTicketInfoResponse.newBuilder()
                            .setProductEntry(updatedProductEntry)
                            .setResponseContext(responseContextBuilder).build());

        } catch (Exception e) {
            log.error("Request id: " + requestId + " " + e.getMessage());
            e.printStackTrace();
            responseContextBuilder.setError(ApiError.newBuilder()
                    .setErrorMessage(e.getMessage())
                    .setExceptionName(e.getClass().getName()));

            responseObserver.onNext(
                    PatchProductionTicketInfoResponse.newBuilder()
                            .setResponseContext(responseContextBuilder).build());
        }
        responseObserver.onCompleted();
    }
}

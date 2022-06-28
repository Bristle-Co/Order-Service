package com.bristle.orderservice.controller;

import com.bristle.orderservice.converter.OrderEntityConverter;
import com.bristle.orderservice.service.OrderService;
import com.bristle.proto.common.ApiError;
import com.bristle.proto.common.ResponseContext;
import com.bristle.proto.order.DeleteOrderRequest;
import com.bristle.proto.order.DeleteOrderResponse;
import com.bristle.proto.order.GetOrderRequest;
import com.bristle.proto.order.GetOrderResponse;
import com.bristle.proto.order.Order;
import com.bristle.proto.order.OrderFilter;
import com.bristle.proto.order.OrderServiceGrpc;
import com.bristle.proto.order.UpsertOrderRequest;
import com.bristle.proto.order.UpsertOrderResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@GrpcService
public class OrderServiceGrpcController extends OrderServiceGrpc.OrderServiceImplBase {

    private final OrderService m_orderService;
    private final OrderEntityConverter m_orderConverter;

    Logger log = LoggerFactory.getLogger(OrderServiceGrpcController.class);

    OrderServiceGrpcController(OrderService orderService, OrderEntityConverter orderConverter) {
        this.m_orderService = orderService;
        this.m_orderConverter = orderConverter;
    }

    @Override
    public void upsertOrder(UpsertOrderRequest request, StreamObserver<UpsertOrderResponse> responseObserver) {
        String requestId = request.getRequestContext().getRequestId();
        log.info("Request id: " + requestId + " , upsertOrder grpc request received: " + request.getOrder());
        ResponseContext.Builder responseContextBuilder = ResponseContext.newBuilder();
        responseContextBuilder.setRequestId(requestId);
        Order toBeUpserted = request.getOrder();

        try {
            Order upsertedOrder = m_orderService.upsertOrder(toBeUpserted);
            responseObserver.onNext(
                    UpsertOrderResponse.newBuilder()
                            .setOrder(upsertedOrder)
                            .setResponseContext(responseContextBuilder).build());

        } catch (Exception e) {
            log.error("Request id: " + requestId + " " + e.getMessage());
            responseContextBuilder.setError(ApiError.newBuilder()
                    .setErrorMessage(e.getMessage())
                    .setExceptionName(e.getClass().getName()));

            responseObserver.onNext(UpsertOrderResponse.newBuilder()
                    .setResponseContext(responseContextBuilder).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getOrders(GetOrderRequest request, StreamObserver<GetOrderResponse> responseObserver) {
        String requestId = request.getRequestContext().getRequestId();
        log.info("Request id: " + requestId + " , upsertOrder grpc request received: " + request.getFilter());
        ResponseContext.Builder responseContextBuilder = ResponseContext.newBuilder();
        responseContextBuilder.setRequestId(requestId);
        OrderFilter filter = request.getFilter();

        try {
            List<Order> orders = m_orderService.getOrders(filter);
            responseObserver.onNext(
                    GetOrderResponse.newBuilder()
                            .addAllOrder(orders)
                            .setResponseContext(responseContextBuilder).build());

        } catch (Exception e) {
            log.error("Request id: " + requestId + " " + e.getMessage());
            responseContextBuilder.setError(ApiError.newBuilder()
                    .setErrorMessage(e.getMessage())
                    .setExceptionName(e.getClass().getName()));

            responseObserver.onNext(
                    GetOrderResponse.newBuilder()
                    .setResponseContext(responseContextBuilder).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void deleteOrder(DeleteOrderRequest request, StreamObserver<DeleteOrderResponse> responseObserver) {
        String requestId = request.getRequestContext().getRequestId();
        log.info("Request id: " + requestId + " , deleteOrder grpc request received: " + request.getOrderId());
        ResponseContext.Builder responseContextBuilder = ResponseContext.newBuilder();
        responseContextBuilder.setRequestId(requestId);

        try {
            Order deleteOrder = m_orderService.deleteOrder(request.getOrderId());
            responseObserver.onNext(
                    DeleteOrderResponse.newBuilder()
                            .setDeletedOrder(deleteOrder)
                            .setResponseContext(responseContextBuilder).build());

        } catch (Exception e) {
            log.error("Request id: " + requestId + " " + e.getMessage());
            responseContextBuilder.setError(ApiError.newBuilder()
                    .setErrorMessage(e.getMessage())
                    .setExceptionName(e.getClass().getName()));

            responseObserver.onNext(
                    DeleteOrderResponse.newBuilder()
                            .setResponseContext(responseContextBuilder).build());
        }
        responseObserver.onCompleted();
    }
}

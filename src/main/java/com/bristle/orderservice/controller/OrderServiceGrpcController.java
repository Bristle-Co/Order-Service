package com.bristle.orderservice.controller;

import com.bristle.proto.order.OrderServiceGrpc;
import com.bristle.proto.order.UpsertOrderRequest;
import com.bristle.proto.order.UpsertOrderResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderServiceGrpcController extends OrderServiceGrpc.OrderServiceImplBase {
    @Override
    public void upsertOrder(UpsertOrderRequest request, StreamObserver<UpsertOrderResponse> responseObserver) {

    }
}

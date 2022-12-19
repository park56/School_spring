package com.example.jwttest.response;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public<T> SingleResponse<T> getSingleResponse(T data) {
        SingleResponse singleResponse = new SingleResponse();
        setSuccessResponse(singleResponse);
        singleResponse.data = data;

        return singleResponse;
    }

    public<T> ListResponse<T> getListResponse(List<T> datas) {
        ListResponse listResponse = new ListResponse();
        setSuccessResponse(listResponse);
        listResponse.dataList=datas;

        return listResponse;
    }

    void setSuccessResponse(CommonResponse response) {
        response.code=200;
        response.success= true;
        response.message = "Success";
    }


}

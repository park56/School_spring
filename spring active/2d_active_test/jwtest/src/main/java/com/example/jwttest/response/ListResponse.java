package com.example.jwttest.response;

import java.util.List;

public class ListResponse<T> extends CommonResponse{
    List<T> dataList;
}

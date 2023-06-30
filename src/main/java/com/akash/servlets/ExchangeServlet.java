package com.akash.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.akash.dto.ReponseTO;
import com.akash.dto.RequestTO;
import com.akash.dto.SessionDescriptionProtocol;
import com.akash.service.ConnectionService;
import com.akash.util.JsonUtil;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {
    
    private ConnectionService connectionService = ConnectionService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        RequestTO requestTO = JsonUtil.parse(json);

        SessionDescriptionProtocol sdp = connectionService.exchange(requestTO);

        ReponseTO reponseTO;
        if(sdp != null) {
            reponseTO = new ReponseTO(true, requestTO.getOtherUsername(), sdp);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            reponseTO = new ReponseTO(false, null, null);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        resp.setContentType("application/json");
        resp.getWriter().print(JsonUtil.stringyfy(reponseTO));
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }

    
    
}
